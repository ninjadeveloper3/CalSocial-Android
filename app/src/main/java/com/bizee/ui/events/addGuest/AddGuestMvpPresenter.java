package com.CalSocial.ui.events.addGuest;


import com.CalSocial.ui.base.MvpPresenter;

public interface AddGuestMvpPresenter<V extends AddGuestMvpView> extends MvpPresenter<V> {

    void onMembersRVViewPrepared();

}