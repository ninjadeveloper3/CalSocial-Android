package com.CalSocial.ui.homeMain.contactsHiveFragment;


import com.CalSocial.ui.base.MvpPresenter;

public interface ContactsHiveMvpPresenter<V extends ContactsHiveMvpView> extends MvpPresenter<V> {

    void onContactsHiveRVViewPrepared();
}