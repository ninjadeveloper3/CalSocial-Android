package com.CalSocial.ui.homeMain.contacts;

import androidx.fragment.app.Fragment;

import com.CalSocial.ui.base.MvpView;

public interface ContactsMvpView extends MvpView {

    void onFragmentDetached(String TAG);

    void showHiveEditScreen(Fragment fragment);

    void showSwarmEditScreen(Fragment fragment);

    void showHiveViewScreen(Fragment fragment);

    void showSwarmViewScreen(Fragment fragment);

}