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

package com.CalSocial.data.db;

import com.CalSocial.data.db.model.Contact;
import com.CalSocial.data.db.model.ContactHasEvent;
import com.CalSocial.data.db.model.DaoMaster;
import com.CalSocial.data.db.model.DaoSession;
import com.CalSocial.data.db.model.Event;
import com.CalSocial.data.db.model.Swarm;
import com.CalSocial.data.db.model.SwarmHasContact;
import com.CalSocial.data.db.model.User;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;


/**
 * Created by janisharali on 08/12/16.
 */

@Singleton
public class AppDbHelper implements DbHelper {

    private final DaoSession mDaoSession;

    @Inject
    public AppDbHelper(DbOpenHelper dbOpenHelper) {
        mDaoSession = new DaoMaster(dbOpenHelper.getWritableDb()).newSession();
    }

    @Override
    public Observable<Long> insertUser(final User user) {
        return Observable.fromCallable(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return mDaoSession.getUserDao().insert(user);
            }
        });
    }

    @Override
    public Observable<List<User>> getAllUsers() {
        return Observable.fromCallable(new Callable<List<User>>() {
            @Override
            public List<User> call() throws Exception {
                return mDaoSession.getUserDao().loadAll();
            }
        });
    }

    @Override
    public Observable<Long> insertContact(final Contact contact) {
        return Observable.fromCallable(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return mDaoSession.getContactDao().insert(contact);
            }
        });
    }

    @Override
    public Observable<List<Contact>> getAllContacts() {
        return Observable.fromCallable(new Callable<List<Contact>>() {
            @Override
            public List<Contact> call() throws Exception {
                return mDaoSession.getContactDao().loadAll();
            }
        });
    }

    @Override
    public Observable<Long> insertEvent(final Event event) {
        return Observable.fromCallable(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return mDaoSession.getEventDao().insert(event);
            }
        });
    }

    @Override
    public Observable<Long> insertSwarm(final Swarm swarm) {
        return Observable.fromCallable(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return mDaoSession.getSwarmDao().insert(swarm);
            }
        });
    }

    @Override
    public Observable<Long> insertContactHasEvent(final ContactHasEvent contactHasEvent) {
        return Observable.fromCallable(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return mDaoSession.getContactHasEventDao().insert(contactHasEvent);
            }
        });
    }

    @Override
    public Observable<Long> insertSwarmHasContact(final SwarmHasContact swarmHasContact) {
        return Observable.fromCallable(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return mDaoSession.getSwarmHasContactDao().insert(swarmHasContact);
            }
        });
    }
}
