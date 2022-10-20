package com.CalSocial.ui.onboarding;

import com.CalSocial.ui.base.MvpPresenter;

public interface OnboardingMvpPresenter<V extends OnboardingMvpView> extends MvpPresenter<V> {

    void onScreenCreated();

    void showSignUpFragment();

    void showPhoneCodeFragment();

    void showAdditionalInfoFragment();

    void showContactsSyncFragment();

    void showSigninFragment();

    void showLandingPageFragment();

    void showContactsFoundFragment();

    void showCalenderSyncFragment();

    void openHomeActivity();

    void showCreateFirstEventFragment();
}