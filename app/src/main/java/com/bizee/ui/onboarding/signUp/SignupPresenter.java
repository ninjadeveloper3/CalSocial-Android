package com.CalSocial.ui.onboarding.signUp;


import android.util.Log;

import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.CalSocial.R;
import com.CalSocial.data.DataManager;
import com.CalSocial.data.TemporaryData;
import com.CalSocial.data.network.ApiEndPoint;
import com.CalSocial.ui.base.BasePresenter;
import com.CalSocial.utils.AppUtils;
import com.CalSocial.utils.rx.SchedulerProvider;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class SignupPresenter<V extends SignupMvpView> extends BasePresenter<V> implements SignupMvpPresenter<V> {

    private String USER_REGISTRATION_SUCCESS = "User registered successfully.";
    private String USER_REGISTRATION_FAILURE = "The phone has already been taken.";

    @Inject
    public SignupPresenter(DataManager dataManager,
                           SchedulerProvider schedulerProvider,
                           CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onAgreeContinueClick(String firstName, String lastName, String phone, String TAG) {

        String title = "";
        String message = "";

        if (firstName == null || firstName.isEmpty()) {
            //getMvpView().onError(R.string.empty_first_name);
            title = getMvpView().getContext().getResources().getString(R.string.error_title);
            message = getMvpView().getContext().getResources().getString(R.string.empty_first_name);
            getMvpView().showAlertDialogMessage(title, message);

            return;
        }

        if (lastName == null || lastName.isEmpty()) {
            //getMvpView().onError(R.string.empty_last_name);
            title = getMvpView().getContext().getResources().getString(R.string.error_title);
            message = getMvpView().getContext().getResources().getString(R.string.empty_last_name);
            getMvpView().showAlertDialogMessage(title, message);
            return;
        }


        if (phone == null || phone.isEmpty()) {
            //getMvpView().onError(R.string.empty_phone);
            title = getMvpView().getContext().getResources().getString(R.string.error_title);
            message = getMvpView().getContext().getResources().getString(R.string.empty_phone);
            getMvpView().showAlertDialogMessage(title, message);
            return;
        }

        String formattedPhone = AppUtils.getPhoneNumberUSCANFormat(phone);
        if (formattedPhone != null) {
            //signupApiCall(firstName, lastName, formattedPhone, TAG);
            getMvpView().hideLoading();
            getMvpView().onFragmentDetached(TAG);
        } else {
            title = getMvpView().getContext().getResources().getString(R.string.invalid_us_can_number_title);
            message = getMvpView().getContext().getResources().getString(R.string.invalid_us_can_number_message);
            getMvpView().showAlertDialogMessage(title, message);
        }

    }

    @Override
    public void onSigninClicked() {

        getMvpView().openSignin();
    }

    @Override
    public void onTermsLinkClicked() {

        getMvpView().openTermsLink();
    }

    @Override
    public void onPolicyLinkClicked() {

        getMvpView().openPrivacyPolicyLink();
    }

    void signupApiCall(String firstName, String lastName, String phone, String TAG) {
        getMvpView().showLoading();

        Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_SIGN_UP)
                .addBodyParameter("first_name", firstName)
                .addBodyParameter("last_name", lastName)
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
                            if (responseMessage.compareToIgnoreCase(USER_REGISTRATION_SUCCESS) == 0) {
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

                                //The accessToken will not be saved on sign up
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

                            } else if (responseMessage.compareToIgnoreCase(USER_REGISTRATION_FAILURE) == 0) {
                                //some error has occurred
                                //show dialog with error
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
                            String title = getMvpView().getContext().getResources().getString(R.string.error_title);
                            String message = getMvpView().getContext().getResources().getString(R.string.error_message);
                            getMvpView().showAlertDialogMessage(title, message);

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
                            String responseMessage = jsonObject.getJSONObject("ResponseBody").getString("ResponseMessage");
                            if (responseMessage != null
                                    && responseMessage.compareToIgnoreCase(USER_REGISTRATION_FAILURE) == 0) {
                                String title = getMvpView().getContext().getResources().getString(R.string.phone_already_registered_title);
                                String message = getMvpView().getContext().getResources().getString(R.string.phone_already_registered_message);
                                getMvpView().showAlertDialogMessage(title, message);

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            handleApiError(anError);
                        }

                    }
                });
    }

}