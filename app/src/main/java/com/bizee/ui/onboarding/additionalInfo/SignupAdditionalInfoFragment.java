package com.CalSocial.ui.onboarding.additionalInfo;

import android.content.Context;
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
import com.CalSocial.ui.dialog.alert.AlertDialog;
import com.CalSocial.utils.AppConstants;
import com.CalSocial.widget.CalSocialEditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignupAdditionalInfoFragment extends BaseFragment implements SignupAdditionalInfoMvpView {

    public static final String TAG = "SignupAdditionalInfoFragment";

    @BindView(R.id.location)
    CalSocialEditText location;

    @BindView(R.id.bio)
    CalSocialEditText bio;

    @BindView(R.id.skipForNow)
    TextView skipForNow;

    @Inject
    SignupAdditionalInfoPresenter<SignupAdditionalInfoMvpView> mPresenter;

    public static SignupAdditionalInfoFragment newInstance() {
        Bundle args = new Bundle();
        SignupAdditionalInfoFragment fragment = new SignupAdditionalInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.onboarding_fragment_signup_additional_info, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
            location.requestFocus();
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

    /*@OnClick(R.id.nav_back_btn)
    void onNavBackClick() {
        getBaseActivity().onFragmentDetached(TAG);
    }*/

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }

    @OnClick(R.id.CalSocialButton)
    void onSaveContinueClicked() {

        mPresenter.onSaveContinueClicked(location.getEditText().getText().toString().trim(), bio.getEditText().getText().toString().trim(), TAG + "-" + AppConstants.CONTACTS_SYNC);
    }

    @OnClick(R.id.skipForNow)
    void onSkipForNowClicked() {
        mPresenter.onSkipForNowClicked(TAG + "-" + AppConstants.CONTACTS_SYNC);
    }

    @Override
    public void onFragmentDetached(String TAG) {
        hideLoading();
        getBaseActivity().onFragmentDetached(TAG);
    }

    @Override
    public void showAlertDialogMessage(String title, String message) {
        AlertDialog alertDialog = AlertDialog.newInstance();
        alertDialog.setAlertTitle(title);
        alertDialog.setAlertMessage(message);
        alertDialog.show(getFragmentManager());
    }

    @Override
    public Context getContext() {
        return getActivity();
    }

}