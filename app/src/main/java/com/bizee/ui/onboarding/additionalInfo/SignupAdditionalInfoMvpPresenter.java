package com.CalSocial.ui.onboarding.additionalInfo;


import com.CalSocial.ui.base.MvpPresenter;

public interface SignupAdditionalInfoMvpPresenter<V extends SignupAdditionalInfoMvpView> extends MvpPresenter<V> {

    void onSaveContinueClicked(String location, String bio, String TAG);

    void onSkipForNowClicked(String TAG);
}