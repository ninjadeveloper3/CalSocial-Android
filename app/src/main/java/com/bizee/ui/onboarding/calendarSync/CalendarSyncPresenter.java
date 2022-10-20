package com.CalSocial.ui.onboarding.calendarSync;

import com.CalSocial.data.DataManager;
import com.CalSocial.ui.base.BasePresenter;
import com.CalSocial.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class CalendarSyncPresenter<V extends CalendarSyncMvpView> extends BasePresenter<V> implements CalendarSyncMvpPresenter<V> {

    private static final String TAG = "ContactsFoundPresenter";

    @Inject
    public CalendarSyncPresenter(DataManager dataManager,
                                 SchedulerProvider schedulerProvider,
                                 CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onSkipForNowClicked(String TAG) {
        getMvpView().onFragmentDetached(TAG);
    }

    @Override
    public void onSyncCalenderClicked() {
        //getMvpView().startCalenderSync();
    }
}