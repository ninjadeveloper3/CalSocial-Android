package com.CalSocial.ui.profile.contactProfile;

import com.CalSocial.data.network.model.EventsHomeResponse;
import com.CalSocial.ui.base.MvpView;

import java.util.List;

public interface ContactProfileMvpView extends MvpView {

    void populateViews(String name, String location, String profileImage, String coverImage, Boolean isFavorite, String bioDescription, List<String> bucketList, HiveStatus hiveStatus);

    void sendHiveRequest();

    void onFragmentDetached(String TAG);

    void updateProfileEventsRepo(List<EventsHomeResponse.Event> eventList);

    void openCreateMessage();

    void openNewEvent();

}