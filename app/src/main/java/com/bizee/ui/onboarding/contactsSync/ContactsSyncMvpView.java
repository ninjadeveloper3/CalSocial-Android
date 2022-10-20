package com.CalSocial.ui.onboarding.contactsSync;

import com.CalSocial.ui.base.MvpView;

public interface ContactsSyncMvpView extends MvpView {

    void onFragmentDetached(String TAG);

    void startSyncContacts(String accessToken);

    void informUserOfContactsSyncDialog(String title, String message);

    void alertUserDialog(String title, String message);


}