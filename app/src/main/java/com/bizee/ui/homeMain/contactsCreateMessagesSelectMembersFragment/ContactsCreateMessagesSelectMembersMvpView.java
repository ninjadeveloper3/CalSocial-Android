package com.CalSocial.ui.homeMain.contactsCreateMessagesSelectMembersFragment;

import com.CalSocial.data.network.model.ContactsHiveResponse;
import com.CalSocial.data.network.model.NewMessageSelectContactsResponse;
import com.CalSocial.ui.base.MvpView;

import java.util.List;

public interface ContactsCreateMessagesSelectMembersMvpView extends MvpView {

    void onFragmentDetached(String TAG);

    void updateMembersRepo(List<NewMessageSelectContactsResponse.User> userList);
}