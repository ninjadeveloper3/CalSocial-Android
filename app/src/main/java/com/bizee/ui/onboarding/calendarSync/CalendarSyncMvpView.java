package com.CalSocial.ui.onboarding.calendarSync;

import com.CalSocial.ui.base.MvpView;

public interface CalendarSyncMvpView extends MvpView {

    void onFragmentDetached(String TAG);

    void startCalenderSync();

}