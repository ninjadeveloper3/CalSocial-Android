package com.CalSocial.ui.profile.editProfile;


import com.CalSocial.data.DataManager;
import com.CalSocial.ui.base.BasePresenter;
import com.CalSocial.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class EditProfilePresenter<V extends EditProfileMvpView> extends BasePresenter<V> implements EditProfileMvpPresenter<V> {

    @Inject
    public EditProfilePresenter(DataManager dataManager,
                                SchedulerProvider schedulerProvider,
                                CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


}