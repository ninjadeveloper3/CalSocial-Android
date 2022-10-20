package com.CalSocial.ui.profile.settings.settingsBlockedUsers;


import com.CalSocial.data.DataManager;
import com.CalSocial.ui.base.BasePresenter;
import com.CalSocial.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class SettingsBlockedUsersPresenter<V extends SettingsBlockedUsersMvpView> extends BasePresenter<V> implements SettingsBlockedUsersMvpPresenter<V> {

    @Inject
    public SettingsBlockedUsersPresenter(DataManager dataManager,
                                         SchedulerProvider schedulerProvider,
                                         CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


}
