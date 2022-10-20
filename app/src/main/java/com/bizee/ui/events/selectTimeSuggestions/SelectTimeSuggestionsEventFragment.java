package com.CalSocial.ui.events.selectTimeSuggestions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.CalSocial.R;
import com.CalSocial.data.network.model.EventAttendeesResponse;
import com.CalSocial.data.network.model.TimeSuggestionsResponse;
import com.CalSocial.di.component.ActivityComponent;
import com.CalSocial.event.OpenFragmentEvent;
import com.CalSocial.ui.base.BaseFragment;
import com.CalSocial.ui.events.adapter.CalSocialTimeSuggesstionsAttendeesAdapter;
import com.CalSocial.ui.events.adapter.TimeSuggestionsAdapter;
import com.CalSocial.ui.events.createEvent.CreateEventFragment;

import net.cachapa.expandablelayout.ExpandableLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectTimeSuggestionsEventFragment extends BaseFragment implements SelectTimeSuggestionsEventMvpView {

    public static final String TAG = "SelectTimeSuggestionsEventFragment";

    @Inject
    SelectTimeSuggestionsEventPresenter<SelectTimeSuggestionsEventMvpView> mPresenter;

    @BindView(R.id.selectedTime)
    LinearLayout selectedTime;

    @BindView(R.id.selectTimeExpandableLayout)
    ExpandableLayout selectTimeExpandableLayout;

    @BindView(R.id.useTimeButton)
    Button useTimeButton;

    @BindView(R.id.suggestionsRV)
    RecyclerView suggestionsRV;

    @Inject
    LinearLayoutManager mSuggestionsLinearLayoutManager;

    @Inject
    TimeSuggestionsAdapter mTimeSuggestionsAdapter;

    @BindView(R.id.attendeesRV)
    RecyclerView attendeesRV;

    @Inject
    LinearLayoutManager mAttendeesLinearLayoutManager;

    @Inject
    CalSocialTimeSuggesstionsAttendeesAdapter mAttendeesAdapter;

    @BindView(R.id.title)
    TextView title;


    public static SelectTimeSuggestionsEventFragment newInstance() {
        Bundle args = new Bundle();
        SelectTimeSuggestionsEventFragment fragment = new SelectTimeSuggestionsEventFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.events_fragment_create_event_time_suggestions, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);

            suggestionsRV.setLayoutManager(mSuggestionsLinearLayoutManager);
            suggestionsRV.setItemAnimator(new DefaultItemAnimator());
            mTimeSuggestionsAdapter.setPresenter(mPresenter);
            suggestionsRV.setAdapter(mTimeSuggestionsAdapter);
            mPresenter.onSuggestionsRVViewPrepared();

            mAttendeesLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            attendeesRV.setLayoutManager(mAttendeesLinearLayoutManager);
            attendeesRV.setItemAnimator(new DefaultItemAnimator());
            attendeesRV.setAdapter(mAttendeesAdapter);
            mPresenter.onAttendeesRVViewPrepared();

        }


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
    public void onFragmentDetached(String TAG) {
        hideLoading();
        getBaseActivity().onFragmentDetached(TAG);
    }

    @Override
    public void updateSuggestionsRepo(List<TimeSuggestionsResponse.Suggestion> suggestionsList) {
        mTimeSuggestionsAdapter.addItems(suggestionsList);
    }

    @Override
    public void updateAttendeesRepo(List<EventAttendeesResponse.User> userList) {
        mAttendeesAdapter.addItems(userList);
    }

    @OnClick(R.id.selectedTimeCV)
    void onselectedTimeCVClicked() {
        if (selectTimeExpandableLayout.isExpanded()) {
            selectTimeExpandableLayout.collapse();
            title.animate().translationYBy(-260).setStartDelay(200);
            suggestionsRV.animate().translationYBy(-260).setStartDelay(200);
        } else {
            selectTimeExpandableLayout.expand();
            title.animate().translationYBy(260);
            suggestionsRV.animate().translationYBy(260);

        }

    }


    @OnClick(R.id.useTimeButton)
    void onUseTimeButtonClicked() {
        EventBus.getDefault().postSticky(new OpenFragmentEvent(SelectTimeSuggestionsEventFragment.TAG, CreateEventFragment.TAG));
    }

}