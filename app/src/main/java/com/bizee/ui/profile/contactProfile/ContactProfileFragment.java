package com.CalSocial.ui.profile.contactProfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.CalSocial.R;
import com.CalSocial.data.network.model.EventsHomeResponse;
import com.CalSocial.di.component.ActivityComponent;
import com.CalSocial.event.OpenContactProfileEvent;
import com.CalSocial.ui.base.BaseFragment;
import com.CalSocial.ui.events.EventsActivity;
import com.CalSocial.ui.homeMain.HomeMainActivity;
import com.CalSocial.ui.profile.adapter.ProfileEventsAdapter;
import com.CalSocial.utils.AppConstants;
import com.CalSocial.utils.ViewUtils;
import com.CalSocial.widget.CalSocialButton;
import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactProfileFragment extends BaseFragment implements ContactProfileMvpView {

    public static final String TAG = "ContactProfileFragment";

    @BindView(R.id.coverImage)
    ImageView coverImage;

    @BindView(R.id.profileImage)
    ImageView profileImage;

    @BindView(R.id.name)
    TextView nameTV;

    @BindView(R.id.favorite)
    ImageView favorite;

    @BindView(R.id.location)
    TextView locationTV;

    @BindView(R.id.bioDescription)
    TextView bioDescriptionTV;

    @BindView(R.id.bucketListTitle)
    TextView bucketListItemTitle;

    @BindView(R.id.bucketItemOne)
    LinearLayout bucketItemOne;

    @BindView(R.id.bucketItemOneText)
    TextView bucketItemOneText;

    @BindView(R.id.bucketItemTwo)
    LinearLayout bucketItemTwo;

    @BindView(R.id.bucketItemTwoText)
    TextView bucketItemTwoText;

    @BindView(R.id.bucketItemThree)
    LinearLayout bucketItemThree;

    @BindView(R.id.bucketItemThreeText)
    TextView bucketItemThreeText;

    @BindView(R.id.bucketItemFour)
    LinearLayout bucketItemFour;

    @BindView(R.id.bucketItemFourText)
    TextView bucketItemFourText;

    @BindView(R.id.bucketItemFive)
    LinearLayout bucketItemFive;

    @BindView(R.id.bucketItemFiveText)
    TextView bucketItemFiveText;

    @BindView(R.id.inHive)
    CalSocialButton inHive;

    @BindView(R.id.addToHive)
    CalSocialButton addToHive;

    @BindView(R.id.requestSent)
    CalSocialButton requestSent;

    @BindView(R.id.acceptDecline)
    LinearLayout acceptDecline;

    @BindView(R.id.accept)
    CalSocialButton accept;

    @BindView(R.id.decline)
    CalSocialButton decline;

    @BindView(R.id.eventsScheduled)
    TextView eventsScheduled;

    @Inject
    LinearLayoutManager mEventsLayoutManager;

    @BindView(R.id.eventsRV)
    RecyclerView mEventsRecyclerView;

    @Inject
    ProfileEventsAdapter mProfileEventsAdapter;

    @Inject
    ContactProfilePresenter<ContactProfileMvpView> mPresenter;

    public static ContactProfileFragment newInstance() {
        Bundle args = new Bundle();
        ContactProfileFragment fragment = new ContactProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profiles_fragment_contact_profile, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
        }

        mPresenter.getContactDetails();

        mEventsRecyclerView.setLayoutManager(mEventsLayoutManager);
        mEventsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mEventsRecyclerView.setAdapter(mProfileEventsAdapter);
        mPresenter.onEventsRVViewPrepared();

        return view;
    }

    @Override
    protected void setUp(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    /*@OnClick(R.id.nav_back_btn)
    void onNavBackClick() {
        getBaseActivity().onFragmentDetached(TAG);
    }*/

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }

    @Override
    public void populateViews(String name, String location, String profileImage, String coverImage, Boolean isFavorite, String bioDescription, List<String> bucketList, HiveStatus hiveStatus) {

        LinearLayout[] bucketItems = {bucketItemOne, bucketItemTwo, bucketItemThree, bucketItemFour, bucketItemFive};
        TextView[] bucketItemsTextView = {bucketItemOneText, bucketItemTwoText, bucketItemThreeText, bucketItemFourText, bucketItemFiveText};
        nameTV.setText(name);
        locationTV.setText(location);

        if (isFavorite) {
            favorite.setVisibility(View.VISIBLE);
        } else {
            favorite.setVisibility(View.GONE);
        }

        bioDescriptionTV.setText(bioDescription);

        if (bucketList.size() > 0) {
            bucketListItemTitle.setVisibility(View.VISIBLE);
            for (int i = 0; i < bucketList.size(); i++) {
                bucketItems[i].setVisibility(View.VISIBLE);
                bucketItemsTextView[i].setText(bucketList.get(i));
            }

        }

        if (hiveStatus == HiveStatus.IN_HIVE) {
            inHive.setVisibility(View.VISIBLE);
            requestSent.setVisibility(View.GONE);
            acceptDecline.setVisibility(View.GONE);
            addToHive.setVisibility(View.GONE);
        } else if (hiveStatus == HiveStatus.NOT_IN_HIVE) {
            inHive.setVisibility(View.GONE);
            requestSent.setVisibility(View.GONE);
            acceptDecline.setVisibility(View.GONE);
            addToHive.setVisibility(View.VISIBLE);
        } else if (hiveStatus == HiveStatus.REQUEST_SENT) {
            inHive.setVisibility(View.GONE);
            requestSent.setVisibility(View.VISIBLE);
            acceptDecline.setVisibility(View.GONE);
            addToHive.setVisibility(View.GONE);
        } else if (hiveStatus == HiveStatus.REQUEST_RECEIVED) {
            inHive.setVisibility(View.GONE);
            requestSent.setVisibility(View.GONE);
            acceptDecline.setVisibility(View.VISIBLE);
            addToHive.setVisibility(View.GONE);
        } else {
            inHive.setVisibility(View.GONE);
            requestSent.setVisibility(View.GONE);
            acceptDecline.setVisibility(View.GONE);
            addToHive.setVisibility(View.VISIBLE);
        }

    }

    @OnClick(R.id.addToHive)
    void onAddToHiveClicked() {
        mPresenter.sendHiveRequest();
    }

    @Override
    public void sendHiveRequest() {

        inHive.setVisibility(View.GONE);
        requestSent.setVisibility(View.VISIBLE);
        acceptDecline.setVisibility(View.GONE);
        addToHive.setVisibility(View.GONE);
    }

    @Override
    public void onFragmentDetached(String TAG) {
        hideLoading();
        getBaseActivity().onFragmentDetached(TAG);
    }

    @Override
    public void updateProfileEventsRepo(List<EventsHomeResponse.Event> eventList) {
        eventsScheduled.setText(eventList.size() + " Events Scheduled with " + nameTV.getText());
        mProfileEventsAdapter.addItems(eventList);

    }

    @Override
    public void openCreateMessage() {
        Intent intent = new Intent(getActivity(), HomeMainActivity.class);
        intent.putExtra("screenToOpen", AppConstants.MESSAGES_ONE_TO_ONE_FRAGMENT);
        getActivity().startActivity(intent);
    }

    @Override
    public void openNewEvent() {
        Intent intent = new Intent(getActivity(), EventsActivity.class);
        intent.putExtra("screenToOpen", AppConstants.CREATE_EVENT_FRAGMENT);
        startActivity(intent);
    }

    @OnClick(R.id.message)
    void onMessageClicked() {
        mPresenter.onMessagePressed();

    }

    @OnClick(R.id.newEvent)
    void onNewEventClicked() {
        mPresenter.onAddNewEventClicked();
    }

    @OnClick(R.id.viewHive)
    void onViewHiveClicked() {
        mPresenter.showHive(TAG + "-" + AppConstants.OPEN_VIEW_HIVE_PROFILE);
    }


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true, priority = 1)
    public void onMessageEvent(OpenContactProfileEvent event) {

        Glide.with(getActivity())
                .load(event.profileImage)
                .asBitmap()
                .centerCrop()
                .into(ViewUtils.getRoundedImageTarget(getActivity(), profileImage, 15));
        nameTV.setText(event.name);
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


}