package com.CalSocial.ui.onboarding.signUp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
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

public class SignupFragment extends BaseFragment implements SignupMvpView {

    public static final String TAG = "SignupFragment";

    @BindView(R.id.firstName)
    CalSocialEditText firstName;

    @BindView(R.id.lastName)
    CalSocialEditText lastName;

    @BindView(R.id.phone)
    CalSocialEditText phone;


    @BindView(R.id.signInLink)
    TextView signInLink;

    @BindView(R.id.agreePolicyLink)
    TextView agreePolicyLink;

    @BindView(R.id.agreeTermsLink)
    TextView agreeTermsLink;

    @Inject
    SignupPresenter<SignupMvpView> mPresenter;

    public static SignupFragment newInstance() {
        Bundle args = new Bundle();
        SignupFragment fragment = new SignupFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.onboarding_fragment_signup, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
            signInLink.setTypeface(null, Typeface.BOLD);
            agreePolicyLink.setTypeface(null, Typeface.BOLD);
            agreeTermsLink.setTypeface(null, Typeface.BOLD);

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

    @OnClick(R.id.CalSocialButton)
    void onAgreeContinueClicked() {
        mPresenter.onAgreeContinueClick(firstName.getEditText().getText().toString().trim(), lastName.getEditText().getText().toString().trim(), phone.getEditText().getText().toString().trim(), TAG + "-" + AppConstants.PROCEED_TO_PHONE_VERIFICATION_SIGNUP);
    }

    @OnClick(R.id.signInLink)
    void onSigninClicked() {
        mPresenter.onSigninClicked();
    }

    @OnClick(R.id.agreeTermsLink)
    void onTermsLinkClicked() {
        mPresenter.onTermsLinkClicked();

    }

    @OnClick(R.id.agreePolicyLink)
    void onPolicyLinkClicked() {
        mPresenter.onPolicyLinkClicked();
    }

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
    public void openTermsLink() {
        String url = AppConstants.TERMS_LINK;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    @Override
    public void openPrivacyPolicyLink() {
        String url = AppConstants.POLICY_LINK;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    @Override
    public void openSignin() {
        onFragmentDetached(TAG + "-" + AppConstants.OPEN_LOGIN);
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