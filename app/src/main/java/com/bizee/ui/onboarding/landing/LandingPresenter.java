package com.CalSocial.ui.onboarding.landing;


import com.CalSocial.data.DataManager;
import com.CalSocial.ui.base.BasePresenter;
import com.CalSocial.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class LandingPresenter<V extends LandingMvpView> extends BasePresenter<V> implements LandingMvpPresenter<V> {

    private static final String TAG = "LandingPresenter";

    @Inject
    public LandingPresenter(DataManager dataManager,
                            SchedulerProvider schedulerProvider,
                            CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onSigninClicked(String TAG) {
        getMvpView().onFragmentDetached(TAG);

    }

    @Override
    public void onSignupClicked(String TAG) {
        getMvpView().onFragmentDetached(TAG);

    }


}