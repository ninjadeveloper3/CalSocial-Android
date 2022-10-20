package com.CalSocial.ui.homeMain.contactsSwarmFragment;


import com.androidnetworking.error.ANError;
import com.CalSocial.data.DataManager;
import com.CalSocial.data.network.model.ContactsSwarmResponse;
import com.CalSocial.ui.base.BasePresenter;
import com.CalSocial.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class ContactsSwarmPresenter<V extends ContactsSwarmMvpView> extends BasePresenter<V> implements ContactsSwarmMvpPresenter<V> {

    @Inject
    public ContactsSwarmPresenter(DataManager dataManager,
                                  SchedulerProvider schedulerProvider,
                                  CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void onContactsSwarmRVViewPrepared() {
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .getContactsSwarmApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<ContactsSwarmResponse>() {
                    @Override
                    public void accept(@NonNull ContactsSwarmResponse userResponse)
                            throws Exception {
                        getMvpView().hideLoading();
                        if (userResponse != null && userResponse.getData() != null) {
                            getMvpView().updateSwarmNotificationsRepo(userResponse.getData());
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
    public void onCreateSwarmClicked(String TAG) {
        getMvpView().onFragmentDetached(TAG);

    }


}
