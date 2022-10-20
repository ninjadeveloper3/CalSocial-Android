package com.CalSocial.ui.profile.contactProfile;


import com.CalSocial.ui.base.MvpPresenter;

public interface ContactProfileMvpPresenter<V extends ContactProfileMvpView> extends MvpPresenter<V> {

    void getContactDetails();

    void sendHiveRequest();

    void onEventsRVViewPrepared();

    void showHive(String TAG);

    void onMessagePressed();

    void onAddNewEventClicked();


}