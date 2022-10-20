package com.CalSocial.ui.onboarding.createFirstEvent;

import com.CalSocial.ui.base.MvpPresenter;

public interface CreateFistEventMvpPresenter<V extends CreateFirstEventMvpView> extends MvpPresenter<V> {

    void onSkipForNowClicked(String TAG);

    void onCreateNewEventClicked();

}
