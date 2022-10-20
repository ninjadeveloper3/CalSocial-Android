package com.CalSocial.ui.homeMain.messagesChatsOneToMany;


import com.androidnetworking.error.ANError;
import com.CalSocial.data.DataManager;
import com.CalSocial.data.network.model.MessagesResponse;
import com.CalSocial.ui.base.BasePresenter;
import com.CalSocial.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class MessagesChatOMPresenter<V extends MessagesChatOMMvpView> extends BasePresenter<V> implements MessagesChatOMMvpPresenter<V> {

    @Inject
    public MessagesChatOMPresenter(DataManager dataManager,
                                   SchedulerProvider schedulerProvider,
                                   CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    public void showHive(String TAG) {
        getMvpView().onFragmentDetached(TAG);
    }

    @Override
    public void onMessagesRVViewPrepared() {
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .getMessagesApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<MessagesResponse>() {
                    @Override
                    public void accept(@NonNull MessagesResponse messagesResponse)
                            throws Exception {
                        getMvpView().hideLoading();
                        if (messagesResponse != null && messagesResponse.getData() != null) {
                            getMvpView().updateMessagesRepo(messagesResponse.getData());
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
}
