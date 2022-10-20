package com.CalSocial.ui.homeMain.messagesDetails;

import com.CalSocial.data.network.model.MessagesResponse;
import com.CalSocial.ui.base.MvpView;

import java.util.List;

public interface MessagesDetailsMvpView extends MvpView {

    void onFragmentDetached(String TAG);

    void updateMessagesRepo(List<MessagesResponse.Message> messageList);

}