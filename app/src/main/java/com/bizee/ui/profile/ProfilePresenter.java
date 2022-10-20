package com.CalSocial.ui.profile;

import com.CalSocial.data.DataManager;
import com.CalSocial.ui.base.BasePresenter;
import com.CalSocial.ui.homeMain.home.HomeFragment;
import com.CalSocial.ui.homeMain.messagesChats.MessagesChatFragment;
import com.CalSocial.ui.homeMain.messagesChatsOneToMany.MessagesChatOMFragment;
import com.CalSocial.ui.profile.contactProfile.ContactProfileFragment;
import com.CalSocial.ui.profile.editProfile.EditProfileFragment;
import com.CalSocial.ui.profile.editSwarm.EditSwarmFragment;
import com.CalSocial.ui.profile.settings.settingsCalSocialSuggestions.SettingsCalSocialSuggestionsFragment;
import com.CalSocial.ui.profile.settings.settingsBlockedUsers.SettingsBlockedUsersFragment;
import com.CalSocial.ui.profile.settings.settingsContactUs.SettingsContactUsFragment;
import com.CalSocial.ui.profile.settings.settingsContactUsReport.SettingsContactUsReportFragment;
import com.CalSocial.ui.profile.settings.settingsHiveConnections.SettingsHiveConnectionsFragment;
import com.CalSocial.ui.profile.settings.settingsNotifications.SettingsNotificationsFragment;
import com.CalSocial.ui.profile.settings.settingsOptions.SettingsOptionsFragment;
import com.CalSocial.ui.profile.settings.settingsSetYourPrivacy.SettingsSetYourPrivacyFragment;
import com.CalSocial.ui.profile.settings.settingsSyncCalendars.SettingsSyncCalendarsFragment;
import com.CalSocial.ui.profile.swarmProfile.SwarmProfileFragment;
import com.CalSocial.ui.profile.viewHive.ViewHiveFragment;
import com.CalSocial.ui.profile.yourProfile.YourProfileFragment;
import com.CalSocial.utils.AppConstants;
import com.CalSocial.utils.AppUtils;
import com.CalSocial.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class ProfilePresenter<V extends ProfileMvpView> extends BasePresenter<V> implements ProfileMvpPresenter<V> {


    @Inject
    public ProfilePresenter(DataManager dataManager,
                            SchedulerProvider schedulerProvider,
                            CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onScreenCreated(String screenToOpen) {

        if (screenToOpen.compareToIgnoreCase(AppConstants.CONTACT_PROFILE_FRAGMENT) == 0) {
            AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, ContactProfileFragment.TAG);
            getMvpView().showContactProfileFragment();

        } else if (screenToOpen.compareToIgnoreCase(AppConstants.EDIT_PROFILE_FRAGMENT) == 0) {
            AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, EditProfileFragment.TAG);
            getMvpView().showEditProfileFragment();

        } else if (screenToOpen.compareToIgnoreCase(AppConstants.EDIT_SWARM_FRAGMENT) == 0) {
            AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, EditSwarmFragment.TAG);
            getMvpView().showEditSwarmFragment();

        } else if (screenToOpen.compareToIgnoreCase(AppConstants.SWARM_PROFILE_FRAGMENT) == 0) {
            AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, SwarmProfileFragment.TAG);
            getMvpView().showSwarmProfileFragment();

        } else if (screenToOpen.compareToIgnoreCase(AppConstants.VIEW_HIVE_FRAGMENT) == 0) {
            AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, ViewHiveFragment.TAG);
            getMvpView().showViewHiveFragment();

        } else if (screenToOpen.compareToIgnoreCase(AppConstants.YOUR_PROFILE_FRAGMENT) == 0) {
            AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, YourProfileFragment.TAG);
            getMvpView().showYourProfileFragment();

        }
    }

    @Override
    public void showContactProfileFragment() {
        AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, ContactProfileFragment.TAG);
        getMvpView().showContactProfileFragment();
    }

    @Override
    public void showSwarmProfileFragment() {
        AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, SwarmProfileFragment.TAG);
        getMvpView().showSwarmProfileFragment();
    }

    @Override
    public void showViewHiveFragment() {
        AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, ViewHiveFragment.TAG);
        getMvpView().showViewHiveFragment();

    }

    @Override
    public void showEditSwarmFragment() {
        AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, EditSwarmFragment.TAG);
        getMvpView().showEditSwarmFragment();
    }

    @Override
    public void showEditProfileFragment() {
        AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, EditProfileFragment.TAG);
        getMvpView().showEditProfileFragment();
    }

    @Override
    public void showYourProfileFragment() {
        AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, YourProfileFragment.TAG);
        getMvpView().showYourProfileFragment();

    }

    @Override
    public void showSettingsOptionsFragment() {
        AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, SettingsOptionsFragment.TAG);
        getMvpView().showSettingsOptionsFragment();
    }

    @Override
    public void showSettingsCalSocialSuggestionsFragment() {
        AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, SettingsCalSocialSuggestionsFragment.TAG);
        getMvpView().showSettingsCalSocialSuggestionsFragment();
    }

    @Override
    public void showSettingsSyncCalendarsFragment() {
        AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, SettingsSyncCalendarsFragment.TAG);
        getMvpView().showSettingsSyncCalendarFragment();
    }

    @Override
    public void showSettingsSetYourPrivacy() {
        AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, SettingsSetYourPrivacyFragment.TAG);
        getMvpView().showSettingsSetYourPrivacyFragment();
    }

    @Override
    public void showSettingsHiveConnections() {
        AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, SettingsHiveConnectionsFragment.TAG);
        getMvpView().showSettingsHiveConnectionsFragment();
    }

    @Override
    public void showSettingsBlockedUsers() {
        AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, SettingsBlockedUsersFragment.TAG);
        getMvpView().showSettingsBlockedUsersFragment();
    }

    @Override
    public void showSettingsContactUs() {
        AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, SettingsContactUsFragment.TAG);
        getMvpView().showSettingsContactUsFragment();
    }

    @Override
    public void showSettingsNotifications() {
        AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, SettingsNotificationsFragment.TAG);
        getMvpView().showSettingsNotificationsFragment();
    }

    @Override
    public void showSettingsContactUsReport() {
        AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, SettingsContactUsReportFragment.TAG);
        getMvpView().showSettingsContactUsReportFragment();
    }

    @Override
    public void onGoBackPressed() {

        if (AppUtils.sourceScreenFragment.compareToIgnoreCase(ContactProfileFragment.TAG) == 0 && AppUtils.destinationScreenFragment.compareToIgnoreCase(MessagesChatFragment.TAG) == 0) {
            AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, ContactProfileFragment.TAG);
            getMvpView().showContactProfileFragment();
        } else if (AppUtils.sourceScreenFragment.compareToIgnoreCase(SwarmProfileFragment.TAG) == 0 && AppUtils.destinationScreenFragment.compareToIgnoreCase(MessagesChatOMFragment.TAG) == 0) {
            AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, SwarmProfileFragment.TAG);
            getMvpView().showSwarmProfileFragment();
        } else if (AppUtils.sourceScreenFragment.compareToIgnoreCase(SettingsOptionsFragment.TAG) == 0
                && (AppUtils.destinationScreenFragment.compareToIgnoreCase(SettingsSyncCalendarsFragment.TAG) == 0
                || AppUtils.destinationScreenFragment.compareToIgnoreCase(SettingsCalSocialSuggestionsFragment.TAG) == 0
                || AppUtils.destinationScreenFragment.compareToIgnoreCase(SettingsSetYourPrivacyFragment.TAG) == 0
                || AppUtils.destinationScreenFragment.compareToIgnoreCase(SettingsContactUsFragment.TAG) == 0
                || AppUtils.destinationScreenFragment.compareToIgnoreCase(SettingsNotificationsFragment.TAG) == 0)) {
            AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, SettingsOptionsFragment.TAG);
            getMvpView().showSettingsOptionsFragment();
        } else if (AppUtils.sourceScreenFragment.compareToIgnoreCase(SettingsSetYourPrivacyFragment.TAG) == 0
                && (AppUtils.destinationScreenFragment.compareToIgnoreCase(SettingsHiveConnectionsFragment.TAG) == 0
                || AppUtils.destinationScreenFragment.compareToIgnoreCase(SettingsBlockedUsersFragment.TAG) == 0)) {
            AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, SettingsSetYourPrivacyFragment.TAG);
            getMvpView().showSettingsSetYourPrivacyFragment();
        } else if (AppUtils.sourceScreenFragment.compareToIgnoreCase(SettingsContactUsFragment.TAG) == 0 && AppUtils.destinationScreenFragment.compareToIgnoreCase(SettingsContactUsReportFragment.TAG) == 0) {
            AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, SettingsContactUsFragment.TAG);
            getMvpView().showSettingsContactUsFragment();
        } else if (AppUtils.sourceScreenFragment.compareToIgnoreCase(YourProfileFragment.TAG) == 0 && AppUtils.destinationScreenFragment.compareToIgnoreCase(EditProfileFragment.TAG) == 0) {
            AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, YourProfileFragment.TAG);
            getMvpView().showYourProfileFragment();
        } else if (AppUtils.sourceScreenFragment.compareToIgnoreCase(YourProfileFragment.TAG) == 0 && AppUtils.destinationScreenFragment.compareToIgnoreCase(SettingsOptionsFragment.TAG) == 0) {
            AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, YourProfileFragment.TAG);
            getMvpView().showYourProfileFragment();
        } else if ((
                AppUtils.sourceScreenFragment.compareToIgnoreCase(SettingsSyncCalendarsFragment.TAG) == 0
                        || AppUtils.sourceScreenFragment.compareToIgnoreCase(SettingsCalSocialSuggestionsFragment.TAG) == 0
                        || AppUtils.sourceScreenFragment.compareToIgnoreCase(SettingsSetYourPrivacyFragment.TAG) == 0
                        || AppUtils.sourceScreenFragment.compareToIgnoreCase(SettingsContactUsFragment.TAG) == 0)
                && AppUtils.destinationScreenFragment.compareToIgnoreCase(SettingsOptionsFragment.TAG) == 0) {
            AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, YourProfileFragment.TAG);
            getMvpView().showYourProfileFragment();
        } else {
            AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, HomeFragment.TAG);
            getMvpView().showHome();

        }

    }

    @Override
    public void onCancelPressed() {
        if (AppUtils.sourceScreenFragment.compareToIgnoreCase(SwarmProfileFragment.TAG) == 0 && AppUtils.destinationScreenFragment.compareToIgnoreCase(EditSwarmFragment.TAG) == 0) {
            AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, EditSwarmFragment.TAG);
            getMvpView().showSwarmProfileFragment();
        } else if (AppUtils.sourceScreenFragment.compareToIgnoreCase(YourProfileFragment.TAG) == 0 && AppUtils.destinationScreenFragment.compareToIgnoreCase(EditProfileFragment.TAG) == 0) {
            AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, YourProfileFragment.TAG);
            getMvpView().showYourProfileFragment();
        }
    }

    @Override
    public void onSaveClicked() {
        if (AppUtils.sourceScreenFragment.compareToIgnoreCase(SwarmProfileFragment.TAG) == 0 && AppUtils.destinationScreenFragment.compareToIgnoreCase(EditSwarmFragment.TAG) == 0) {
            AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, EditSwarmFragment.TAG);
            getMvpView().showSwarmProfileFragment();
        } else if (AppUtils.sourceScreenFragment.compareToIgnoreCase(YourProfileFragment.TAG) == 0 && AppUtils.destinationScreenFragment.compareToIgnoreCase(EditProfileFragment.TAG) == 0) {
            AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, YourProfileFragment.TAG);
            getMvpView().showYourProfileFragment();
        }
    }
}