package com.CalSocial.ui.profile;

import com.CalSocial.ui.base.MvpView;

public interface ProfileMvpView extends MvpView {

    void showContactProfileFragment();

    void showSwarmProfileFragment();

    void showViewHiveFragment();

    void showEditSwarmFragment();

    void showEditProfileFragment();

    void showYourProfileFragment();

    void showSettingsOptionsFragment();

    void showSettingsCalSocialSuggestionsFragment();

    void showSettingsSyncCalendarFragment();

    void showSettingsSetYourPrivacyFragment();

    void showSettingsHiveConnectionsFragment();

    void showSettingsBlockedUsersFragment();

    void showSettingsContactUsFragment();

    void showSettingsNotificationsFragment();

    void showSettingsContactUsReportFragment();

    void showHome();
}