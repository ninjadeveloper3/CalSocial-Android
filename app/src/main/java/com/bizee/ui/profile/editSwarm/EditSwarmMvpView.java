package com.CalSocial.ui.profile.editSwarm;

import com.CalSocial.data.network.model.UsersResponse;
import com.CalSocial.ui.base.MvpView;

import java.util.List;

public interface EditSwarmMvpView extends MvpView {

    void onFragmentDetached(String TAG);

    void updateSwarmMembersRepo(List<UsersResponse.User> userList);


}