package com.CalSocial.ui.events;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.CalSocial.R;
import com.CalSocial.event.OpenFragmentEvent;
import com.CalSocial.ui.base.BaseActivity;
import com.CalSocial.ui.events.addGuest.AddGuestFragment;
import com.CalSocial.ui.events.createEvent.CreateEventFragment;
import com.CalSocial.ui.events.eventDetail.EventDetailDetailFragment;
import com.CalSocial.ui.events.eventsMoreMenuDialog.EventsMoreMenuDialog;
import com.CalSocial.ui.events.selectDateTime.SelectDateTimeEventFragment;
import com.CalSocial.ui.events.selectTimeSuggestions.SelectTimeSuggestionsEventFragment;
import com.CalSocial.utils.AppConstants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventsActivity extends BaseActivity implements EventsMvpView {

    @BindView(R.id.fragmentRoot)
    LinearLayout rootLL;

    @BindView(R.id.moreSettings)
    ImageView moreSettings;

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.goBack)
    ImageView goBack;

    @BindView(R.id.addGuests)
    TextView addGuests;

    @BindView(R.id.cancelContactChanges)
    TextView cancel;

    private AppConstants.EventsPageMoreSettingsSource moreSettingsSource;

    @Inject
    EventsPresenter<EventsMvpView> mPresenter;


    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, EventsActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events_activity_events);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(EventsActivity.this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null && bundle.getString("screenToOpen") != null) {
            mPresenter.onScreenCreated(bundle.getString("screenToOpen"));
        }

    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }


    @Override
    public void onFragmentAttached() {
    }

    @Override
    public void onFragmentDetached(String tag) {
        if (tag.equalsIgnoreCase("")) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            List<Fragment> fragments = fragmentManager.getFragments();
            for (int i = 0; i < fragments.size(); i++) {
                if (fragmentManager.findFragmentByTag(fragments.get(i).getTag()) != null) {
                    fragmentManager.beginTransaction()
                            .disallowAddToBackStack()
                            .remove(fragments.get(i))
                            .commitNow();
                }
            }

        } else {
            String option = tag.split("-")[1];
            tag = tag.split("-")[0];
            FragmentManager fragmentManager = getSupportFragmentManager();
            Fragment fragment = fragmentManager.findFragmentByTag(tag);
            if (fragment != null) {
                fragmentManager
                        .beginTransaction()
                        .disallowAddToBackStack()
                        .remove(fragment)
                        .commitNow();
            }

            if (option.compareToIgnoreCase(AppConstants.OPEN_EVENT_DETAIL) == 0) {
                mPresenter.showEventDetailFragment();
            } else if (option.compareToIgnoreCase(AppConstants.OPEN_CREATE_EVENT) == 0) {
                mPresenter.showCreateEventFragment();
            } else if (option.compareToIgnoreCase(AppConstants.OPEN_ADD_GUEST_EVENT) == 0) {
                mPresenter.showAddGuestFragment();
            } else if (option.compareToIgnoreCase(AppConstants.OPEN_SELECT_DATE_TIME_CREATE_EVENT) == 0) {
                mPresenter.showSelectDateTimeEventFragment();
            } else if (option.compareToIgnoreCase(AppConstants.OPEN_CalSocial_SUGGESTIONS_CREATE_EVENT) == 0) {
                mPresenter.showCalSocialSuggestionsEventFragment();
            }


        }


    }

    @Override
    protected void setUp() {

    }

    @Override
    public void showEventDetailFragment() {
        //moreSettingsSource = AppConstants.EventsPageMoreSettingsSource.CONTACT_PROFILE;
        onFragmentDetached("");
        goBack.setVisibility(View.VISIBLE);
        cancel.setVisibility(View.GONE);
        title.setVisibility(View.VISIBLE);
        title.setText("Event Name");
        addGuests.setVisibility(View.GONE);
        moreSettings.setVisibility(View.VISIBLE);

        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .add(R.id.fragmentRoot, EventDetailDetailFragment.newInstance(), EventDetailDetailFragment.TAG)
                .commit();
    }

    @Override
    public void showCreateEventFragment() {
        //moreSettingsSource = AppConstants.EventsPageMoreSettingsSource.SWARM_PROFILE;

        goBack.setVisibility(View.GONE);
        cancel.setVisibility(View.VISIBLE);
        title.setVisibility(View.VISIBLE);
        title.setText("New Event");
        addGuests.setVisibility(View.GONE);
        moreSettings.setVisibility(View.GONE);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(AddGuestFragment.TAG);
        if (fragment != null) {
            fragmentManager
                    .beginTransaction()
                    .disallowAddToBackStack()
                    .remove(fragment)
                    .commitNow();
        }

        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .add(R.id.fragmentRoot, CreateEventFragment.newInstance(), CreateEventFragment.TAG)
                .commit();
    }

    @Override
    public void showAddGuestFragment() {

        onFragmentDetached("");
        goBack.setVisibility(View.VISIBLE);
        cancel.setVisibility(View.GONE);
        title.setVisibility(View.VISIBLE);
        title.setText("1 Selected");
        addGuests.setVisibility(View.VISIBLE);
        moreSettings.setVisibility(View.GONE);

        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .add(R.id.fragmentRoot, AddGuestFragment.newInstance(), AddGuestFragment.TAG)
                .commit();
    }

    @Override
    public void showSelectDateTimeEventFragment() {
        onFragmentDetached("");
        goBack.setVisibility(View.VISIBLE);
        cancel.setVisibility(View.GONE);
        title.setVisibility(View.VISIBLE);
        title.setText("Date & Time");
        addGuests.setVisibility(View.GONE);
        moreSettings.setVisibility(View.GONE);
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .add(R.id.fragmentRoot, SelectDateTimeEventFragment.newInstance(), SelectDateTimeEventFragment.TAG)
                .commit();
    }

    @Override
    public void showCalSocialSuggestionsEventFragment() {
        onFragmentDetached("");
        goBack.setVisibility(View.VISIBLE);
        cancel.setVisibility(View.GONE);
        title.setVisibility(View.VISIBLE);
        title.setText("Choose Time");
        addGuests.setVisibility(View.GONE);
        moreSettings.setVisibility(View.GONE);
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .add(R.id.fragmentRoot, SelectTimeSuggestionsEventFragment.newInstance(), SelectTimeSuggestionsEventFragment.TAG)
                .commit();
    }

    @Override
    public void goToHomeScreen() {
        finish();
    }


    @OnClick(R.id.moreSettings)
    void onMoreSettingsClicked() {
        //EventBus.getDefault().post(new ToolBarEvent(AppConstants.SWARM_PROFILE));

        EventsMoreMenuDialog.newInstance().show(getSupportFragmentManager());

        if (moreSettingsSource == AppConstants.EventsPageMoreSettingsSource.EVENT_OWNER) {
            //open the popup dialog
        } else {
            //open the edit swarm fragment
            //mPresenter.showEditSwarmFragment();
        }
    }


    @OnClick(R.id.goBack)
    void goBack() {
        if (getSupportFragmentManager().findFragmentByTag(AddGuestFragment.TAG) != null) {
            onFragmentDetached("");
            mPresenter.showCreateEventFragment();
        } else if (getSupportFragmentManager().findFragmentByTag(EventDetailDetailFragment.TAG) != null) {
            onFragmentDetached("");
            finish();
        } else if (getSupportFragmentManager().findFragmentByTag(SelectTimeSuggestionsEventFragment.TAG) != null) {
            onFragmentDetached("");
            mPresenter.showCreateEventFragment();
        } else if (getSupportFragmentManager().findFragmentByTag(SelectDateTimeEventFragment.TAG) != null) {
            onFragmentDetached("");
            mPresenter.showCreateEventFragment();
        }

    }

    @OnClick(R.id.addGuests)
    void onAddGuestsClicked() {
        mPresenter.showCreateEventFragment();
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true, priority = 1)
    public void onMessageEvent(OpenFragmentEvent event) {
        if (event.targetFragment.equalsIgnoreCase(AddGuestFragment.TAG)) {
            onFragmentDetached("");
            mPresenter.showAddGuestFragment();
        } else if (event.targetFragment.equalsIgnoreCase(CreateEventFragment.TAG)) {
            onFragmentDetached("");
            mPresenter.showCreateEventFragment();
        }

        EventBus.getDefault().removeStickyEvent(event);

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.cancelContactChanges)
    void onCancelClicked() {
        onFragmentDetached("");
        mPresenter.onCancelClicked();
    }


}