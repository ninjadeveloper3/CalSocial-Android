package com.CalSocial.ui.homeMain.contactsCreateSwarmSelectMembersFragment;

import com.CalSocial.data.network.model.ContactsHiveResponse;
import com.CalSocial.ui.base.MvpView;

import java.util.List;

public interface ContactsCreateSwarmSelectMembersMvpView extends MvpView {

    void onFragmentDetached(String TAG);

    void updateMembersRepo(List<ContactsHiveResponse.User> userList);
}