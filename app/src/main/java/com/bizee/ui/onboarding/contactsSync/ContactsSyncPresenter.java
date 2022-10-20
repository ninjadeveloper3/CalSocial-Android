package com.CalSocial.ui.onboarding.contactsSync;

import android.content.Context;

import com.CalSocial.R;
import com.CalSocial.data.DataManager;
import com.CalSocial.ui.base.BasePresenter;
import com.CalSocial.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class ContactsSyncPresenter<V extends ContactsSyncMvpView> extends BasePresenter<V> implements ContactsSyncMvpPresenter<V> {

    @Inject
    public ContactsSyncPresenter(DataManager dataManager,
                                 SchedulerProvider schedulerProvider,
                                 CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onSyncContactsClicked(String TAG, Context context) {
        //TODO: get contacts here and send to the server
        //start the background syncing service

        //getMvpView().onFragmentDetached(TAG);

        /*Contact contact = new Contact(1L, "Mustafa", 1L, "123456789", "", false, true);
        getDataManager().insertContact(contact);
*/
        /*Observable<List<Contact>> contacts = getDataManager().getAllContacts();
        contacts.*/

        getMvpView().informUserOfContactsSyncDialog(context.getResources().getString(R.string.contacts_sync_confirm_title), context.getResources().getString(R.string.contacts_sync_confirm_message));

    }

    @Override
    public void onSkipForNowClicked(String TAG) {

        getMvpView().onFragmentDetached(TAG);

    }

    @Override
    public void startContactsSyncing() {
        getMvpView().startSyncContacts(getDataManager().getApiHeader().getProtectedApiHeader().getAccessToken());

    }

    @Override
    public void alertUser(Context context) {
        getMvpView().alertUserDialog(context.getResources().getString(R.string.contacts_access_not_allowed_title), context.getResources().getString(R.string.contacts_access_not_allowed_message));
    }
}