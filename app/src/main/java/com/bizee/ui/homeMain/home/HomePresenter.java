package com.CalSocial.ui.homeMain.home;


import com.androidnetworking.error.ANError;
import com.applandeo.materialcalendarview.EventDay;
import com.CalSocial.data.DataManager;
import com.CalSocial.data.network.model.EventsHomeResponse;
import com.CalSocial.data.network.model.UsersResponse;
import com.CalSocial.event.GestureEvent;
import com.CalSocial.ui.base.BasePresenter;
import com.CalSocial.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class HomePresenter<V extends HomeMvpView> extends BasePresenter<V> implements HomeMvpPresenter<V> {

    @Inject
    public HomePresenter(DataManager dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onFavoritesRVViewPrepared() {
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .getFavoritesApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<UsersResponse>() {
                    @Override
                    public void accept(@NonNull UsersResponse usersResponse)
                            throws Exception {
                        getMvpView().hideLoading();
                        if (usersResponse != null && usersResponse.getData() != null) {
                           getMvpView().updateFavoritesRepo(usersResponse.getData());
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
    public void onEventsRVViewPrepared() {
        getCompositeDisposable().add(getDataManager()
                .getEventsHomeApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<EventsHomeResponse>() {
                    @Override
                    public void accept(@NonNull EventsHomeResponse eventsHomeResponse)
                            throws Exception {
                        if (eventsHomeResponse != null && eventsHomeResponse.getData() != null) {
                            getMvpView().updateEventsHomeRepo(eventsHomeResponse.getData());
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

    @Override
    public void toggleCalendar() {
        getMvpView().toggleCalendar();
    }

    @Override
    public void getNextMonth() {
        getMvpView().getNextMonth();
    }

    @Override
    public void getPreviousMonth() {
        getMvpView().getPreviousMonth();
    }

    @Override
    public void getEventsForSelectedDay(EventDay eventDay) {

        //TODO: Call the relevant API to get the events for the given day

        getMvpView().repopulateEventsRV();

    }

    @Override
    public void closeCalendarOnScroll(GestureEvent event) {
        getMvpView().closeCalendarOnScroll(event);
    }
}
