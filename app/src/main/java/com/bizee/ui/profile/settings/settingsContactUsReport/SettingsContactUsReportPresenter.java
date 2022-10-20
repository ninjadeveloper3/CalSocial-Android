package com.CalSocial.ui.profile.settings.settingsContactUsReport;


import com.CalSocial.data.DataManager;
import com.CalSocial.ui.base.BasePresenter;
import com.CalSocial.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class SettingsContactUsReportPresenter<V extends SettingsContactUsReportMvpView> extends BasePresenter<V> implements SettingsContactUsReportMvpPresenter<V> {

    @Inject
    public SettingsContactUsReportPresenter(DataManager dataManager,
                                            SchedulerProvider schedulerProvider,
                                            CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


}
