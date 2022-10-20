package com.CalSocial.ui.profile.contactProfile;


import com.androidnetworking.error.ANError;
import com.CalSocial.data.DataManager;
import com.CalSocial.data.network.model.EventsHomeResponse;
import com.CalSocial.ui.base.BasePresenter;
import com.CalSocial.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class ContactProfilePresenter<V extends ContactProfileMvpView> extends BasePresenter<V> implements ContactProfileMvpPresenter<V> {

    @Inject
    public ContactProfilePresenter(DataManager dataManager,
                                   SchedulerProvider schedulerProvider,
                                   CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void getContactDetails() {
        //This function will call the relevant API to get the details of the contact.

        String name = "John Smith";
        String location = "Los Angeles, California";
        String profileImage = "";
        String coverImage = "";
        Boolean isFavorite = false;
        String bioDescription = "This is where the user gets to write whatever they want about themselves its purpose, goals, whatever to X characters.";
        List<String> bucketList = new ArrayList<String>();
        bucketList.add("I want to fly a plane.");
        bucketList.add("I want to drive a F1 car.");
        bucketList.add("I want to own a Ferrari.");
        HiveStatus hiveStatus = HiveStatus.IN_HIVE;

        getMvpView().populateViews(name, location, profileImage, coverImage, isFavorite, bioDescription, bucketList, hiveStatus);
    }

    @Override
    public void sendHiveRequest() {
        getMvpView().sendHiveRequest();
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
    public void showHive(String TAG) {
        getMvpView().onFragmentDetached(TAG);
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
