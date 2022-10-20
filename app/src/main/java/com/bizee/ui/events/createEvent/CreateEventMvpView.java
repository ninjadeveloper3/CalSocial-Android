package com.CalSocial.ui.events.createEvent;

import com.CalSocial.data.network.model.EventAttendeesResponse;
import com.CalSocial.ui.base.MvpView;

import java.util.List;

public interface CreateEventMvpView extends MvpView {

    void onFragmentDetached(String TAG);

    void updateAttendeesRepo(List<EventAttendeesResponse.User> userList);


}