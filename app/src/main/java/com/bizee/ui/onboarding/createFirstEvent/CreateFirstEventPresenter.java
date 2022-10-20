package com.CalSocial.ui.onboarding.createFirstEvent;

import com.CalSocial.data.DataManager;
import com.CalSocial.ui.base.BasePresenter;
import com.CalSocial.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class CreateFirstEventPresenter<V extends CreateFirstEventMvpView> extends BasePresenter<V> implements CreateFistEventMvpPresenter<V> {
    @Inject
    public CreateFirstEventPresenter(DataManager dataManager,
                                     SchedulerProvider schedulerProvider,
                                     CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onSkipForNowClicked(String TAG) {
        getMvpView().onFragmentDetached(TAG);
    }

    @Override
    public void onCreateNewEventClicked() {
        getMvpView().openCreateNewEvent();
    }
}
