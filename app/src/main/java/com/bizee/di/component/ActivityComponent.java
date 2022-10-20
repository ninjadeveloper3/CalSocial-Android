/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.CalSocial.di.component;

import com.CalSocial.di.PerActivity;
import com.CalSocial.di.module.ActivityModule;
import com.CalSocial.ui.dialog.alert.AlertDialog;
import com.CalSocial.ui.dialog.confirm.ConfirmDialog;
import com.CalSocial.ui.events.EventsActivity;
import com.CalSocial.ui.events.addGuest.AddGuestFragment;
import com.CalSocial.ui.events.createEvent.CreateEventFragment;
import com.CalSocial.ui.events.eventCreatedDialog.EventCreatedDialog;
import com.CalSocial.ui.events.eventDetail.EventDetailDetailFragment;
import com.CalSocial.ui.events.eventsMoreMenuDialog.EventsMoreMenuDialog;
import com.CalSocial.ui.events.selectDateTime.SelectDateTimeEventFragment;
import com.CalSocial.ui.events.selectTimeSuggestions.SelectTimeSuggestionsEventFragment;
import com.CalSocial.ui.homeMain.HomeMainActivity;
import com.CalSocial.ui.homeMain.contacts.ContactsFragment;
import com.CalSocial.ui.homeMain.contactsCalSocialNetworkFragment.ContactsCalSocialNetworkFragment;
import com.CalSocial.ui.homeMain.contactsCreateMessagesSelectMembersFragment.ContactsCreateMessagesSelectMembersFragment;
import com.CalSocial.ui.homeMain.contactsCreateSwarmFragment.ContactsCreateSwarmFragment;
import com.CalSocial.ui.homeMain.contactsCreateSwarmSelectMembersFragment.ContactsCreateSwarmSelectMembersFragment;
import com.CalSocial.ui.homeMain.contactsHiveFragment.ContactsHiveFragment;
import com.CalSocial.ui.homeMain.contactsPhoneFragment.ContactsPhoneFragment;
import com.CalSocial.ui.homeMain.contactsSwarmFragment.ContactsSwarmFragment;
import com.CalSocial.ui.homeMain.home.HomeFragment;
import com.CalSocial.ui.homeMain.messages.MessagesFragment;
import com.CalSocial.ui.homeMain.messagesChats.MessagesChatFragment;
import com.CalSocial.ui.homeMain.messagesChatsOneToMany.MessagesChatOMFragment;
import com.CalSocial.ui.homeMain.messagesDetails.MessagesDetailsFragment;
import com.CalSocial.ui.homeMain.notifications.NotificationsFragment;
import com.CalSocial.ui.onboarding.OnboardingActivity;
import com.CalSocial.ui.onboarding.additionalInfo.SignupAdditionalInfoFragment;
import com.CalSocial.ui.onboarding.calendarSync.CalendarSyncFragment;
import com.CalSocial.ui.onboarding.contactsFound.ContactsFoundFragment;
import com.CalSocial.ui.onboarding.contactsSync.ContactsSyncFragment;
import com.CalSocial.ui.onboarding.createFirstEvent.CreateFirstEventFragment;
import com.CalSocial.ui.onboarding.landing.LandingFragment;
import com.CalSocial.ui.onboarding.phoneCode.PhoneCodeFragment;
import com.CalSocial.ui.onboarding.signIn.SigninFragment;
import com.CalSocial.ui.onboarding.signUp.SignupFragment;
import com.CalSocial.ui.profile.ProfileActivity;
import com.CalSocial.ui.profile.contactProfile.ContactProfileFragment;
import com.CalSocial.ui.profile.dialog.MoreMenuDialog;
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
import com.CalSocial.ui.splash.SplashActivity;

import dagger.Component;


@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {


    void inject(SplashActivity activity);

    void inject(SignupFragment fragment);

    void inject(SignupAdditionalInfoFragment fragment);

    void inject(PhoneCodeFragment fragment);

    void inject(ContactsSyncFragment fragment);

    void inject(CalendarSyncFragment fragment);

    void inject(OnboardingActivity activity);

    void inject(HomeMainActivity activity);

    void inject(SigninFragment fragment);

    void inject(LandingFragment fragment);

    void inject(CreateFirstEventFragment fragment);

    void inject(ContactsFoundFragment fragment);

    void inject(ProfileActivity activity);

    void inject(ContactProfileFragment fragment);

    void inject(EditProfileFragment fragment);

    void inject(EditSwarmFragment fragment);

    void inject(SwarmProfileFragment fragment);

    void inject(ViewHiveFragment fragment);

    void inject(YourProfileFragment fragment);

    void inject(MoreMenuDialog dialog);

    void inject(EventsActivity activity);

    void inject(CreateEventFragment fragment);

    void inject(EventDetailDetailFragment fragment);

    void inject(EventsMoreMenuDialog dialog);

    void inject(AddGuestFragment fragment);

    void inject(SettingsOptionsFragment fragment);

    void inject(NotificationsFragment fragment);

    void inject(HomeFragment fragment);

    void inject(MessagesFragment fragment);

    void inject(ContactsFragment fragment);

    void inject(SettingsCalSocialSuggestionsFragment fragment);

    void inject(SettingsSyncCalendarsFragment fragment);

    void inject(ContactsHiveFragment fragment);

    void inject(ContactsSwarmFragment fragment);

    void inject(ContactsCreateSwarmFragment fragment);

    void inject(ContactsCreateSwarmSelectMembersFragment fragment);

    void inject(ContactsPhoneFragment fragment);

    void inject(ContactsCalSocialNetworkFragment fragment);

    void inject(ContactsCreateMessagesSelectMembersFragment fragment);

    void inject(SelectDateTimeEventFragment fragment);

    void inject(SelectTimeSuggestionsEventFragment fragment);

    void inject(EventCreatedDialog dialog);

    void inject(MessagesChatFragment fragment);

    void inject(MessagesChatOMFragment fragment);

    void inject(MessagesDetailsFragment fragment);

    void inject(AlertDialog dialog);

    void inject(ConfirmDialog dialog);

    void inject(SettingsSetYourPrivacyFragment fragment);

    void inject(SettingsHiveConnectionsFragment fragment);

    void inject(SettingsBlockedUsersFragment fragment);

    void inject(SettingsContactUsFragment fragment);

    void inject(SettingsNotificationsFragment fragment);

    void inject(SettingsContactUsReportFragment fragment);
}
