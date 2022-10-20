package com.CalSocial.ui.profile.yourProfile;

import com.CalSocial.ui.base.MvpView;

import java.util.List;

public interface YourProfileMvpView extends MvpView {

    void populateViews(String name, String location, String profileImage, String coverImage, String bioDescription, List<String> bucketList);

    void onFragmentDetached(String TAG);

}