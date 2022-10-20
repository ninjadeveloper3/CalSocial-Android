package com.CalSocial.ui.onboarding.contactsSync;

import android.Manifest;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.CalSocial.R;
import com.CalSocial.di.component.ActivityComponent;
import com.CalSocial.event.ConfirmDiaglogEvent;
import com.CalSocial.service.ContactsSyncService;
import com.CalSocial.ui.base.BaseFragment;
import com.CalSocial.ui.dialog.alert.AlertDialog;
import com.CalSocial.ui.dialog.confirm.ConfirmDialog;
import com.CalSocial.utils.AppConstants;
import com.CalSocial.widget.CalSocialButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ContactsSyncFragment extends BaseFragment implements ContactsSyncMvpView {

    public static final String TAG = "ContactsSyncFragment";

    @Inject
    ContactsSyncPresenter<ContactsSyncMvpView> mPresenter;

    @BindView(R.id.skipForNow)
    TextView skipForNow;

    @BindView(R.id.sync_contacts)
    CalSocialButton syncContacts;

    public static ContactsSyncFragment newInstance() {
        Bundle args = new Bundle();
        ContactsSyncFragment fragment = new ContactsSyncFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.onboarding_fragment_sync_contacts, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
            skipForNow.setTypeface(null, Typeface.BOLD);

        }

        return view;
    }

    @Override
    protected void setUp(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }

    @OnClick(R.id.CalSocialButton)
    void onSyncContactsClicked() {

        if (!getBaseActivity().hasPermission(Manifest.permission.READ_CONTACTS) && !getBaseActivity().hasPermission(Manifest.permission.WRITE_CONTACTS)) {
            mPresenter.alertUser(getActivity());
            mPresenter.onSkipForNowClicked(TAG + "-" + AppConstants.CALENDER_SYNC);
        } else {
            //all of the required permissions have been granted
            mPresenter.onSyncContactsClicked(TAG + "-" + AppConstants.CONTACTS_FOUND, getActivity());
        }
    }

    @OnClick(R.id.skipForNow)
    void onSkipForNowClicked() {
        mPresenter.onSkipForNowClicked(TAG + "-" + AppConstants.CALENDER_SYNC);
    }

    @Override
    public void onFragmentDetached(String TAG) {
        hideLoading();
        getBaseActivity().onFragmentDetached(TAG);
    }

    @Override
    public void startSyncContacts(String accessToken) {

        Intent contactSyncServiceIntent = new Intent(getActivity(), ContactsSyncService.class);
        contactSyncServiceIntent.putExtra("access_token", accessToken);
        contactSyncServiceIntent.putExtra("first_sync", true);
        getActivity().startService(contactSyncServiceIntent);

    }

    @Override
    public void informUserOfContactsSyncDialog(String title, String message) {
        ConfirmDialog confirmDialog = ConfirmDialog.newInstance();
        confirmDialog.setAlertTitle(title);
        confirmDialog.setAlertMessage(message);
        confirmDialog.show(getFragmentManager());
    }

    @Override
    public void alertUserDialog(String title, String message) {
        AlertDialog alertDialog = AlertDialog.newInstance();
        alertDialog.setAlertTitle(title);
        alertDialog.setAlertMessage(message);
        alertDialog.show(getFragmentManager());
    }


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true, priority = 1)
    public void onConfirmDialogEvent(ConfirmDiaglogEvent event) {
        if (event.actionTaken.equalsIgnoreCase(ConfirmDiaglogEvent.POSITIVE_ACTION)) {
            //mPresenter.startContactsSyncing();
            mPresenter.onSkipForNowClicked(TAG + "-" + AppConstants.CALENDER_SYNC);


        } else if (event.actionTaken.equalsIgnoreCase(ConfirmDiaglogEvent.NEGATIVE_ACTION)) {

            mPresenter.onSkipForNowClicked(TAG + "-" + AppConstants.CALENDER_SYNC);
        }

        EventBus.getDefault().removeStickyEvent(event);

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


}