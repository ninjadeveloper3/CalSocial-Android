package com.CalSocial.ui.onboarding.additionalInfo;

import android.content.Context;

import com.CalSocial.ui.base.MvpView;

public interface SignupAdditionalInfoMvpView extends MvpView {

    void onFragmentDetached(String TAG);

    void showAlertDialogMessage(String title, String message);

    Context getContext();
}