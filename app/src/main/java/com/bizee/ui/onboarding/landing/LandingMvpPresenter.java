package com.CalSocial.ui.onboarding.landing;

import com.CalSocial.ui.base.MvpPresenter;

public interface LandingMvpPresenter<V extends LandingMvpView> extends MvpPresenter<V> {
    void onSigninClicked(String TAG);

    void onSignupClicked(String TAG);

}