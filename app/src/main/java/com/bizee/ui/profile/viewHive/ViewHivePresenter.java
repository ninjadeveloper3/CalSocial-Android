package com.CalSocial.ui.profile.viewHive;


import com.androidnetworking.error.ANError;
import com.CalSocial.data.DataManager;
import com.CalSocial.data.network.model.UsersResponse;
import com.CalSocial.ui.base.BasePresenter;
import com.CalSocial.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class ViewHivePresenter<V extends ViewHiveMvpView> extends BasePresenter<V> implements ViewHiveMvpPresenter<V> {

    private static final String TAG = "EditProfilePresenter";

    @Inject
    public ViewHivePresenter(DataManager dataManager,
                             SchedulerProvider schedulerProvider,
                             CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
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
}