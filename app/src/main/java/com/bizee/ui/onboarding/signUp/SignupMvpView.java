package com.CalSocial.ui.onboarding.signUp;


import android.content.Context;

import com.CalSocial.ui.base.MvpView;

public interface SignupMvpView extends MvpView {

    void onFragmentDetached(String TAG);

    void openTermsLink();

    void openPrivacyPolicyLink();

    void openSignin();

    void showAlertDialogMessage(String title, String message);

    Context getContext();

}