package com.CalSocial.ui.homeMain.contacts;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.CalSocial.data.DataManager;
import com.CalSocial.ui.base.BasePresenter;
import com.CalSocial.ui.homeMain.contactsHiveFragment.ContactsHiveFragment;
import com.CalSocial.ui.homeMain.contactsSwarmFragment.ContactsSwarmFragment;
import com.CalSocial.utils.rx.SchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class ContactsPresenter<V extends ContactsMvpView> extends BasePresenter<V> implements ContactsMvpPresenter<V> {

    @Inject
    public ContactsPresenter(DataManager dataManager,
                             SchedulerProvider schedulerProvider,
                             CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void editButtonClicked(FragmentManager fragmentManager) {

        List<Fragment> fragments = fragmentManager.getFragments();
        for (int i = 0; i < fragments.size(); i++) {
            if (fragments.get(i) instanceof ContactsHiveFragment) {
                getMvpView().showHiveEditScreen(fragments.get(i));
            } else if (fragments.get(i) instanceof ContactsSwarmFragment) {
                getMvpView().showSwarmEditScreen(fragments.get(i));
            }
        }


    }

    @Override
    public void cancelButtonClicked(FragmentManager fragmentManager) {
        List<Fragment> fragments = fragmentManager.getFragments();
        for (int i = 0; i < fragments.size(); i++) {
            if (fragments.get(i) instanceof ContactsHiveFragment) {
                getMvpView().showHiveViewScreen(fragments.get(i));
            } else if (fragments.get(i) instanceof ContactsSwarmFragment) {
                getMvpView().showSwarmViewScreen(fragments.get(i));
            }
        }

    }
}
