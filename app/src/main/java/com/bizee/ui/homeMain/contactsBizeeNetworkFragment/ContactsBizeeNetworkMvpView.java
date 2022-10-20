package com.CalSocial.ui.homeMain.contactsCalSocialNetworkFragment;

import com.CalSocial.data.network.model.ContactsHiveResponse;
import com.CalSocial.ui.base.MvpView;

import java.util.List;

public interface ContactsCalSocialNetworkMvpView extends MvpView {
    void onFragmentDetached(String TAG);

    void updateSwarmNotificationsRepo(List<ContactsHiveResponse.User> userList);

}