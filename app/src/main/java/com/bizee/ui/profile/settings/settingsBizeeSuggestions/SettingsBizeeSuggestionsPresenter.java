package com.CalSocial.ui.profile.settings.settingsCalSocialSuggestions;


import com.CalSocial.data.DataManager;
import com.CalSocial.ui.base.BasePresenter;
import com.CalSocial.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class SettingsCalSocialSuggestionsPresenter<V extends SettingsCalSocialSuggestionsMvpView> extends BasePresenter<V> implements SettingsCalSocialSuggestionsMvpPresenter<V> {

    @Inject
    public SettingsCalSocialSuggestionsPresenter(DataManager dataManager,
                                             SchedulerProvider schedulerProvider,
                                             CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


}
