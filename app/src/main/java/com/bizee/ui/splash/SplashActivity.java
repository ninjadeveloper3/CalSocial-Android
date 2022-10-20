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

package com.CalSocial.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.CalSocial.R;
import com.CalSocial.ui.base.BaseActivity;
import com.CalSocial.ui.homeMain.HomeMainActivity;
import com.CalSocial.ui.onboarding.OnboardingActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;


/**
 * Created by janisharali on 27/01/17.
 */

public class SplashActivity extends BaseActivity implements SplashMvpView {

    @Inject
    SplashMvpPresenter<SplashMvpView> mPresenter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, SplashActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onboarding_activity_splash);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(SplashActivity.this);
    }


    @Override
    public void startSyncService() {
//        SyncService.start(this);
    }

    @Override
    public void openOnboardingActivity() {
        Intent intent = OnboardingActivity.getStartIntent(SplashActivity.this);
        //Intent intent = HomeMainActivity.getStartIntent(SplashActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    public void openHomeActivity() {
        Intent intent = HomeMainActivity.getStartIntent(SplashActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected void setUp() {

    }


}
