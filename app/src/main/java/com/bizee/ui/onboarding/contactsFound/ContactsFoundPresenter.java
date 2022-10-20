package com.CalSocial.ui.onboarding.contactsFound;

import com.androidnetworking.error.ANError;
import com.CalSocial.data.DataManager;
import com.CalSocial.data.network.model.ContactsFoundResponse;
import com.CalSocial.ui.base.BasePresenter;
import com.CalSocial.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class ContactsFoundPresenter<V extends ContactsFoundMvpView> extends BasePresenter<V> implements ContactsFoundMvpPresenter<V> {

    private static final String TAG = "ContactsFoundPresenter";

    @Inject
    public ContactsFoundPresenter(DataManager dataManager,
                                  SchedulerProvider schedulerProvider,
                                  CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onSkipForNowClicked(String TAG) {
        getMvpView().onFragmentDetached(TAG);
    }

    @Override
    public void onViewPrepared() {
        getCompositeDisposable().add(getDataManager()
                .getFoundcontactsCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<ContactsFoundResponse>() {
                    @Override
                    public void accept(@NonNull ContactsFoundResponse usersResponse)
                            throws Exception {
                        if (usersResponse != null && usersResponse.getData() != null) {
                            getMvpView().updateRepo(usersResponse.getData());
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