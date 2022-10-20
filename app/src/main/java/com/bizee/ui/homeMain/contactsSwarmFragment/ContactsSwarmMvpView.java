package com.CalSocial.ui.homeMain.contactsSwarmFragment;

import com.CalSocial.data.network.model.ContactsSwarmResponse;
import com.CalSocial.ui.base.MvpView;

import java.util.List;

public interface ContactsSwarmMvpView extends MvpView {
    void onFragmentDetached(String TAG);

    void updateSwarmNotificationsRepo(List<ContactsSwarmResponse.Swarm> userList);

}