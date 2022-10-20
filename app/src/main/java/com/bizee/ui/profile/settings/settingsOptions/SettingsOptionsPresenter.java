package com.CalSocial.ui.profile.settings.settingsOptions;


import com.CalSocial.data.DataManager;
import com.CalSocial.ui.base.BasePresenter;
import com.CalSocial.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class SettingsOptionsPresenter<V extends SettingsOptionsMvpView> extends BasePresenter<V> implements SettingsOptionsMvpPresenter<V> {

    @Inject
    public SettingsOptionsPresenter(DataManager dataManager,
                                    SchedulerProvider schedulerProvider,
                                    CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


}
