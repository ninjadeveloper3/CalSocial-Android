package com.CalSocial.ui.onboarding.additionalInfo;


import com.CalSocial.R;
import com.CalSocial.data.DataManager;
import com.CalSocial.ui.base.BasePresenter;
import com.CalSocial.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class SignupAdditionalInfoPresenter<V extends SignupAdditionalInfoMvpView> extends BasePresenter<V> implements SignupAdditionalInfoMvpPresenter<V> {

    private String USER_DETAILS_UPDATED = "User.details_added";

    @Inject
    public SignupAdditionalInfoPresenter(DataManager dataManager,
                                         SchedulerProvider schedulerProvider,
                                         CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onSaveContinueClicked(String location, String bio, String TAG) {
        String title = "";
        String message = "";
        if (location == null || location.isEmpty()) {
            title = getMvpView().getContext().getResources().getString(R.string.error_title);
            message = getMvpView().getContext().getResources().getString(R.string.empty_location);
            getMvpView().showAlertDialogMessage(title, message);
            return;
        }

        if (bio == null || bio.isEmpty()) {
            title = getMvpView().getContext().getResources().getString(R.string.error_title);
            message = getMvpView().getContext().getResources().getString(R.string.empty_bio);
            getMvpView().showAlertDialogMessage(title, message);
            return;
        }

        getMvpView().showLoading();
        getMvpView().hideLoading();

        getMvpView().onFragmentDetached(TAG);

        /*Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_ADDITIONAL_INFO)
                .addBodyParameter("biography", bio)
                .addBodyParameter("address", location)
                .addHeaders("Authorization", "Bearer " + getDataManager().getApiHeader().getProtectedApiHeader().getAccessToken())
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
                            if (responseMessage.compareToIgnoreCase(USER_DETAILS_UPDATED) == 0) {
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

                                //I do not want the access token to be overwritten by some other value,
                                //so i am giving the already saved access token to persist that value.
                                getDataManager().updateUserInfo(
                                        getDataManager().getApiHeader().getProtectedApiHeader().getAccessToken(),
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
                                String title = getMvpView().getContext().getResources().getString(R.string.additional_info_updated_title);
                                String message = getMvpView().getContext().getResources().getString(R.string.additional_info_updated_message);
                                getMvpView().showAlertDialogMessage(title, message);
                                getMvpView().onFragmentDetached(TAG);

                            } else {
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
                });*/

    }

    @Override
    public void onSkipForNowClicked(String TAG) {
        getMvpView().onFragmentDetached(TAG);
    }
}