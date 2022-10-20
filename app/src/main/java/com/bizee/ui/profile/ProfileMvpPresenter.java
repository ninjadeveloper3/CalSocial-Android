package com.CalSocial.ui.profile;

import com.CalSocial.ui.base.MvpPresenter;

public interface ProfileMvpPresenter<V extends ProfileMvpView> extends MvpPresenter<V> {

    void onScreenCreated(String screenToOpen);

    void showContactProfileFragment();

    void showSwarmProfileFragment();

    void showViewHiveFragment();

    void showEditSwarmFragment();

    void showEditProfileFragment();

    void showYourProfileFragment();

    void showSettingsOptionsFragment();

    void showSettingsCalSocialSuggestionsFragment();

    void showSettingsSyncCalendarsFragment();

    void showSettingsSetYourPrivacy();

    void showSettingsHiveConnections();

    void showSettingsBlockedUsers();

    void showSettingsContactUs();

    void showSettingsNotifications();

    void showSettingsContactUsReport();

    void onGoBackPressed();

    void onCancelPressed();

    void onSaveClicked();
}