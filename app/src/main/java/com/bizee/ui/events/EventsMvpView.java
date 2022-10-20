package com.CalSocial.ui.events;

import com.CalSocial.ui.base.MvpView;

public interface EventsMvpView extends MvpView {

    void showEventDetailFragment();

    void showCreateEventFragment();

    void showAddGuestFragment();

    void showSelectDateTimeEventFragment();

    void showCalSocialSuggestionsEventFragment();

    void goToHomeScreen();
}