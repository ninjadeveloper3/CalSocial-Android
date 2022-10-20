package com.CalSocial.ui.homeMain.contactsCreateSwarmSelectMembersFragment;


import com.CalSocial.ui.base.MvpPresenter;

public interface ContactsCreateSwarmSelectMembersMvpPresenter<V extends ContactsCreateSwarmSelectMembersMvpView> extends MvpPresenter<V> {

    void onContactsHiveRVViewPrepared();

    void onAddMemberClicked(String TAG);
}