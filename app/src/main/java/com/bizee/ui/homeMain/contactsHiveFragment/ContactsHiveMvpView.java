package com.CalSocial.ui.homeMain.contactsHiveFragment;

import com.CalSocial.data.network.model.ContactsHiveResponse;
import com.CalSocial.ui.base.MvpView;

import java.util.List;

public interface ContactsHiveMvpView extends MvpView {

    void onFragmentDetached(String TAG);

    void updateHiveNotificationsRepo(List<ContactsHiveResponse.User> userList);
}