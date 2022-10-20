package com.CalSocial.ui.profile.swarmProfile;


import com.androidnetworking.error.ANError;
import com.CalSocial.data.DataManager;
import com.CalSocial.data.network.model.EventsHomeResponse;
import com.CalSocial.data.network.model.UsersResponse;
import com.CalSocial.ui.base.BasePresenter;
import com.CalSocial.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class SwarmProfilePresenter<V extends SwarmProfileMvpView> extends BasePresenter<V> implements SwarmProfileMvpPresenter<V> {

    private static final String TAG = "EditProfilePresenter";

    @Inject
    public SwarmProfilePresenter(DataManager dataManager,
                                 SchedulerProvider schedulerProvider,
                                 CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void getSwarmDetails() {
        String name = "John Smith";
        List<String> profileImages = new ArrayList<>();
        String coverImage = "";
        Boolean isFavorite = false;
        String bioDescription = "This is where the user gets to write whatever they want about themselves its purpose, goals, whatever to X characters.";

        getMvpView().populateViews(name, profileImages, coverImage, isFavorite, bioDescription);
    }

    @Override
    public void onEventsRVViewPrepared() {
        getCompositeDisposable().add(getDataManager()
                .getProfileEventsApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<EventsHomeResponse>() {
                    @Override
                    public void accept(@NonNull EventsHomeResponse eventsHomeResponse)
                            throws Exception {
                        if (eventsHomeResponse != null && eventsHomeResponse.getData() != null) {
                            getMvpView().updateProfileEventsRepo(eventsHomeResponse.getData());
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
    public void onMembersRVViewPrepared() {
        getCompositeDisposable().add(getDataManager()
                .getSwarmMembersApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<UsersResponse>() {
                    @Override
                    public void accept(@NonNull UsersResponse usersResponse)
                            throws Exception {
                        if (usersResponse != null && usersResponse.getData() != null) {
                            getMvpView().updateSwarmMembersRepo(usersResponse.getData());
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
    public void onMessagePressed() {
        getMvpView().openCreateMessage();
    }

    @Override
    public void onAddNewEventClicked() {
        getMvpView().openNewEvent();

    }
}