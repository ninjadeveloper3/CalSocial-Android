package com.CalSocial.ui.homeMain;

import com.CalSocial.data.DataManager;
import com.CalSocial.ui.base.BasePresenter;
import com.CalSocial.ui.events.createEvent.CreateEventFragment;
import com.CalSocial.ui.homeMain.contacts.ContactsFragment;
import com.CalSocial.ui.homeMain.contactsCreateMessagesSelectMembersFragment.ContactsCreateMessagesSelectMembersFragment;
import com.CalSocial.ui.homeMain.contactsCreateSwarmFragment.ContactsCreateSwarmFragment;
import com.CalSocial.ui.homeMain.contactsCreateSwarmSelectMembersFragment.ContactsCreateSwarmSelectMembersFragment;
import com.CalSocial.ui.homeMain.home.HomeFragment;
import com.CalSocial.ui.homeMain.messages.MessagesFragment;
import com.CalSocial.ui.homeMain.messagesChats.MessagesChatFragment;
import com.CalSocial.ui.homeMain.messagesChatsOneToMany.MessagesChatOMFragment;
import com.CalSocial.ui.homeMain.messagesDetails.MessagesDetailsFragment;
import com.CalSocial.ui.homeMain.notifications.NotificationsFragment;
import com.CalSocial.ui.profile.contactProfile.ContactProfileFragment;
import com.CalSocial.ui.profile.swarmProfile.SwarmProfileFragment;
import com.CalSocial.utils.AppConstants;
import com.CalSocial.utils.AppUtils;
import com.CalSocial.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class HomeMainPresenter<V extends HomeMainMvpView> extends BasePresenter<V> implements HomeMainMvpPresenter<V> {


    @Inject
    public HomeMainPresenter(DataManager dataManager,
                             SchedulerProvider schedulerProvider,
                             CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void onScreenCreated(String screenToOpen) {

        AppUtils.sourceScreenFragment = HomeFragment.TAG;

        if (screenToOpen.compareToIgnoreCase(AppConstants.MESSAGES_ONE_TO_ONE_FRAGMENT) == 0) {
            AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, MessagesChatFragment.TAG);
            getMvpView().showMessageChatFragment();
        } else if (screenToOpen.compareToIgnoreCase(AppConstants.MESSAGES_ONE_TO_MANY_FRAGMENT) == 0) {
            AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, MessagesChatOMFragment.TAG);
            getMvpView().showMessageChatOneToManyFragment();
        } else if (screenToOpen.compareToIgnoreCase(AppConstants.HOME_FRAGMENT) == 0) {
            AppUtils.updateSourceDestination(AppUtils.sourceScreenFragment, HomeFragment.TAG);
            getMvpView().showHomeFragment();
        }

    }


    @Override
    public void showHomeFragment() {

        AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, HomeFragment.TAG);
        getMvpView().showHomeFragment();
    }

    @Override
    public void showNotificationsFragment() {
        AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, NotificationsFragment.TAG);
        getMvpView().showNotificationsFragment();
    }

    @Override
    public void showContactsFragment() {
        AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, ContactsFragment.TAG);
        getMvpView().showContactsFragment();
    }


    @Override
    public void showContactsCreateSwarmFragment() {
        AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, ContactsCreateSwarmFragment.TAG);
        getMvpView().showContactsCreateSwarmFragment();
    }

    @Override
    public void showContactsCreateSwarmSelectMembersFragment() {
        AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, ContactsCreateSwarmSelectMembersFragment.TAG);
        getMvpView().showContactsCreateSwarmSelectMembersFragment();
    }


    @Override
    public void showMessagesFragment() {
        AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, MessagesFragment.TAG);
        getMvpView().showMessagesFragment();
    }

    @Override
    public void showMessagesAddContactsFragment() {
        AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, ContactsCreateMessagesSelectMembersFragment.TAG);
        getMvpView().showMessagesAddContactsFragment();
    }

    @Override
    public void showMessageChatFragment() {
        AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, MessagesChatFragment.TAG);
        getMvpView().showMessageChatFragment();
    }

    @Override
    public void showMessageChatOMFragment() {
        AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, MessagesChatOMFragment.TAG);
        getMvpView().showMessageChatOneToManyFragment();
    }

    @Override
    public void showMessageDetailsFragment() {
        AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, MessagesDetailsFragment.TAG);
        getMvpView().showMessageDetailsFragment();
    }

    @Override
    public void onGoBackClicked(String screen) {

        if (AppUtils.sourceScreenFragment.compareToIgnoreCase(ContactsFragment.TAG) == 0 && AppUtils.destinationScreenFragment.compareToIgnoreCase(MessagesChatFragment.TAG) == 0) {
            AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, ContactsFragment.TAG);
            getMvpView().showContactsFragment();
        } else if (AppUtils.sourceScreenFragment.compareToIgnoreCase(ContactsFragment.TAG) == 0 && AppUtils.destinationScreenFragment.compareToIgnoreCase(MessagesChatOMFragment.TAG) == 0) {
            AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, ContactsFragment.TAG);
            getMvpView().showContactsFragment();
        } else if (AppUtils.sourceScreenFragment.compareToIgnoreCase(MessagesFragment.TAG) == 0 && AppUtils.destinationScreenFragment.compareToIgnoreCase(MessagesChatFragment.TAG) == 0) {
            AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, MessagesFragment.TAG);
            getMvpView().showMessagesFragment();
        } else if (AppUtils.sourceScreenFragment.compareToIgnoreCase(MessagesFragment.TAG) == 0 && AppUtils.destinationScreenFragment.compareToIgnoreCase(MessagesChatOMFragment.TAG) == 0) {
            AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, MessagesFragment.TAG);
            getMvpView().showMessagesFragment();
        } else if (AppUtils.sourceScreenFragment.compareToIgnoreCase(MessagesChatOMFragment.TAG) == 0 && AppUtils.destinationScreenFragment.compareToIgnoreCase(MessagesDetailsFragment.TAG) == 0) {
            AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, MessagesChatOMFragment.TAG);
            getMvpView().showMessageChatOneToManyFragment();
        } else if (AppUtils.sourceScreenFragment.compareToIgnoreCase(MessagesDetailsFragment.TAG) == 0 && AppUtils.destinationScreenFragment.compareToIgnoreCase(MessagesChatOMFragment.TAG) == 0) {
            AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, MessagesFragment.TAG);
            getMvpView().showMessagesFragment();
        } else if (AppUtils.sourceScreenFragment.compareToIgnoreCase(ContactProfileFragment.TAG) == 0 && AppUtils.destinationScreenFragment.compareToIgnoreCase(MessagesChatFragment.TAG) == 0) {
            AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, ContactProfileFragment.TAG);
            getMvpView().goBackToContactProfile();
        } else if (AppUtils.sourceScreenFragment.compareToIgnoreCase(SwarmProfileFragment.TAG) == 0 && AppUtils.destinationScreenFragment.compareToIgnoreCase(MessagesChatOMFragment.TAG) == 0) {
            AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, SwarmProfileFragment.TAG);
            getMvpView().goBackToSwarmProfile();
        } else {
            AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, HomeFragment.TAG);
            getMvpView().showHomeFragment();
        }

    }

    @Override
    public void onAddNewEventClicked() {
        AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, CreateEventFragment.TAG);
        getMvpView().openNewEvent();
    }


    @Override
    public void showYourProfile() {
        getMvpView().showYourProfile();
    }
}