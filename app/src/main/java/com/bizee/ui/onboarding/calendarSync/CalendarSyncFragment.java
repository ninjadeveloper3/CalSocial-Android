package com.CalSocial.ui.onboarding.calendarSync;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.CalSocial.R;
import com.CalSocial.di.component.ActivityComponent;
import com.CalSocial.service.CalenderSyncService;
import com.CalSocial.ui.base.BaseFragment;
import com.CalSocial.utils.AppConstants;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CalendarSyncFragment extends BaseFragment implements CalendarSyncMvpView {

    public static final String TAG = "CalendarSyncFragment";

    @Inject
    CalendarSyncPresenter<CalendarSyncMvpView> mPresenter;

    @BindView(R.id.skipForNow)
    TextView skipForNow;

    public static CalendarSyncFragment newInstance() {
        Bundle args = new Bundle();
        CalendarSyncFragment fragment = new CalendarSyncFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.onboarding_fragment_sync_calendars, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
            skipForNow.setTypeface(null, Typeface.BOLD);

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


    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }


    @OnClick(R.id.skipForNow)
    void onSkipForNowClicked() {
        mPresenter.onSkipForNowClicked(TAG + "-" + AppConstants.CREATE_FIRST_EVENT);
    }

    @Override
    public void onFragmentDetached(String TAG) {
        hideLoading();
        getBaseActivity().onFragmentDetached(TAG);
    }

    @Override
    public void startCalenderSync() {
        Intent calenderSyncServiceIntent = new Intent(getActivity(), CalenderSyncService.class);
        getActivity().startService(calenderSyncServiceIntent);
    }

    @OnClick(R.id.selectCalender)
    public void onSelectCalanderClicked() {
        //TODO: start the syncing background process
        mPresenter.onSyncCalenderClicked();

    }

}