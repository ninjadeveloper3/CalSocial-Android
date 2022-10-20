package com.CalSocial.ui.events.createEvent;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.CalSocial.R;
import com.CalSocial.data.network.model.EventAttendeesResponse;
import com.CalSocial.di.component.ActivityComponent;
import com.CalSocial.ui.base.BaseFragment;
import com.CalSocial.ui.events.adapter.AttendeesAddGuestAdapter;
import com.CalSocial.ui.events.eventCreatedDialog.EventCreatedDialog;
import com.CalSocial.utils.AppConstants;
import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.OnSeekChangeListener;
import com.warkiz.widget.SeekParams;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateEventFragment extends BaseFragment implements CreateEventMvpView {

    public static final String TAG = "CreateEventFragment";

    @Inject
    LinearLayoutManager mAttendeesLayoutManager;

    @BindView(R.id.attendeesRV)
    RecyclerView mAttendeesRecyclerView;

    @Inject
    AttendeesAddGuestAdapter mAttendeesAdapter;

    @Inject
    CreateEventPresenter<CreateEventMvpView> mPresenter;

    @Inject
    LinearLayoutManager mMembersLayoutManager;

    @BindView(R.id.eventDate)
    TextView eventDate;

    @BindView(R.id.thirtyMinTv)
    TextView thirtyMinTv;

    @BindView(R.id.allDayTv)
    TextView allDayTv;

    @BindView(R.id.eventDuration)
    IndicatorSeekBar eventDuration;

    public static CreateEventFragment newInstance() {
        Bundle args = new Bundle();
        CreateEventFragment fragment = new CreateEventFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.events_fragment_create_event, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);

            mAttendeesLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mAttendeesRecyclerView.setLayoutManager(mAttendeesLayoutManager);
            mAttendeesRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mAttendeesRecyclerView.setAdapter(mAttendeesAdapter);
            mPresenter.onAttendeesRVViewPrepared();

            thirtyMinTv.setTypeface(null, Typeface.BOLD);
            thirtyMinTv.setTypeface(null, Typeface.BOLD);
            allDayTv.setTypeface(null, Typeface.BOLD);

            String[] durations = {"0hr", "0.5hr", "1hr", "1.5hr", "2hr", "2.5hr", "3hr", "3.5hr", "4hr", "4.5hr", "5hr", "5.5hr", "6hr", "All Day"};

            //eventDuration.setIndicatorTextFormat("${durations[PROGRESS]}");
            View thumbView = LayoutInflater.from(getActivity()).inflate(R.layout.seekbar_indicator, null, false);
            eventDuration.getIndicator().setContentView(thumbView);
            eventDuration.setOnSeekChangeListener(new OnSeekChangeListener() {

                @Override
                public void onSeeking(SeekParams seekParams) {
                    ((TextView) (thumbView.findViewById(R.id.seekerText))).setTypeface(null, Typeface.BOLD);
                    ((TextView) (thumbView.findViewById(R.id.seekerText))).setText(durations[eventDuration.getProgress()]);
                }

                @Override
                public void onStartTrackingTouch(IndicatorSeekBar seekBar) {


                }

                @Override
                public void onStopTrackingTouch(IndicatorSeekBar seekBar) {
                    //seekBar.setIndicatorTextFormat(durations[0]);
                }
            });


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
    public void updateAttendeesRepo(List<EventAttendeesResponse.User> userList) {
        mAttendeesAdapter.addItems(userList);
    }

    @OnClick(R.id.eventDate)
    void onDateClicked() {

        mPresenter.openSelectDateTimeFragment(TAG + "-" + AppConstants.OPEN_SELECT_DATE_TIME_CREATE_EVENT);
    }

    @OnClick(R.id.CalSocialButton)
    void onConfirmSendClicked() {

        EventCreatedDialog.newInstance().show(getFragmentManager());
        mPresenter.onConfirmSendClicked(TAG + "-" + AppConstants.OPEN_EVENT_DETAIL);
    }
}