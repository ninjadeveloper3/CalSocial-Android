package com.CalSocial.ui.profile.swarmProfile;


import com.CalSocial.ui.base.MvpPresenter;

public interface SwarmProfileMvpPresenter<V extends SwarmProfileMvpView> extends MvpPresenter<V> {

    void getSwarmDetails();

    void onEventsRVViewPrepared();

    void onMembersRVViewPrepared();

    void onMessagePressed();

    void onAddNewEventClicked();


}