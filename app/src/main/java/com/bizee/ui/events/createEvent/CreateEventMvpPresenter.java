package com.CalSocial.ui.events.createEvent;


import com.CalSocial.ui.base.MvpPresenter;

public interface CreateEventMvpPresenter<V extends CreateEventMvpView> extends MvpPresenter<V> {

    void onAttendeesRVViewPrepared();

    void onAddGuestClicked(String TAG);

    void openSelectDateTimeFragment(String TAG);

    void onConfirmSendClicked(String TAG);
}