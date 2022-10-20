package com.CalSocial.ui.homeMain;


import com.CalSocial.ui.base.MvpPresenter;

public interface HomeMainMvpPresenter<V extends HomeMainMvpView> extends MvpPresenter<V> {

    void onScreenCreated(String screenToOpen);

    void showHomeFragment();

    void showNotificationsFragment();

    void showContactsFragment();

    void showContactsCreateSwarmFragment();

    void showContactsCreateSwarmSelectMembersFragment();

    void showMessagesFragment();

    void showMessagesAddContactsFragment();

    void showMessageChatFragment();

    void showMessageChatOMFragment();

    void showMessageDetailsFragment();

    void onAddNewEventClicked();

    void onGoBackClicked(String screen);

    void showYourProfile();

}