package com.CalSocial.ui.events;

import com.CalSocial.ui.base.MvpPresenter;

public interface EventsMvpPresenter<V extends EventsMvpView> extends MvpPresenter<V> {

    void onScreenCreated(String screenToOpen);

    void showEventDetailFragment();

    void showCreateEventFragment();

    void showAddGuestFragment();

    void showSelectDateTimeEventFragment();

    void showCalSocialSuggestionsEventFragment();

    void onCancelClicked();

}