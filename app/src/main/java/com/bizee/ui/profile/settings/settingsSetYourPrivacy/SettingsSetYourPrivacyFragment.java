package com.CalSocial.ui.profile.settings.settingsSetYourPrivacy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.CalSocial.R;
import com.CalSocial.di.component.ActivityComponent;
import com.CalSocial.event.OpenFragmentEvent;
import com.CalSocial.ui.base.BaseFragment;
import com.CalSocial.ui.profile.settings.settingsBlockedUsers.SettingsBlockedUsersFragment;
import com.CalSocial.ui.profile.settings.settingsHiveConnections.SettingsHiveConnectionsFragment;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsSetYourPrivacyFragment extends BaseFragment implements SettingsSetYourPrivacyMvpView {

    public static final String TAG = "SettingsSetYourPrivacyFragment";

    @Inject
    SettingsSetYourPrivacyPresenter<SettingsSetYourPrivacyMvpView> mPresenter;

    public static SettingsSetYourPrivacyFragment newInstance() {
        Bundle args = new Bundle();
        SettingsSetYourPrivacyFragment fragment = new SettingsSetYourPrivacyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profiles_fragment_settings_set_your_privacy_options, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
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

    /*@OnClick(R.id.nav_back_btn)
    void onNavBackClick() {
        getBaseActivity().onFragmentDetached(TAG);
    }*/

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }

    @Override
    public void onFragmentDetached(String TAG) {
        hideLoading();
        getBaseActivity().onFragmentDetached(TAG);
    }

    @OnClick(R.id.hiveConnections)
    void onHiveConnectionsClicked() {
        EventBus.getDefault().postSticky(new OpenFragmentEvent(SettingsSetYourPrivacyFragment.TAG, SettingsHiveConnectionsFragment.TAG));
    }

    @OnClick(R.id.blockedUsers)
    void onBlockedUsersClicked() {
        EventBus.getDefault().postSticky(new OpenFragmentEvent(SettingsSetYourPrivacyFragment.TAG, SettingsBlockedUsersFragment.TAG));
    }


}