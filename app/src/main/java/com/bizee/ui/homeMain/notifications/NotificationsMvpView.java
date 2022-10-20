package com.CalSocial.ui.homeMain.notifications;

import com.CalSocial.data.network.model.NotificationsResponse;
import com.CalSocial.ui.base.MvpView;

import java.util.List;

public interface NotificationsMvpView extends MvpView {

    void onFragmentDetached(String TAG);

    void updateNotificationsRepo(List<NotificationsResponse.Notification> notificationListt);

}