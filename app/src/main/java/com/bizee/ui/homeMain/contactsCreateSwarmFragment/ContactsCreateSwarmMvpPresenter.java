package com.CalSocial.ui.homeMain.contactsCreateSwarmFragment;


import com.CalSocial.ui.base.MvpPresenter;

public interface ContactsCreateSwarmMvpPresenter<V extends ContactsCreateSwarmMvpView> extends MvpPresenter<V> {

    void onContactsHiveRVViewPrepared();

    void onAddMemberClicked(String TAG);
}