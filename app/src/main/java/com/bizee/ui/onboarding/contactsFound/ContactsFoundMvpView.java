package com.CalSocial.ui.onboarding.contactsFound;

import com.CalSocial.data.network.model.ContactsFoundResponse;
import com.CalSocial.ui.base.MvpView;

import java.util.List;

public interface ContactsFoundMvpView extends MvpView {

    void onFragmentDetached(String TAG);

    void updateRepo(List<ContactsFoundResponse.User> userList);

}