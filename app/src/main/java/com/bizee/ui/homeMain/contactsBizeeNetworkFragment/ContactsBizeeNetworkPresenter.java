package com.CalSocial.ui.homeMain.contactsCalSocialNetworkFragment;


import com.androidnetworking.error.ANError;
import com.CalSocial.data.DataManager;
import com.CalSocial.data.network.model.ContactsHiveResponse;
import com.CalSocial.ui.base.BasePresenter;
import com.CalSocial.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class ContactsCalSocialNetworkPresenter<V extends ContactsCalSocialNetworkMvpView> extends BasePresenter<V> implements ContactsCalSocialNetworkMvpPresenter<V> {

    @Inject
    public ContactsCalSocialNetworkPresenter(DataManager dataManager,
                                         SchedulerProvider schedulerProvider,
                                         CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void onContactsSwarmRVViewPrepared() {
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .getContactsHiveApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<ContactsHiveResponse>() {
                    @Override
                    public void accept(@NonNull ContactsHiveResponse userResponse)
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
