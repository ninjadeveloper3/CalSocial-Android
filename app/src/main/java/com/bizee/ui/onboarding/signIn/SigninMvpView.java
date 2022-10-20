package com.CalSocial.ui.onboarding.signIn;


import android.content.Context;

import com.CalSocial.ui.base.MvpView;

public interface SigninMvpView extends MvpView {

    void onFragmentDetached(String TAG);

    void showAlertDialogMessage(String title, String message);

    Context getContext();

}