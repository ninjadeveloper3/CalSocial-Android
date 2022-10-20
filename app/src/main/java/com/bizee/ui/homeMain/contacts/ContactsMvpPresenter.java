package com.CalSocial.ui.homeMain.contacts;


import androidx.fragment.app.FragmentManager;

import com.CalSocial.ui.base.MvpPresenter;

public interface ContactsMvpPresenter<V extends ContactsMvpView> extends MvpPresenter<V> {

    void editButtonClicked(FragmentManager fragmentManager);

    void cancelButtonClicked(FragmentManager fragmentManager);

}