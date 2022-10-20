package com.CalSocial.ui.events.selectTimeSuggestions;


import com.androidnetworking.error.ANError;
import com.CalSocial.data.DataManager;
import com.CalSocial.data.network.model.EventAttendeesResponse;
import com.CalSocial.data.network.model.TimeSuggestionsResponse;
import com.CalSocial.ui.base.BasePresenter;
import com.CalSocial.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class SelectTimeSuggestionsEventPresenter<V extends SelectTimeSuggestionsEventMvpView> extends BasePresenter<V> implements SelectTimeSuggestionsEventMvpPresenter<V> {

    private static final String TAG = "EditProfilePresenter";

    @Inject
    public SelectTimeSuggestionsEventPresenter(DataManager dataManager,
                                               SchedulerProvider schedulerProvider,
                                               CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void openTimeSuggestions(String TAG) {
        getMvpView().onFragmentDetached(TAG);
    }

    @Override
    public void onSuggestionsRVViewPrepared() {
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .getTimeSuggestionsApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<TimeSuggestionsResponse>() {
                    @Override
                    public void accept(@NonNull TimeSuggestionsResponse suggestionsResponse)
                            throws Exception {
                        getMvpView().hideLoading();
                        if (suggestionsResponse != null && suggestionsResponse.getData() != null) {
                            getMvpView().updateSuggestionsRepo(suggestionsResponse.getData());
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
    public void onAttendeesRVViewPrepared() {
        getCompositeDisposable().add(getDataManager()
                .getEventAttendeesApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<EventAttendeesResponse>() {
                    @Override
                    public void accept(@NonNull EventAttendeesResponse usersResponse)
                            throws Exception {
                        if (usersResponse != null && usersResponse.getData() != null) {
                            getMvpView().updateAttendeesRepo(usersResponse.getData());
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable)
                            throws Exception {
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
}