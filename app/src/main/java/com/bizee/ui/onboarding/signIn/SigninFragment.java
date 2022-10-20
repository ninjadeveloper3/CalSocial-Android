package com.CalSocial.ui.onboarding.signIn;

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

public class SigninFragment extends BaseFragment implements SigninMvpView {
    public static final String TAG = "SigninFragment";

    @BindView(R.id.phone)
    CalSocialEditText phone;

    @BindView(R.id.goBack)
    TextView goBack;

    @Inject
    SigninPresenter<SigninMvpView> mPresenter;

    public static SigninFragment newInstance() {
        Bundle args = new Bundle();
        SigninFragment fragment = new SigninFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.onboarding_fragment_signin, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
            goBack.setTypeface(null, Typeface.BOLD);

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
    public void showAlertDialogMessage(String title, String message) {
        AlertDialog alertDialog = AlertDialog.newInstance();
        alertDialog.setAlertTitle(title);
        alertDialog.setAlertMessage(message);
        alertDialog.show(getFragmentManager());

    }

    @Override
    public void onFragmentDetached(String TAG) {
        hideLoading();
        getBaseActivity().onFragmentDetached(TAG);
    }

    @OnClick(R.id.goBack)
    void goBackClicked() {
        mPresenter.onGoBackClicked(TAG + "-" + AppConstants.OPEN_SIGNUP);
    }

    @OnClick(R.id.CalSocialButton)
    void onContinueClicked() {
        mPresenter.onContinueClicked(phone.getEditText().getText().toString().trim(), TAG + "-" + AppConstants.PROCEED_TO_PHONE_VERIFICATION_SIGNUP);
    }


    @Override
    public Context getContext() {
        return getActivity();
    }

}