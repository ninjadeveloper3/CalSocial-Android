package com.CalSocial.ui.profile.editSwarm;


import com.CalSocial.ui.base.MvpPresenter;

public interface EditSwarmMvpPresenter<V extends EditSwarmMvpView> extends MvpPresenter<V> {

    void onMembersRVViewPrepared();

    void onAddMemberClicked(String TAG);
}