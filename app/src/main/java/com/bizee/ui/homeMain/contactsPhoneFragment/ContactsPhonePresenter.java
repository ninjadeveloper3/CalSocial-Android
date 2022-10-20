package com.CalSocial.ui.homeMain.contactsPhoneFragment;


import android.content.Context;

import com.androidnetworking.error.ANError;
import com.CalSocial.data.DataManager;
import com.CalSocial.data.network.model.ContactsHiveResponse;
import com.CalSocial.ui.base.BasePresenter;
import com.CalSocial.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class ContactsPhonePresenter<V extends ContactsPhoneMvpView> extends BasePresenter<V> implements ContactsPhoneMvpPresenter<V> {

    @Inject
    public ContactsPhonePresenter(DataManager dataManager,
                                  SchedulerProvider schedulerProvider,
                                  CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void onContactsSwarmRVViewPrepared(Context context) {
        getMvpView().showLoading();

       /* DaoSession daoSession = AppUtils.getDaoSession(context);
        List<Contact> savedPhoneContacts = daoSession.getContactDao().queryBuilder().list();

        List<ContactsHiveResponse.User> userList = new ArrayList<>();

        for (int i = 0; i < savedPhoneContacts.size(); i++) {
            Contact contact = savedPhoneContacts.get(i);

            if (contact.getIsPhoneContact()) {
                String[] nameParts = contact.getName().split(" ");
                String firstName = "";
                String lastName = "";

                if (nameParts.length >= 2) {

                    firstName = nameParts[0];
                    lastName = nameParts[1];
                } else {
                    firstName = contact.getName();
                    lastName = "";
                }

                userList.add(new ContactsHiveResponse.User(contact.getId(), firstName, lastName, "", false, contact.getIsCalSocialUser(), contact.getIsPhoneContact()));
            }
        }*/

        //getMvpView().hideLoading();
        //getMvpView().updateSwarmNotificationsRepo(userList);

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
