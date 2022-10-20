package com.CalSocial.ui.events.selectDateTime;


import com.CalSocial.data.DataManager;
import com.CalSocial.ui.base.BasePresenter;
import com.CalSocial.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class SelectDateTimeEventPresenter<V extends SelectDateTimeEventMvpView> extends BasePresenter<V> implements SelectDateTimeEventMvpPresenter<V> {

    private static final String TAG = "EditProfilePresenter";

    @Inject
    public SelectDateTimeEventPresenter(DataManager dataManager,
                                        SchedulerProvider schedulerProvider,
                                        CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void openTimeSuggestions(String TAG) {
        getMvpView().onFragmentDetached(TAG);
    }
}