package com.CalSocial.ui.onboarding.phoneCode;

import android.util.Log;

import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.CalSocial.R;
import com.CalSocial.data.DataManager;
import com.CalSocial.data.network.ApiEndPoint;
import com.CalSocial.ui.base.BasePresenter;
import com.CalSocial.ui.onboarding.signIn.SigninFragment;
import com.CalSocial.ui.onboarding.signUp.SignupFragment;
import com.CalSocial.utils.AppUtils;
import com.CalSocial.utils.rx.SchedulerProvider;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class PhoneCodePresenter<V extends PhoneCodeMvpView> extends BasePresenter<V> implements PhoneCodeMvpPresenter<V> {

    private String USER_VERIFICATION_SUCCESSFUL = "User account activated successfully";
    private String USER_ALREADY_ACTIVATED = "User is already active";
    private String INVALID_CODE = "Please enter valid activation code";
    private String LOGIN_VERIFICATION_SUCCESSFUL = "Log In Successful!";
    private String LOGIN_VERIFICATION_INCORRECT_CODE = "Code you entered is incorrect , enter correct one";
    private String USER_NOT_ACTIVATED = "User is not activated , activate your account first via signup";

    @Inject
    public PhoneCodePresenter(DataManager dataManager,
                              SchedulerProvider schedulerProvider,
                              CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onPhoneContinueClicked(String[] digits, String TAG) {
        String title = "";
        String message = "";

        if (digits[0] == null || digits[0].isEmpty()) {
            //getMvpView().onError(R.string.empty_phone_digit);
            title = getMvpView().getContext().getResources().getString(R.string.error_title);
            message = getMvpView().getContext().getResources().getString(R.string.empty_phone_digit);
            getMvpView().showAlertDialogMessage(title, message);
            return;
        }

        if (digits[1] == null || digits[1].isEmpty()) {
            //getMvpView().onError(R.string.empty_phone_digit);
            title = getMvpView().getContext().getResources().getString(R.string.error_title);
            message = getMvpView().getContext().getResources().getString(R.string.empty_phone_digit);
            getMvpView().showAlertDialogMessage(title, message);
            return;
        }

        if (digits[2] == null || digits[2].isEmpty()) {
            //getMvpView().onError(R.string.empty_phone_digit);
            title = getMvpView().getContext().getResources().getString(R.string.error_title);
            message = getMvpView().getContext().getResources().getString(R.string.empty_phone_digit);
            getMvpView().showAlertDialogMessage(title, message);
            return;
        }

        if (digits[3] == null || digits[3].isEmpty()) {
            //getMvpView().onError(R.string.empty_phone_digit);
            title = getMvpView().getContext().getResources().getString(R.string.error_title);
            message = getMvpView().getContext().getResources().getString(R.string.empty_phone_digit);
            getMvpView().showAlertDialogMessage(title, message);
            return;
        }

        if (digits[4] == null || digits[4].isEmpty()) {
            //getMvpView().onError(R.string.empty_phone_digit);
            title = getMvpView().getContext().getResources().getString(R.string.error_title);
            message = getMvpView().getContext().getResources().getString(R.string.empty_phone_digit);
            getMvpView().showAlertDialogMessage(title, message);
            return;
        }

        if (digits[5] == null || digits[5].isEmpty()) {
            //getMvpView().onError(R.string.empty_phone_digit);
            title = getMvpView().getContext().getResources().getString(R.string.error_title);
            message = getMvpView().getContext().getResources().getString(R.string.empty_phone_digit);
            getMvpView().showAlertDialogMessage(title, message);
            return;
        }


        String code = "";
        for (int i = 0; i < digits.length; i++) {
            code = code.concat(digits[i]);
        }
        getMvpView().hideLoading();
        getMvpView().onFragmentDetached(TAG);
        /*
        Long userId = getDataManager().getCurrentUserId();
        if (AppUtils.sourceScreenFragment.compareToIgnoreCase(SignupFragment.TAG) == 0 && AppUtils.destinationScreenFragment.compareToIgnoreCase(PhoneCodeFragment.TAG) == 0) {
            verifyAfterSignup(code, userId, TAG);
        } else if (AppUtils.sourceScreenFragment.compareToIgnoreCase(SigninFragment.TAG) == 0 && AppUtils.destinationScreenFragment.compareToIgnoreCase(PhoneCodeFragment.TAG) == 0) {
            verifyAfterSignin(code, userId, TAG);
        }*/

    }

    @Override
    public void onResendCodeClicked() {
       /* if (AppUtils.sourceScreenFragment.compareToIgnoreCase(SignupFragment.TAG) == 0 && AppUtils.destinationScreenFragment.compareToIgnoreCase(PhoneCodeFragment.TAG) == 0) {
            String firstName = getDataManager().getCommonData("first_name").getStrValue();
            String lastName = getDataManager().getCommonData("last_name").getStrValue();
            String phone = getDataManager().getCommonData("phone").getStrValue();

            if (firstName != null && lastName != null && phone != null) {
                regenerateCodeAfterSignup(firstName, lastName, phone);
            } else {
                String title = getMvpView().getContext().getResources().getString(R.string.error_title);
                String message = getMvpView().getContext().getResources().getString(R.string.error_message);
                getMvpView().showAlertDialogMessage(title, message);
            }

        } else if (AppUtils.sourceScreenFragment.compareToIgnoreCase(SigninFragment.TAG) == 0 && AppUtils.destinationScreenFragment.compareToIgnoreCase(PhoneCodeFragment.TAG) == 0) {


            String phone = getDataManager().getCommonData("phone").getStrValue();
            if (phone != null) {
                //regenerateCodeAfterSignin(phone);
            } else {
                String title = getMvpView().getContext().getResources().getString(R.string.error_title);
                String message = getMvpView().getContext().getResources().getString(R.string.error_message);
                getMvpView().showAlertDialogMessage(title, message);
            }


        }*/


    }

    @Override
    public void onSmsCodeReceived(String code) {

        if (code.trim().length() == 6) {
            String[] digits = {
                    code.trim().charAt(0) + "",
                    code.trim().charAt(1) + "",
                    code.trim().charAt(2) + "",
                    code.trim().charAt(3) + "",
                    code.trim().charAt(4) + "",
                    code.trim().charAt(5) + ""
            };
            getMvpView().populateDigits(digits);
        }


    }

    private void verifyAfterSignup(String code, Long userId, String TAG) {
        getMvpView().showLoading();
        String url = ApiEndPoint.ENDPOINT_VERIFY_PHONE_CODE_AFTER_SIGN_UP + "?code=" + code + "&user_id=" + userId.toString();
        Rx2AndroidNetworking.get(url)
                //.addPathParameter("user_id", userId.toString())
                //.addPathParameter("code", code)
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
                            if (responseMessage.compareToIgnoreCase(USER_VERIFICATION_SUCCESSFUL) == 0) {
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

                                getDataManager().updateUserInfo(
                                        data.getString("token"),
                                        data.getLong("id"),
                                        DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER,
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
                                String title = getMvpView().getContext().getResources().getString(R.string.user_account_activation_successful_title);
                                String message = getMvpView().getContext().getResources().getString(R.string.user_account_activation_successful_message);
                                getMvpView().showAlertDialogMessage(title, message);
                                getMvpView().onFragmentDetached(TAG);

                            } else if (responseMessage.compareToIgnoreCase(USER_ALREADY_ACTIVATED) == 0) {
                                //some error has occurred
                                //show error message
                                getMvpView().hideLoading();
                                String title = getMvpView().getContext().getResources().getString(R.string.user_already_active_title);
                                String message = getMvpView().getContext().getResources().getString(R.string.user_already_active_message);
                                getMvpView().showAlertDialogMessage(title, message);
                            } else if (responseMessage.compareToIgnoreCase(INVALID_CODE) == 0) {
                                //some error has occurred
                                //show error message
                                getMvpView().hideLoading();
                                String title = getMvpView().getContext().getResources().getString(R.string.invalid_code_title);
                                String message = getMvpView().getContext().getResources().getString(R.string.invalid_code_message);
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
                        if (!isViewAttached()) {
                            return;
                        }

                        getMvpView().hideLoading();
                        //handleApiError(anError);
                        String title = getMvpView().getContext().getResources().getString(R.string.error_title);
                        String message = getMvpView().getContext().getResources().getString(R.string.error_message);
                        getMvpView().showAlertDialogMessage(title, message);

                    }
                });
    }

    private void verifyAfterSignin(String code, Long userId, String TAG) {

        getMvpView().showLoading();
        String url = ApiEndPoint.ENDPOINT_VERIFY_PHONE_CODE_AFTER_SIGN_IN;
        Rx2AndroidNetworking.post(url)
                .addBodyParameter("user_id", userId.toString())
                .addBodyParameter("code", code)
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
                            if (responseMessage.compareToIgnoreCase(LOGIN_VERIFICATION_SUCCESSFUL) == 0) {
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

                                getDataManager().updateUserInfo(
                                        data.getString("token"),
                                        data.getLong("id"),
                                        DataManager.LoggedInMode.LOGGED_IN_MODE_SERVER,
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
                                String title = getMvpView().getContext().getResources().getString(R.string.user_login_verification_successful_title);
                                String message = getMvpView().getContext().getResources().getString(R.string.user_login_verification_successful_message);
                                getMvpView().showAlertDialogMessage(title, message);
                                getMvpView().onFragmentDetached(TAG);

                            } else if (responseMessage.compareToIgnoreCase(LOGIN_VERIFICATION_INCORRECT_CODE) == 0) {
                                //some error has occurred
                                //show error message
                                getMvpView().hideLoading();
                                String title = getMvpView().getContext().getResources().getString(R.string.invalid_code_title);
                                String message = getMvpView().getContext().getResources().getString(R.string.invalid_code_message);
                                getMvpView().showAlertDialogMessage(title, message);
                            } else if (responseMessage.compareToIgnoreCase(USER_NOT_ACTIVATED) == 0) {
                                //some error has occurred
                                //show error message
                                getMvpView().showMessage(responseMessage);
                                getMvpView().hideLoading();
                                String title = getMvpView().getContext().getResources().getString(R.string.user_not_activated_title);
                                String message = getMvpView().getContext().getResources().getString(R.string.user_not_activated_message);
                                getMvpView().showAlertDialogMessage(title, message);
                            }


                        } catch (JSONException e) {
                            //e.printStackTrace();

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
                        //anError.printStackTrace();
                        if (!isViewAttached()) {
                            return;
                        }

                        getMvpView().hideLoading();
                        //handleApiError(anError);
                        String title = getMvpView().getContext().getResources().getString(R.string.error_title);
                        String message = getMvpView().getContext().getResources().getString(R.string.error_message);
                        getMvpView().showAlertDialogMessage(title, message);

                    }
                });
    }

    private void regenerateCodeAfterSignup(String firstName, String lastName, String phone) {
        String USER_REGISTRATION_SUCCESS = "User registered successfully.";
        String USER_REGISTRATION_FAILURE = "The phone has already been taken.";

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

                                getDataManager().updateUserInfo(
                                        data.getString("token"),
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
                        //anError.printStackTrace();
                        //Log.e("error", anError.getErrorBody());

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
                                String title = getMvpView().getContext().getResources().getString(R.string.error_title);
                                String message = getMvpView().getContext().getResources().getString(R.string.error_message);
                                getMvpView().showAlertDialogMessage(title, message);

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            //handleApiError(anError);
                        }

                    }
                });
    }

    private void regenerateCodeAfterSignin(String phone) {
        String USER_SIGNIN_VERIFICATION_CODE_GENERATED = "Congrats :) verification code against your phone created successfully";
        String USER_NOT_ACTIVATED = "User is not activated , activate your account first via signup";

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

                            } else if (responseMessage.compareToIgnoreCase(USER_NOT_ACTIVATED) == 0) {
                                //some error has occurred
                                //show dialog with error
                                //getMvpView().showMessage(responseMessage);
                                getMvpView().hideLoading();
                                String title = getMvpView().getContext().getResources().getString(R.string.error_title);
                                String message = getMvpView().getContext().getResources().getString(R.string.error_message);
                                getMvpView().showAlertDialogMessage(title, message);
                            }


                        } catch (JSONException e) {
                            //e.printStackTrace();

                            if (!isViewAttached()) {
                                return;
                            }

                            getMvpView().hideLoading();

                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        //anError.printStackTrace();
                        //Log.e("error", anError.getErrorBody());

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