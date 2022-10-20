package com.CalSocial.ui.homeMain.home;

import com.CalSocial.data.network.model.EventsHomeResponse;
import com.CalSocial.data.network.model.UsersResponse;
import com.CalSocial.event.GestureEvent;
import com.CalSocial.ui.base.MvpView;

import java.util.List;

public interface HomeMvpView extends MvpView {

    void updateFavoritesRepo(List<UsersResponse.User> userList);

    void updateEventsHomeRepo(List<EventsHomeResponse.Event> eventList);

    void toggleCalendar();

    void getNextMonth();

    void getPreviousMonth();

    void repopulateEventsRV();

    void openContactProfile();

    void openSwarmProfile();

    void closeCalendarOnScroll(GestureEvent event);

}