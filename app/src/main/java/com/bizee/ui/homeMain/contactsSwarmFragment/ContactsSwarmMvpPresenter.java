package com.CalSocial.ui.homeMain.contactsSwarmFragment;


import com.CalSocial.ui.base.MvpPresenter;

public interface ContactsSwarmMvpPresenter<V extends ContactsSwarmMvpView> extends MvpPresenter<V> {

    void onContactsSwarmRVViewPrepared();

    void onCreateSwarmClicked(String TAG);
}