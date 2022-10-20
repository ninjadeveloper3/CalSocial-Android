package com.CalSocial.ui.homeMain.messagesChatsOneToMany;


import com.CalSocial.ui.base.MvpPresenter;

public interface MessagesChatOMMvpPresenter<V extends MessagesChatOMMvpView> extends MvpPresenter<V> {

    void onMessagesRVViewPrepared();


}