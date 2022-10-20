package com.CalSocial.ui.profile.settings.settingsSyncCalendars;


import com.CalSocial.data.DataManager;
import com.CalSocial.ui.base.BasePresenter;
import com.CalSocial.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class SettingsSyncCalendarsPresenter<V extends SettingsCalSocialSuggestionsMvpView> extends BasePresenter<V> implements SettingsSyncCalendarsMvpPresenter<V> {

    @Inject
    public SettingsSyncCalendarsPresenter(DataManager dataManager,
                                          SchedulerProvider schedulerProvider,
                                          CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


}
