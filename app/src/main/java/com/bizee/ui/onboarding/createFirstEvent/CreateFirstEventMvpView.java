package com.CalSocial.ui.onboarding.createFirstEvent;

import com.CalSocial.ui.base.MvpView;

public interface CreateFirstEventMvpView extends MvpView {
    void onFragmentDetached(String TAG);

    void openCreateNewEvent();

}
