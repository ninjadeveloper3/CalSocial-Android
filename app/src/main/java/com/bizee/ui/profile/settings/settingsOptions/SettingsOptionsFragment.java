package com.CalSocial.ui.profile.settings.settingsOptions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.CalSocial.R;
import com.CalSocial.di.component.ActivityComponent;
import com.CalSocial.event.OpenFragmentEvent;
import com.CalSocial.ui.base.BaseFragment;
import com.CalSocial.ui.profile.settings.settingsCalSocialSuggestions.SettingsCalSocialSuggestionsFragment;
import com.CalSocial.ui.profile.settings.settingsContactUs.SettingsContactUsFragment;
import com.CalSocial.ui.profile.settings.settingsNotifications.SettingsNotificationsFragment;
import com.CalSocial.ui.profile.settings.settingsSetYourPrivacy.SettingsSetYourPrivacyFragment;
import com.CalSocial.ui.profile.settings.settingsSyncCalendars.SettingsSyncCalendarsFragment;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsOptionsFragment extends BaseFragment implements SettingsOptionsMvpView {

    public static final String TAG = "SettingsOptionsFragment";

    @Inject
    SettingsOptionsPresenter<SettingsOptionsMvpView> mPresenter;

    public static SettingsOptionsFragment newInstance() {
        Bundle args = new Bundle();
        SettingsOptionsFragment fragment = new SettingsOptionsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profiles_fragment_settings_options, container, false);

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

    @OnClick(R.id.syncCalendar)
    void onSyncCalendarClicked() {
        EventBus.getDefault().postSticky(new OpenFragmentEvent(SettingsOptionsFragment.TAG, SettingsSyncCalendarsFragment.TAG));
    }


    @OnClick(R.id.CalSocialSuggestionPrivacy)
    void onCalSocialSuggestionPrivacyClicked() {
        EventBus.getDefault().postSticky(new OpenFragmentEvent(SettingsOptionsFragment.TAG, SettingsCalSocialSuggestionsFragment.TAG));
    }

    @OnClick(R.id.profileVisibility)
    void onProfileVisibilityClicked() {
        EventBus.getDefault().postSticky(new OpenFragmentEvent(SettingsOptionsFragment.TAG, SettingsSetYourPrivacyFragment.TAG));
    }

    @OnClick(R.id.notifications)
    void onNotificationsClicked() {
        EventBus.getDefault().postSticky(new OpenFragmentEvent(SettingsOptionsFragment.TAG, SettingsNotificationsFragment.TAG));
    }

    @OnClick(R.id.tour)
    void onTourClicked() {

    }

    @OnClick(R.id.findFriends)
    void onFindFriendsClicked() {

    }

    @OnClick(R.id.helpContactUs)
    void onHelpContactUsClicked() {
        EventBus.getDefault().postSticky(new OpenFragmentEvent(SettingsOptionsFragment.TAG, SettingsContactUsFragment.TAG));
    }


    @OnClick(R.id.privacyPolicy)
    void onPrivacyPolicyClicked() {

    }


    @OnClick(R.id.termsOfUse)
    void onTermsOfUseClicked() {

    }


    @OnClick(R.id.logout)
    void onLogoutClicked() {

    }


}