package com.CalSocial.ui.onboarding.contactsFound;

import com.CalSocial.ui.base.MvpPresenter;

public interface ContactsFoundMvpPresenter<V extends ContactsFoundMvpView> extends MvpPresenter<V> {

    void onSkipForNowClicked(String TAG);

    void onViewPrepared();

}