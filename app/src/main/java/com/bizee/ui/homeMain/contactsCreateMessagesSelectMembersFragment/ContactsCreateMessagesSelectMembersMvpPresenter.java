package com.CalSocial.ui.homeMain.contactsCreateMessagesSelectMembersFragment;


import com.CalSocial.ui.base.MvpPresenter;

public interface ContactsCreateMessagesSelectMembersMvpPresenter<V extends ContactsCreateMessagesSelectMembersMvpView> extends MvpPresenter<V> {

    void onContactsHiveRVViewPrepared();

    void onAddMemberClicked(String TAG);
}