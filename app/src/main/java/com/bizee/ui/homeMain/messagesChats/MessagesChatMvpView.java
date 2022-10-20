package com.CalSocial.ui.homeMain.messagesChats;

import com.CalSocial.data.network.model.MessagesResponse;
import com.CalSocial.ui.base.MvpView;

import java.util.List;

public interface MessagesChatMvpView extends MvpView {

    void onFragmentDetached(String TAG);

    void updateMessagesRepo(List<MessagesResponse.Message> messageList);

}