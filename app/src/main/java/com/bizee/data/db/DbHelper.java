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
import com.CalSocial.data.db.model.Event;
import com.CalSocial.data.db.model.Swarm;
import com.CalSocial.data.db.model.SwarmHasContact;
import com.CalSocial.data.db.model.User;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by janisharali on 08/12/16.
 */

public interface DbHelper {

    Observable<Long> insertUser(final User user);

    Observable<List<User>> getAllUsers();

    Observable<Long> insertContact(final Contact contact);

    Observable<List<Contact>> getAllContacts();

    Observable<Long> insertEvent(final Event event);

    Observable<Long> insertSwarm(final Swarm swarm);

    Observable<Long> insertContactHasEvent(final ContactHasEvent contactHasEvent);

    Observable<Long> insertSwarmHasContact(final SwarmHasContact swarmHasContact);

}
