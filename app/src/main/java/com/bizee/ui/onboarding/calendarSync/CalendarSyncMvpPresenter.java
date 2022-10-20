package com.CalSocial.ui.onboarding.calendarSync;

import com.CalSocial.ui.base.MvpPresenter;

public interface CalendarSyncMvpPresenter<V extends CalendarSyncMvpView> extends MvpPresenter<V> {

    void onSkipForNowClicked(String TAG);

    void onSyncCalenderClicked();

}