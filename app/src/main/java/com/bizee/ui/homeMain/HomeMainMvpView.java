package com.CalSocial.ui.homeMain;


import com.CalSocial.ui.base.MvpView;

public interface HomeMainMvpView extends MvpView {

    void showHomeFragment();

    void showNotificationsFragment();

    void showContactsFragment();

    void showMessagesFragment();

    void showContactsCreateSwarmFragment();

    void showContactsCreateSwarmSelectMembersFragment();

    void openNewEvent();

    void showMessagesAddContactsFragment();

    void showMessageChatFragment();

    void showMessageChatOneToManyFragment();

    void showMessageDetailsFragment();

    void showContactProfile();

    void showSwarmProfile();

    void goBackToContactProfile();

    void goBackToSwarmProfile();

    void showYourProfile();

}