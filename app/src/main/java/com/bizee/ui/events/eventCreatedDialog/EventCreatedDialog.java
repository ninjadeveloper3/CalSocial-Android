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

package com.CalSocial.ui.events.eventCreatedDialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentManager;

import com.CalSocial.R;
import com.CalSocial.di.component.ActivityComponent;
import com.CalSocial.ui.base.BaseDialog;
import com.CalSocial.utils.AppConstants;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventCreatedDialog extends BaseDialog implements EventCreatedDialogMvpView {

    private static final String TAG = "EventCreatedDialog";

    @Inject
    EventCreatedDialogMvpPresenter<EventCreatedDialogMvpView> mPresenter;

    public static EventCreatedDialog newInstance() {
        EventCreatedDialog fragment = new EventCreatedDialog();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_event_created, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {

            component.inject(this);

            setUnBinder(ButterKnife.bind(this, view));

            mPresenter.onAttach(this);
        }

        return view;
    }

    public void show(FragmentManager fragmentManager) {
        super.show(fragmentManager, TAG);
    }


    @OnClick(R.id.CalSocialButton)
    void onConfirmSendClicked() {
        mPresenter.onCancelClicked();
    }


    @Override
    protected void setUp(View view) {

    }

    @Override
    public void dismissDialog() {
        super.dismissDialog(TAG + "-" + AppConstants.IGNORE_SECOND_CHECK);
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }
}