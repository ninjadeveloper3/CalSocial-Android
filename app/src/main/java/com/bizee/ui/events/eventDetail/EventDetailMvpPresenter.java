package com.CalSocial.ui.events.eventDetail;


import com.CalSocial.ui.base.MvpPresenter;

public interface EventDetailMvpPresenter<V extends EventDetailMvpView> extends MvpPresenter<V> {

    void onAttendeesRVViewPrepared();

    void onCommentsRVViewPrepared();

}