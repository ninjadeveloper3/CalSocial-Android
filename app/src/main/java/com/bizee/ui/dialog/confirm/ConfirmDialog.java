/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.CalSocial.ui.dialog.confirm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;

import com.CalSocial.R;
import com.CalSocial.di.component.ActivityComponent;
import com.CalSocial.event.ConfirmDiaglogEvent;
import com.CalSocial.ui.base.BaseDialog;
import com.CalSocial.utils.AppConstants;
import com.CalSocial.widget.CalSocialButton;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConfirmDialog extends BaseDialog implements ConfirmDialogMvpView {

    private static final String TAG = "ConfirmDialog";

    @Inject
    ConfirmDialogMvpPresenter<ConfirmDialogMvpView> mPresenter;

    @BindView(R.id.alertTitle)
    TextView alertTitleTV;

    @BindView(R.id.alertText)
    TextView alertTextTV;

    @BindView(R.id.yesButton)
    CalSocialButton yesButton;

    @BindView(R.id.noButton)
    CalSocialButton noButton;

    private String alertTitle;
    private String alertMessage;

    public static ConfirmDialog newInstance() {
        ConfirmDialog fragment = new ConfirmDialog();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_confirm_message, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {

            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);

            if (alertMessage != null) {
                alertTextTV.setText(alertMessage);
            }

            if (alertTitle != null) {
                alertTitleTV.setText(alertTitle);
            }

            ((Button) yesButton.findViewById(R.id.CalSocialButton)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //the user has agreed to send his/her contacts to the server
                    mPresenter.onPositiveClicked();
                }
            });

            ((Button) noButton.findViewById(R.id.CalSocialButton)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //the user has not agreed to send his/her contacts to the server
                    mPresenter.onNegativeClicked();
                }
            });
        }

        return view;
    }

    public void show(FragmentManager fragmentManager) {
        super.show(fragmentManager, TAG);
    }


    @Override
    protected void setUp(View view) {

    }

    @Override
    public void negativeActionTaken() {
        super.dismissDialog(TAG + "-" + AppConstants.IGNORE_SECOND_CHECK);
        EventBus.getDefault().postSticky(new ConfirmDiaglogEvent(ConfirmDiaglogEvent.NEGATIVE_ACTION));
    }

    @Override
    public void positiveActionTaken() {
        super.dismissDialog(TAG + "-" + AppConstants.IGNORE_SECOND_CHECK);
        EventBus.getDefault().postSticky(new ConfirmDiaglogEvent(ConfirmDiaglogEvent.POSITIVE_ACTION));

    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }

    public void setAlertTitle(String title) {
        alertTitle = title;


    }

    public void setAlertMessage(String message) {
        alertMessage = message;

    }
}