package com.CalSocial.ui.events.selectDateTime;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.CalSocial.R;
import com.CalSocial.di.component.ActivityComponent;
import com.CalSocial.ui.base.BaseFragment;
import com.CalSocial.utils.AppConstants;

import net.cachapa.expandablelayout.ExpandableLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectDateTimeEventFragment extends BaseFragment implements SelectDateTimeEventMvpView {

    public static final String TAG = "SelectDateTimeEventFragment";

    @BindView(R.id.calHeaderCalSocial)
    LinearLayout calHeaderCalSocial;

    @BindView(R.id.expandableLayoutCal)
    ExpandableLayout expandableLayoutCal;

    @BindView(R.id.startTimeLL)
    LinearLayout startTimeLL;

    @BindView(R.id.startTime)
    TextView startTime;

    @BindView(R.id.expandableLayoutStartTime)
    ExpandableLayout expandableLayoutStartTime;

    @BindView(R.id.endTimeLL)
    LinearLayout endTimeLL;

    @BindView(R.id.expandableLayoutEndTime)
    ExpandableLayout expandableLayoutEndTime;

    @BindView(R.id.endTime)
    TextView endTime;


    @Inject
    SelectDateTimeEventPresenter<SelectDateTimeEventMvpView> mPresenter;

    public static SelectDateTimeEventFragment newInstance() {
        Bundle args = new Bundle();
        SelectDateTimeEventFragment fragment = new SelectDateTimeEventFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.events_fragment_create_event_select_date_time, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);

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

    @OnClick(R.id.calHeaderCalSocial)
    void onCalendarClicked() {
        if (expandableLayoutCal.isExpanded()) {
            expandableLayoutCal.collapse();
        } else {
            expandableLayoutCal.expand();
        }

    }


    @OnClick(R.id.startTimeLL)
    void onStartTimeLLClicked() {
        if (expandableLayoutStartTime.isExpanded()) {
            expandableLayoutStartTime.collapse();
        } else {
            expandableLayoutStartTime.expand();
        }

    }

    @OnClick(R.id.endTimeLL)
    void onEndTimeLLClicked() {

        if (expandableLayoutEndTime.isExpanded()) {
            expandableLayoutEndTime.collapse();
        } else {
            expandableLayoutEndTime.expand();
        }
    }


    @OnClick(R.id.CalSocialButton)
    void oContinueClicked() {
        mPresenter.openTimeSuggestions(TAG + "-" + AppConstants.OPEN_CalSocial_SUGGESTIONS_CREATE_EVENT);
    }


}