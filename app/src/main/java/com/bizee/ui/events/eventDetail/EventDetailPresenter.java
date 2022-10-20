package com.CalSocial.ui.events.eventDetail;


import com.androidnetworking.error.ANError;
import com.CalSocial.data.DataManager;
import com.CalSocial.data.network.model.CommentsResponse;
import com.CalSocial.data.network.model.EventAttendeesResponse;
import com.CalSocial.ui.base.BasePresenter;
import com.CalSocial.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class EventDetailPresenter<V extends EventDetailMvpView> extends BasePresenter<V> implements EventDetailMvpPresenter<V> {

    private static final String TAG = "EditProfilePresenter";

    @Inject
    public EventDetailPresenter(DataManager dataManager,
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
    public void onCommentsRVViewPrepared() {
        getCompositeDisposable().add(getDataManager()
                .getCommentsApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<CommentsResponse>() {
                    @Override
                    public void accept(@NonNull CommentsResponse commentsResponse)
                            throws Exception {
                        if (commentsResponse != null && commentsResponse.getData() != null) {
                            getMvpView().updateCommentsRepo(commentsResponse.getData());
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