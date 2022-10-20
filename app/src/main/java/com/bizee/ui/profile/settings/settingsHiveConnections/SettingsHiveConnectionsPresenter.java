package com.CalSocial.ui.profile.settings.settingsHiveConnections;


import com.CalSocial.data.DataManager;
import com.CalSocial.ui.base.BasePresenter;
import com.CalSocial.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class SettingsHiveConnectionsPresenter<V extends SettingsHiveConnectionsMvpView> extends BasePresenter<V> implements SettingsHiveConnectionsMvpPresenter<V> {

    @Inject
    public SettingsHiveConnectionsPresenter(DataManager dataManager,
                                            SchedulerProvider schedulerProvider,
                                            CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


}
