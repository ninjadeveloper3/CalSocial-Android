package com.CalSocial.ui.homeMain.contactsPhoneFragment;


import android.content.Context;

import com.CalSocial.ui.base.MvpPresenter;

public interface ContactsPhoneMvpPresenter<V extends ContactsPhoneMvpView> extends MvpPresenter<V> {

    void onContactsSwarmRVViewPrepared(Context context);

    void onCreateSwarmClicked(String TAG);
}