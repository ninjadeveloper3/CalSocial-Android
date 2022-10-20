package com.CalSocial.ui.onboarding.createFirstEvent;

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
import com.CalSocial.ui.base.BaseFragment;
import com.CalSocial.ui.events.EventsActivity;
import com.CalSocial.utils.AppConstants;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateFirstEventFragment extends BaseFragment implements CreateFirstEventMvpView {

    public static final String TAG = "CreateFirstEventFragment";

    @BindView(R.id.skipForNow)
    TextView skipForNow;

    @Inject
    CreateFirstEventPresenter<CreateFirstEventMvpView> mPresenter;

    public static CreateFirstEventFragment newInstance() {
        Bundle args = new Bundle();
        CreateFirstEventFragment fragment = new CreateFirstEventFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.onboarding_fragment_create_first_event, container, false);

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

    }

    @Override
    public void onFragmentDetached(String TAG) {
        hideLoading();
        getBaseActivity().onFragmentDetached(TAG);
    }

    @Override
    public void openCreateNewEvent() {
        Intent intent = new Intent(getActivity(), EventsActivity.class);
        intent.putExtra("screenToOpen", AppConstants.CREATE_EVENT_FRAGMENT);
        //intent.putExtra("eventId", mUserResponseList.get(position).getId());
        startActivity(intent);
    }

    @OnClick(R.id.skipForNow)
    void onSkipForNowClicked() {
        mPresenter.onSkipForNowClicked(TAG + "-" + AppConstants.OPEN_HOME);
    }

    @OnClick(R.id.CalSocialButton)
    void onCreateNewEventClicked(){
        mPresenter.onCreateNewEventClicked();
    }




}
