package com.CalSocial.ui.homeMain.contactsCalSocialNetworkFragment;


import com.CalSocial.ui.base.MvpPresenter;

public interface ContactsCalSocialNetworkMvpPresenter<V extends ContactsCalSocialNetworkMvpView> extends MvpPresenter<V> {

    void onContactsSwarmRVViewPrepared();

    void onCreateSwarmClicked(String TAG);
}