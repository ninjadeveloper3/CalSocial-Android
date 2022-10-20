package com.CalSocial.ui.homeMain.home;


import com.applandeo.materialcalendarview.EventDay;
import com.CalSocial.event.GestureEvent;
import com.CalSocial.ui.base.MvpPresenter;

public interface HomeMvpPresenter<V extends HomeMvpView> extends MvpPresenter<V> {

    void onFavoritesRVViewPrepared();

    void onEventsRVViewPrepared();

    void toggleCalendar();

    void getNextMonth();

    void getPreviousMonth();

    void getEventsForSelectedDay(EventDay eventDay);

    void closeCalendarOnScroll(GestureEvent event);

}