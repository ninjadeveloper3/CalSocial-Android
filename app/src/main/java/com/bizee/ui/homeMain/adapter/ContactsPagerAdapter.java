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

package com.CalSocial.ui.homeMain.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.CalSocial.ui.homeMain.contactsCalSocialNetworkFragment.ContactsCalSocialNetworkFragment;
import com.CalSocial.ui.homeMain.contactsHiveFragment.ContactsHiveFragment;
import com.CalSocial.ui.homeMain.contactsPhoneFragment.ContactsPhoneFragment;
import com.CalSocial.ui.homeMain.contactsSwarmFragment.ContactsSwarmFragment;

public class ContactsPagerAdapter extends FragmentStatePagerAdapter {

    private int mTabCount;
    private FragmentManager fragmentManager;
    private int mode = 0; //0 for showing the hive and swarm fragments, 1 for showing the phone contacts and CalSocial network fragments

    public ContactsPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.mTabCount = 0;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public Fragment getItem(int position) {

        if (mode == 0) {
            switch (position) {
                case 0:
                    return ContactsHiveFragment.newInstance();
                case 1:
                    return ContactsSwarmFragment.newInstance();
                default:
                    return null;
            }
        } else {
            switch (position) {
                case 0:
                    return ContactsPhoneFragment.newInstance();
                case 1:
                    return ContactsCalSocialNetworkFragment.newInstance();
                default:
                    return null;
            }
        }

    }

    @Override
    public int getCount() {
        return mTabCount;
    }

    public void setCount(int count) {
        mTabCount = count;
    }

    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
}
