package com.CalSocial.ui.homeMain.messages;


import com.CalSocial.ui.base.MvpPresenter;

public interface MessagesMvpPresenter<V extends MessagesMvpView> extends MvpPresenter<V> {

    void onMessagesRVViewPrepared();


}