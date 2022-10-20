package com.CalSocial.ui.homeMain.notifications;


import com.CalSocial.ui.base.MvpPresenter;

public interface NotificationsMvpPresenter<V extends NotificationsMvpView> extends MvpPresenter<V> {

    void onNotificationsRVViewPrepared();

}