package com.CalSocial.ui.events.eventDetail;

import com.CalSocial.data.network.model.CommentsResponse;
import com.CalSocial.data.network.model.EventAttendeesResponse;
import com.CalSocial.ui.base.MvpView;

import java.util.List;

public interface EventDetailMvpView extends MvpView {

    void onFragmentDetached(String TAG);

    void updateAttendeesRepo(List<EventAttendeesResponse.User> userList);

    void updateCommentsRepo(List<CommentsResponse.Comment> commentList);


}