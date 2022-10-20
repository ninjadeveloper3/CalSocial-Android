package com.CalSocial.ui.onboarding.signIn;


import android.util.Log;

import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.CalSocial.R;
import com.CalSocial.data.DataManager;
import com.CalSocial.data.TemporaryData;
import com.CalSocial.data.network.ApiEndPoint;
import com.CalSocial.ui.base.BasePresenter;
import com.CalSocial.ui.homeMain.contacts.ContactsFragment;
import com.CalSocial.ui.onboarding.landing.LandingFragment;
import com.CalSocial.ui.onboarding.signUp.SignupFragment;
import com.CalSocial.utils.AppConstants;
import com.CalSocial.utils.AppUtils;
import com.CalSocial.utils.rx.SchedulerProvider;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class SigninPresenter<V extends SigninMvpView> extends BasePresenter<V> implements SigninMvpPresenter<V> {

    private String USER_SIGNIN_VERIFICATION_CODE_GENERATED = "Congrats :) verification code against your phone created successfully";
    private String USER_NOT_ACTIVATED = "User is not activated , activate your account first via signup";
    private String USER_NOT_FOUND = "No User Found in record , signup first";

    @Inject
    public SigninPresenter(DataManager dataManager,
                           SchedulerProvider schedulerProvider,
                           CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onGoBackClicked(String TAG) {

        if (AppUtils.sourceScreenFragment.compareToIgnoreCase(LandingFragment.TAG) == 0 && AppUtils.destinationScreenFragment.compareToIgnoreCase(SigninFragment.TAG) == 0) {
            AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, ContactsFragment.TAG);
            getMvpView().onFragmentDetached(SigninFragment.TAG + "-" + AppConstants.OPEN_LANDING);
        } else if (AppUtils.sourceScreenFragment.compareToIgnoreCase(SignupFragment.TAG) == 0 && AppUtils.destinationScreenFragment.compareToIgnoreCase(SigninFragment.TAG) == 0) {
            getMvpView().onFragmentDetached(SigninFragment.TAG + "-" + AppConstants.OPEN_SIGNUP);
        }

    }

    @Override
    public void onContinueClicked(String phone, String TAG) {

        String title = "";
        String message = "";
        if (phone == null || phone.isEmpty()) {
            //getMvpView().onError(R.string.empty_phone);
            title = getMvpView().getContext().getResources().getString(R.string.error_title);
            message = getMvpView().getContext().getResources().getString(R.string.empty_phone);
            getMvpView().showAlertDialogMessage(title, message);
            return;
        }

        String formattedPhone = AppUtils.getPhoneNumberUSCANFormat(phone);
        if (formattedPhone != null) {
            // signinApiCall(formattedPhone, TAG);
            getMvpView().hideLoading();
            getMvpView().onFragmentDetached(TAG);
        } else {
            title = getMvpView().getContext().getResources().getString(R.string.invalid_us_can_number_title);
            message = getMvpView().getContext().getResources().getString(R.string.invalid_us_can_number_message);
            getMvpView().showAlertDialogMessage(title, message);
        }


    }

    void signinApiCall(String phone, String TAG) {
        getMvpView().showLoading();
        Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_SIGN_IN)
                .addBodyParameter("phone", phone)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject responseHeader = response.getJSONObject("ResponseHeader");
                            JSONObject responseBody = response.getJSONObject("ResponseBody");
                            Log.e("app", responseBody.toString());
                            String responseMessage = responseBody.getString("ResponseMessage");
                            if (responseMessage.compareToIgnoreCase(USER_SIGNIN_VERIFICATION_CODE_GENERATED) == 0) {
                                JSONObject data = responseBody.getJSONObject("Data");
                                //do not log the user in just yet.
                                //wait for the user to verify his phone

                                String isActivate = data.getString("is_activate");
                                Boolean isActive = false;
                                if (isActivate.compareToIgnoreCase("1") == 0) {
                                    isActive = true;
                                } else {
                                    isActive = false;
                                }


                                TemporaryData temporaryData = new TemporaryData();

                                temporaryData.setIndex("id");
                                temporaryData.setLnValue(data.getLong("id"));
                                getDataManager().updateCommonData(temporaryData);

                                temporaryData.setIndex("first_name");
                                temporaryData.setStrValue(data.getString("first_name"));
                                getDataManager().updateCommonData(temporaryData);

                                temporaryData = new TemporaryData();
                                temporaryData.setIndex("last_name");
                                temporaryData.setStrValue(data.getString("last_name"));
                                getDataManager().updateCommonData(temporaryData);

                                temporaryData = new TemporaryData();
                                temporaryData.setIndex("phone");
                                temporaryData.setStrValue(data.getString("phone"));
                                getDataManager().updateCommonData(temporaryData);

                                getDataManager().updateUserInfo(
                                        "",
                                        data.getLong("id"),
                                        DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT,
                                        data.getString("first_name"),
                                        data.getString("last_name"),
                                        data.getString("address"),
                                        data.getString("biography"),
                                        data.getString("phone"),
                                        "",
                                        data.getString("activation_code"),
                                        isActive);

                                if (!isViewAttached()) {
                                    return;
                                }

                                getMvpView().hideLoading();
                                getMvpView().onFragmentDetached(TAG);

                            } else if (responseMessage.compareToIgnoreCase(USER_NOT_ACTIVATED) == 0) {
                                //some error has occurred
                                //show dialog with error
                                //getMvpView().showMessage(responseMessage);
                                getMvpView().hideLoading();
                                String title = getMvpView().getContext().getResources().getString(R.string.user_not_activated_title);
                                String message = getMvpView().getContext().getResources().getString(R.string.user_not_activated_message);
                                getMvpView().showAlertDialogMessage(title, message);
                            } else if (responseMessage.compareToIgnoreCase(USER_NOT_FOUND) == 0) {
                                //some error has occurred
                                //show dialog with error
                                //getMvpView().showMessage(responseMessage);
                                getMvpView().hideLoading();
                                String title = getMvpView().getContext().getResources().getString(R.string.user_not_registered_title);
                                String message = getMvpView().getContext().getResources().getString(R.string.user_not_registered_message);
                                getMvpView().showAlertDialogMessage(title, message);
                            } else {
                                getMvpView().hideLoading();
                                String title = getMvpView().getContext().getResources().getString(R.string.error_title);
                                String message = getMvpView().getContext().getResources().getString(R.string.error_message);
                                getMvpView().showAlertDialogMessage(title, message);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();

                            if (!isViewAttached()) {
                                return;
                            }

                            getMvpView().hideLoading();

                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        anError.printStackTrace();
                        Log.e("error", anError.getErrorBody());

                        if (!isViewAttached()) {
                            return;
                        }

                        getMvpView().hideLoading();
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(anError.getErrorBody());

                            //handleApiError(anError);
                            //getMvpView().showMessage(jsonObject.getJSONObject("ResponseBody").getString("ResponseMessage"));

                            String title = getMvpView().getContext().getResources().getString(R.string.error_title);
                            String message = getMvpView().getContext().getResources().getString(R.string.error_message);
                            getMvpView().showAlertDialogMessage(title, message);
                        } catch (JSONException e) {
                            //e.printStackTrace();
                            //handleApiError(anError);
                            String title = getMvpView().getContext().getResources().getString(R.string.error_title);
                            String message = getMvpView().getContext().getResources().getString(R.string.error_message);
                            getMvpView().showAlertDialogMessage(title, message);
                        }

                    }
                });
    }
}