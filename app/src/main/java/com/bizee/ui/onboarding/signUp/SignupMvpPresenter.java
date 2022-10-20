package com.CalSocial.ui.onboarding.signUp;


import com.CalSocial.ui.base.MvpPresenter;

public interface SignupMvpPresenter<V extends SignupMvpView> extends MvpPresenter<V> {

    void onAgreeContinueClick(String firstName, String lastName, String phone, String TAG);

    void onSigninClicked();

    void onTermsLinkClicked();

    void onPolicyLinkClicked();


}