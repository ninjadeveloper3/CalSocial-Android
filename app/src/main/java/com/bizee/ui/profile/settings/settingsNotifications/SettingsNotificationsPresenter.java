package com.CalSocial.ui.profile.settings.settingsNotifications;


import com.CalSocial.data.DataManager;
import com.CalSocial.ui.base.BasePresenter;
import com.CalSocial.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class SettingsNotificationsPresenter<V extends SettingsNotificationsMvpView> extends BasePresenter<V> implements SettingsNotificationsMvpPresenter<V> {

    @Inject
    public SettingsNotificationsPresenter(DataManager dataManager,
                                          SchedulerProvider schedulerProvider,
                                          CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


}
