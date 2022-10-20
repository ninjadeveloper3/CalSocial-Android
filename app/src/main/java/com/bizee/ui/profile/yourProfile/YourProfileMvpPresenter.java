package com.CalSocial.ui.profile.yourProfile;


import com.CalSocial.ui.base.MvpPresenter;

public interface YourProfileMvpPresenter<V extends YourProfileMvpView> extends MvpPresenter<V> {
    void getProfileDetails();

    void showSettingsOptionsFragment(String TAG);

}