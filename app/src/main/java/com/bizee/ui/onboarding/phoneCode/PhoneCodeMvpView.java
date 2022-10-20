package com.CalSocial.ui.onboarding.phoneCode;


import android.content.Context;

import com.CalSocial.ui.base.MvpView;

public interface PhoneCodeMvpView extends MvpView {
    void onFragmentDetached(String TAG);

    void populateDigits(String[] digits);

    void showAlertDialogMessage(String title, String message);

    Context getContext();

}