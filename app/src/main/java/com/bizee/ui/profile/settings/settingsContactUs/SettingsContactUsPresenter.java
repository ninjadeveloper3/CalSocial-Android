package com.CalSocial.ui.profile.settings.settingsContactUs;


import com.CalSocial.data.DataManager;
import com.CalSocial.ui.base.BasePresenter;
import com.CalSocial.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class SettingsContactUsPresenter<V extends SettingsContactUsMvpView> extends BasePresenter<V> implements SettingsContactUsMvpPresenter<V> {

    @Inject
    public SettingsContactUsPresenter(DataManager dataManager,
                                      SchedulerProvider schedulerProvider,
                                      CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


}
