package com.CalSocial.ui.homeMain.messagesDetails;


import com.CalSocial.ui.base.MvpPresenter;

public interface MessagesDetailMvpPresenter<V extends MessagesDetailsMvpView> extends MvpPresenter<V> {

    void onMessagesRVViewPrepared();


}