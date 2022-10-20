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

package com.CalSocial.di.module;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.CalSocial.data.network.model.CommentsResponse;
import com.CalSocial.data.network.model.ContactsFoundResponse;
import com.CalSocial.data.network.model.ContactsHiveResponse;
import com.CalSocial.data.network.model.ContactsSwarmResponse;
import com.CalSocial.data.network.model.EventAttendeesResponse;
import com.CalSocial.data.network.model.EventsHomeResponse;
import com.CalSocial.data.network.model.MessagesResponse;
import com.CalSocial.data.network.model.NewMessageSelectContactsResponse;
import com.CalSocial.data.network.model.NotificationsResponse;
import com.CalSocial.data.network.model.TimeSuggestionsResponse;
import com.CalSocial.data.network.model.UsersResponse;
import com.CalSocial.di.ActivityContext;
import com.CalSocial.di.PerActivity;
import com.CalSocial.ui.dialog.alert.AlertDialogMvpPresenter;
import com.CalSocial.ui.dialog.alert.AlertDialogMvpView;
import com.CalSocial.ui.dialog.alert.AlertDialogPresenter;
import com.CalSocial.ui.dialog.confirm.ConfirmDialogMvpPresenter;
import com.CalSocial.ui.dialog.confirm.ConfirmDialogMvpView;
import com.CalSocial.ui.dialog.confirm.ConfirmDialogPresenter;
import com.CalSocial.ui.events.adapter.AddGuestAdapter;
import com.CalSocial.ui.events.adapter.AttendeesAdapter;
import com.CalSocial.ui.events.adapter.AttendeesAddGuestAdapter;
import com.CalSocial.ui.events.adapter.CalSocialTimeSuggesstionsAttendeesAdapter;
import com.CalSocial.ui.events.adapter.CommentsAdapter;
import com.CalSocial.ui.events.adapter.TimeSuggestionsAdapter;
import com.CalSocial.ui.events.eventCreatedDialog.EventCreatedDialogMvpPresenter;
import com.CalSocial.ui.events.eventCreatedDialog.EventCreatedDialogMvpView;
import com.CalSocial.ui.events.eventCreatedDialog.EventCreatedDialogPresenter;
import com.CalSocial.ui.events.eventsMoreMenuDialog.EventsMoreMenuDialogMvpPresenter;
import com.CalSocial.ui.events.eventsMoreMenuDialog.EventsMoreMenuDialogMvpView;
import com.CalSocial.ui.events.eventsMoreMenuDialog.EventsMoreMenuDialogPresenter;
import com.CalSocial.ui.homeMain.adapter.ContactsCalSocialNetworkAdapter;
import com.CalSocial.ui.homeMain.adapter.ContactsCreateNewMessageSelectMembersAdapter;
import com.CalSocial.ui.homeMain.adapter.ContactsCreateSwarmMembersAdapter;
import com.CalSocial.ui.homeMain.adapter.ContactsCreateSwarmSelectMembersAdapter;
import com.CalSocial.ui.homeMain.adapter.ContactsHiveAdapter;
import com.CalSocial.ui.homeMain.adapter.ContactsPagerAdapter;
import com.CalSocial.ui.homeMain.adapter.ContactsPhoneAdapter;
import com.CalSocial.ui.homeMain.adapter.ContactsSwarmAdapter;
import com.CalSocial.ui.homeMain.adapter.EventsAdapter;
import com.CalSocial.ui.homeMain.adapter.FavoritesAdapter;
import com.CalSocial.ui.homeMain.adapter.MessagesAdapter;
import com.CalSocial.ui.homeMain.adapter.NotificationsAdapter;
import com.CalSocial.ui.onboarding.contactsFound.adapter.ContactsFoundAdapter;
import com.CalSocial.ui.onboarding.landing.LandingPagerAdapter;
import com.CalSocial.ui.profile.adapter.EditSwarmMembersAdapter;
import com.CalSocial.ui.profile.adapter.HiveMembersAdapter;
import com.CalSocial.ui.profile.adapter.ProfileEventsAdapter;
import com.CalSocial.ui.profile.adapter.SwarmMembersAdapter;
import com.CalSocial.ui.profile.dialog.MoreMenuDialogMvpPresenter;
import com.CalSocial.ui.profile.dialog.MoreMenuDialogMvpView;
import com.CalSocial.ui.profile.dialog.MoreMenuDialogPresenter;
import com.CalSocial.ui.splash.SplashMvpPresenter;
import com.CalSocial.ui.splash.SplashMvpView;
import com.CalSocial.ui.splash.SplashPresenter;
import com.CalSocial.utils.rx.AppSchedulerProvider;
import com.CalSocial.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;


