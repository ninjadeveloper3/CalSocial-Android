package com.CalSocial.ui.profile.settings.settingsSetYourPrivacy;


import com.CalSocial.data.DataManager;
import com.CalSocial.ui.base.BasePresenter;
import com.CalSocial.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class SettingsSetYourPrivacyPresenter<V extends SettingsSetYourPrivacyMvpView> extends BasePresenter<V> implements SettingsSetYourPrivacyMvpPresenter<V> {

    @Inject
    public SettingsSetYourPrivacyPresenter(DataManager dataManager,
                                           SchedulerProvider schedulerProvider,
                                           CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


}
