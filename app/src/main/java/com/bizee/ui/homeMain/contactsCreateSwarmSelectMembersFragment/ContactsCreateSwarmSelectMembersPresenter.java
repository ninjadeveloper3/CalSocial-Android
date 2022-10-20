package com.CalSocial.ui.homeMain.contactsCreateSwarmSelectMembersFragment;


import com.androidnetworking.error.ANError;
import com.CalSocial.data.DataManager;
import com.CalSocial.data.network.model.ContactsHiveResponse;
import com.CalSocial.ui.base.BasePresenter;
import com.CalSocial.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class ContactsCreateSwarmSelectMembersPresenter<V extends ContactsCreateSwarmSelectMembersMvpView> extends BasePresenter<V> implements ContactsCreateSwarmSelectMembersMvpPresenter<V> {

    @Inject
    public ContactsCreateSwarmSelectMembersPresenter(DataManager dataManager,
                                                     SchedulerProvider schedulerProvider,
                                                     CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void onContactsHiveRVViewPrepared() {
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
                            getMvpView().updateMembersRepo(userResponse.getData());
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
    public void onAddMemberClicked(String TAG) {
        getMvpView().onFragmentDetached(TAG);
    }
}
