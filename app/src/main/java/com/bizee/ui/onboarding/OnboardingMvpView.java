package com.CalSocial.ui.onboarding;

import com.CalSocial.ui.base.MvpView;

public interface OnboardingMvpView extends MvpView {

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