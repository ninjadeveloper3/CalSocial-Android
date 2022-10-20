package com.CalSocial.ui.onboarding.phoneCode;


import com.CalSocial.ui.base.MvpPresenter;

public interface PhoneCodeMvpPresenter<V extends PhoneCodeMvpView> extends MvpPresenter<V> {
    void onPhoneContinueClicked(String[] digits, String TAG);

    void onResendCodeClicked();

    void onSmsCodeReceived(String code);
}