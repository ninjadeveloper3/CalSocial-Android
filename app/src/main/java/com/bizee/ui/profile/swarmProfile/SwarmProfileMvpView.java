package com.CalSocial.ui.profile.swarmProfile;

import com.CalSocial.data.network.model.EventsHomeResponse;
import com.CalSocial.data.network.model.UsersResponse;
import com.CalSocial.ui.base.MvpView;

import java.util.List;

public interface SwarmProfileMvpView extends MvpView {

    void populateViews(String name, List<String> profileImages, String coverImage, Boolean isFavorite, String aboutUsDescrpition);

    void onFragmentDetached(String TAG);

    void updateProfileEventsRepo(List<EventsHomeResponse.Event> eventList);

    void updateSwarmMembersRepo(List<UsersResponse.User> userList);

    void openCreateMessage();

    void openNewEvent();


}