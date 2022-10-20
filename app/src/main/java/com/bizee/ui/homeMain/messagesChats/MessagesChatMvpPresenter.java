package com.CalSocial.ui.homeMain.messagesChats;


import com.CalSocial.ui.base.MvpPresenter;

public interface MessagesChatMvpPresenter<V extends MessagesChatMvpView> extends MvpPresenter<V> {

    void onMessagesRVViewPrepared();


}