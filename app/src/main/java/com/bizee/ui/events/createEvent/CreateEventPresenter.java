package com.CalSocial.ui.events.createEvent;


import com.androidnetworking.error.ANError;
import com.CalSocial.data.DataManager;
import com.CalSocial.data.network.model.EventAttendeesResponse;
import com.CalSocial.ui.base.BasePresenter;
import com.CalSocial.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class CreateEventPresenter<V extends CreateEventMvpView> extends BasePresenter<V> implements CreateEventMvpPresenter<V> {

    private static final String TAG = "EditProfilePresenter";

    @Inject
    public CreateEventPresenter(DataManager dataManager,
                                SchedulerProvider schedulerProvider,
                                CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void onAttendeesRVViewPrepared() {
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .getEventAttendeesApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<EventAttendeesResponse>() {
                    @Override
                    public void accept(@NonNull EventAttendeesResponse usersResponse)
                            throws Exception {
                        getMvpView().hideLoading();
                        if (usersResponse != null && usersResponse.getData() != null) {
                            getMvpView().updateAttendeesRepo(usersResponse.getData());
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable)
                            throws Exception {
                        getMvpView().hideLoading();
                        if (!isViewAttached()) {
                            return;
                        }

                        // handle the error here
                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            handleApiError(anError);
                        }
                    }
                }));
    }

    @Override
    public void onAddGuestClicked(String TAG) {
        getMvpView().onFragmentDetached(TAG);
    }

    @Override
    public void openSelectDateTimeFragment(String TAG) {
        getMvpView().onFragmentDetached(TAG);
    }

    @Override
    public void onConfirmSendClicked(String TAG) {
        getMvpView().onFragmentDetached(TAG);
    }
}