package com.CalSocial.ui.homeMain.messages;

import com.CalSocial.data.network.model.MessagesResponse;
import com.CalSocial.ui.base.MvpView;

import java.util.List;

public interface MessagesMvpView extends MvpView {

    void onFragmentDetached(String TAG);

    void updateMessagesRepo(List<MessagesResponse.Message> messageList);

}