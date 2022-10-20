package com.CalSocial.ui.onboarding.signIn;

import com.CalSocial.ui.base.MvpPresenter;

public interface SigninMvpPresenter<V extends SigninMvpView> extends MvpPresenter<V> {
    void onGoBackClicked(String TAG);

    void onContinueClicked(String phone, String TAG);

}