@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @PerActivity
    SplashMvpPresenter<SplashMvpView> provideSplashPresenter(
            SplashPresenter<SplashMvpView> presenter) {
        return presenter;
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }

    @Provides
    FavoritesAdapter provideFavoritesAdapter(AppCompatActivity activity) {
        return new FavoritesAdapter(new ArrayList<UsersResponse.User>(), activity);
    }

    @Provides
    LandingPagerAdapter provideLandingPagerAdapter(AppCompatActivity activity) {
        return new LandingPagerAdapter(activity);
    }

    @Provides
    ContactsFoundAdapter provideContactsFoundAdapter(AppCompatActivity activity) {
        return new ContactsFoundAdapter(new ArrayList<ContactsFoundResponse.User>(), activity);
    }

    @Provides
    EventsAdapter provideEventsAdapter(AppCompatActivity activity) {
        return new EventsAdapter(new ArrayList<EventsHomeResponse.Event>(), activity);
    }

    @Provides
    ProfileEventsAdapter provideProfileEventsAdapter(AppCompatActivity activity) {
        return new ProfileEventsAdapter(new ArrayList<EventsHomeResponse.Event>(), activity);
    }

    @Provides
    SwarmMembersAdapter provideSwarmMembersAdapter(AppCompatActivity activity) {
        return new SwarmMembersAdapter(new ArrayList<UsersResponse.User>(), activity);
    }

    @Provides
    EditSwarmMembersAdapter provideEditSwarmMembersAdapter(AppCompatActivity activity) {
        return new EditSwarmMembersAdapter(new ArrayList<UsersResponse.User>(), activity);
    }

    @Provides
    HiveMembersAdapter provideHiveMembersAdapter(AppCompatActivity activity) {
        return new HiveMembersAdapter(new ArrayList<UsersResponse.User>(), activity);
    }

    @Provides
    MoreMenuDialogMvpPresenter<MoreMenuDialogMvpView> provideMoreMenuPresenter(
            MoreMenuDialogPresenter<MoreMenuDialogMvpView> presenter) {
        return presenter;
    }

    @Provides
    EventsMoreMenuDialogMvpPresenter<EventsMoreMenuDialogMvpView> provideEventsMoreMenuPresenter(
            EventsMoreMenuDialogPresenter<EventsMoreMenuDialogMvpView> presenter) {
        return presenter;
    }

    @Provides
    AttendeesAdapter provideAttendeesAdapter(AppCompatActivity activity) {
        return new AttendeesAdapter(new ArrayList<EventAttendeesResponse.User>(), activity);
    }

    @Provides
    AttendeesAddGuestAdapter provideAttendeesAddGuestAdapter(AppCompatActivity activity) {
        return new AttendeesAddGuestAdapter(new ArrayList<EventAttendeesResponse.User>(), activity);
    }

    @Provides
    CommentsAdapter provideCommentsAdapter(AppCompatActivity activity) {
        return new CommentsAdapter(new ArrayList<CommentsResponse.Comment>(), activity);
    }

    @Provides
    AddGuestAdapter provideAddGuestAdapter(AppCompatActivity activity) {
        return new AddGuestAdapter(new ArrayList<UsersResponse.User>(), activity);
    }


    @Provides
    NotificationsAdapter provideNotificationsAdapter(AppCompatActivity activity) {
        return new NotificationsAdapter(new ArrayList<NotificationsResponse.Notification>(), activity);
    }

    @Provides
    ContactsPagerAdapter provideContactsPagerAdapter(AppCompatActivity activity) {
        return new ContactsPagerAdapter(activity.getSupportFragmentManager());
    }

    @Provides
    ContactsHiveAdapter provideContactsHiveAdapter(AppCompatActivity activity) {
        return new ContactsHiveAdapter(new ArrayList<ContactsHiveResponse.User>(), activity, 0);
    }

    @Provides
    ContactsSwarmAdapter provideContactsSwarmAdapter(AppCompatActivity activity) {
        return new ContactsSwarmAdapter(new ArrayList<ContactsSwarmResponse.Swarm>(), activity, 0);
    }

    @Provides
    ContactsCreateSwarmMembersAdapter provideContactsCreateSwarmAdapter(AppCompatActivity activity) {
        return new ContactsCreateSwarmMembersAdapter(new ArrayList<ContactsHiveResponse.User>(), activity);
    }

    @Provides
    ContactsCreateSwarmSelectMembersAdapter provideContactsCreateSwarmSelectMembersAdapter(AppCompatActivity activity) {
        return new ContactsCreateSwarmSelectMembersAdapter(new ArrayList<ContactsHiveResponse.User>(), activity);
    }

    @Provides
    ContactsPhoneAdapter provideContactsPhoneAdapter(AppCompatActivity activity) {
        return new ContactsPhoneAdapter(new ArrayList<ContactsHiveResponse.User>(), activity);
    }

    @Provides
    ContactsCalSocialNetworkAdapter provideContactsCalSocialNetworkAdapter(AppCompatActivity activity) {
        return new ContactsCalSocialNetworkAdapter(new ArrayList<ContactsHiveResponse.User>(), activity);
    }


    @Provides
    MessagesAdapter provideMessagesAdapter(AppCompatActivity activity) {
        return new MessagesAdapter(new ArrayList<MessagesResponse.Message>(), activity);
    }

    @Provides
    TimeSuggestionsAdapter provideTimeSuggestionsAdapter(AppCompatActivity activity) {
        return new TimeSuggestionsAdapter(new ArrayList<TimeSuggestionsResponse.Suggestion>(), activity);
    }

    @Provides
    CalSocialTimeSuggesstionsAttendeesAdapter provideCalSocialTimeSuggestionAttendeesAdapter(AppCompatActivity activity) {
        return new CalSocialTimeSuggesstionsAttendeesAdapter(new ArrayList<EventAttendeesResponse.User>(), activity);
    }


    @Provides
    EventCreatedDialogMvpPresenter<EventCreatedDialogMvpView> provideEventCreatedDialogPresenter(
            EventCreatedDialogPresenter<EventCreatedDialogMvpView> presenter) {
        return presenter;
    }

    @Provides
    AlertDialogMvpPresenter<AlertDialogMvpView> provideAlertsDialogPresenter(
            AlertDialogPresenter<AlertDialogMvpView> presenter) {
        return presenter;
    }

    @Provides
    ConfirmDialogMvpPresenter<ConfirmDialogMvpView> provideConfirmDialogPresenter(
            ConfirmDialogPresenter<ConfirmDialogMvpView> presenter) {
        return presenter;
    }

    @Provides
    ContactsCreateNewMessageSelectMembersAdapter provideContactsCreateNewMessageSelectMembersAdapter(AppCompatActivity activity) {
        return new ContactsCreateNewMessageSelectMembersAdapter(new ArrayList<NewMessageSelectContactsResponse.User>(), activity);
    }


}
