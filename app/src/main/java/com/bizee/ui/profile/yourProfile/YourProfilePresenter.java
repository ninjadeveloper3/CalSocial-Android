package com.CalSocial.ui.profile.yourProfile;


import com.CalSocial.data.DataManager;
import com.CalSocial.ui.base.BasePresenter;
import com.CalSocial.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class YourProfilePresenter<V extends YourProfileMvpView> extends BasePresenter<V> implements YourProfileMvpPresenter<V> {

    @Inject
    public YourProfilePresenter(DataManager dataManager,
                                SchedulerProvider schedulerProvider,
                                CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getProfileDetails() {
        //This function will call the relevant API to get the details of the contact.

        String name = "John Smith";
        String location = "Los Angeles, California";
        String profileImage = "";
        String coverImage = "";
        String bioDescription = "This is where the user gets to write whatever they want about themselves its purpose, goals, whatever to X characters.";
        List<String> bucketList = new ArrayList<String>();
        bucketList.add("I want to fly a plane.");
        bucketList.add("I want to drive a F1 car.");
        bucketList.add("I want to own a Ferrari.");

        getMvpView().populateViews(name, location, profileImage, coverImage, bioDescription, bucketList);
    }

    @Override
    public void showSettingsOptionsFragment(String TAG) {
        getMvpView().onFragmentDetached(TAG);

    }


}