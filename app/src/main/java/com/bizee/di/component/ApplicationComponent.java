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

package com.CalSocial.di.component;

import android.app.Application;
import android.content.Context;

import com.CalSocial.CalSocialApp;
import com.CalSocial.data.DataManager;
import com.CalSocial.di.ApplicationContext;
import com.CalSocial.di.module.ApplicationModule;
import com.CalSocial.service.SyncService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by janisharali on 27/01/17.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(CalSocialApp app);

    void inject(SyncService service);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();
}