package com.CalSocial.ui.onboarding.contactsSync;

import android.content.Context;

import com.CalSocial.ui.base.MvpPresenter;

public interface ContactsSyncMvpPresenter<V extends ContactsSyncMvpView> extends MvpPresenter<V> {

    void onSyncContactsClicked(String TAG, Context context);

    void onSkipForNowClicked(String TAG);

    void startContactsSyncing();

    void alertUser(Context context);

}