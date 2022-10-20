package com.CalSocial.ui.profile.viewHive;


import com.CalSocial.ui.base.MvpPresenter;

public interface ViewHiveMvpPresenter<V extends ViewHiveMvpView> extends MvpPresenter<V> {

    void onMembersRVViewPrepared();

}