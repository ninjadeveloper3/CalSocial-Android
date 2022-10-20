package com.CalSocial.ui.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.CalSocial.R;
import com.CalSocial.event.OpenContactProfileEvent;
import com.CalSocial.event.OpenFragmentEvent;
import com.CalSocial.ui.base.BaseActivity;
import com.CalSocial.ui.homeMain.home.HomeFragment;
import com.CalSocial.ui.profile.contactProfile.ContactProfileFragment;
import com.CalSocial.ui.profile.dialog.MoreMenuDialog;
import com.CalSocial.ui.profile.editProfile.EditProfileFragment;
import com.CalSocial.ui.profile.editSwarm.EditSwarmFragment;
import com.CalSocial.ui.profile.settings.settingsCalSocialSuggestions.SettingsCalSocialSuggestionsFragment;
import com.CalSocial.ui.profile.settings.settingsBlockedUsers.SettingsBlockedUsersFragment;
import com.CalSocial.ui.profile.settings.settingsContactUs.SettingsContactUsFragment;
import com.CalSocial.ui.profile.settings.settingsContactUsReport.SettingsContactUsReportFragment;
import com.CalSocial.ui.profile.settings.settingsHiveConnections.SettingsHiveConnectionsFragment;
import com.CalSocial.ui.profile.settings.settingsNotifications.SettingsNotificationsFragment;
import com.CalSocial.ui.profile.settings.settingsOptions.SettingsOptionsFragment;
import com.CalSocial.ui.profile.settings.settingsSetYourPrivacy.SettingsSetYourPrivacyFragment;
import com.CalSocial.ui.profile.settings.settingsSyncCalendars.SettingsSyncCalendarsFragment;
import com.CalSocial.ui.profile.swarmProfile.SwarmProfileFragment;
import com.CalSocial.ui.profile.viewHive.ViewHiveFragment;
import com.CalSocial.ui.profile.yourProfile.YourProfileFragment;
import com.CalSocial.utils.AppConstants;
import com.CalSocial.utils.AppUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileActivity extends BaseActivity implements ProfileMvpView {

    @BindView(R.id.fragmentRoot)
    LinearLayout rootLL;

    @BindView(R.id.moreSettings)
    ImageView moreSettings;

    @BindView(R.id.editYourProfile)
    TextView editYourProfile;

    @BindView(R.id.saveYourProfile)
    TextView saveYourProfile;

    @BindView(R.id.removePopup)
    ImageView removePopup;

    @BindView(R.id.profileName)
    TextView profileName;

    @BindView(R.id.cancelEditProfile)
    TextView cancelEditProfile;

    @BindView(R.id.goBack)
    ImageView goBack;

    private String profileNameText="";

    private AppConstants.ProfilePageMoreSettingsSource moreSettingsSource;

    @Inject
    ProfilePresenter<ProfileMvpView> mPresenter;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, ProfileActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profiles_activity_profile);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(ProfileActivity.this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null && bundle.getString("screenToOpen") != null && bundle.getString("name") != null && bundle.getString("profileImage") != null) {
            if (bundle.getString("screenToOpen").compareToIgnoreCase(AppConstants.CONTACT_PROFILE_FRAGMENT) == 0) {
                profileName.setText(bundle.getString("name"));
                profileNameText=bundle.getString("name");
                EventBus.getDefault().postSticky(new OpenContactProfileEvent(bundle.getString("name"), bundle.getString("profileImage")));
            } else if (bundle.getString("screenToOpen").compareToIgnoreCase(AppConstants.YOUR_PROFILE_FRAGMENT) == 0) {
                //profileName.setText("My Profile");
                //EventBus.getDefault().postSticky(new OpenContactProfileEvent(bundle.getString("name"), bundle.getString("profileImage")));
                //editYourProfile.setVisibility(View.VISIBLE);
                //moreSettings.setVisibility(View.GONE);
            } else if (bundle.getString("screenToOpen").compareToIgnoreCase(AppConstants.SWARM_PROFILE_FRAGMENT) == 0) {
                profileName.setText(bundle.getString("name"));
                profileNameText=bundle.getString("name");

                EventBus.getDefault().postSticky(new OpenContactProfileEvent(bundle.getString("name"), bundle.getString("profileImage")));

            }
            mPresenter.onScreenCreated(bundle.getString("screenToOpen"));
        }

    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void onFragmentAttached() {
    }

    @Override
    public void onFragmentDetached(String tag) {

        if (tag.equalsIgnoreCase("")) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            List<Fragment> fragments = fragmentManager.getFragments();
            for (int i = 0; i < fragments.size(); i++) {
                if (fragmentManager.findFragmentByTag(fragments.get(i).getTag()) != null) {
                    fragmentManager.beginTransaction()
                            .disallowAddToBackStack()
                            .remove(fragments.get(i))
                            .commitNow();
                }
            }

        } else {
            String option = tag.split("-")[1];
            tag = tag.split("-")[0];
            FragmentManager fragmentManager = getSupportFragmentManager();
            Fragment fragment = fragmentManager.findFragmentByTag(tag);
            if (fragment != null) {
                fragmentManager
                        .beginTransaction()
                        .disallowAddToBackStack()
                        .remove(fragment)
                        .commitNow();
                //unlockDrawer();

                if (option.compareToIgnoreCase(AppConstants.OPEN_CONTACT_PROFILE) == 0) {
                    mPresenter.showContactProfileFragment();
                } else if (option.compareToIgnoreCase(AppConstants.OPEN_SWARM_PROFILE) == 0) {
                    mPresenter.showSwarmProfileFragment();
                } else if (option.compareToIgnoreCase(AppConstants.OPEN_EDIT_SWARM_PROFILE) == 0) {
                    mPresenter.showEditSwarmFragment();
                } else if (option.compareToIgnoreCase(AppConstants.OPEN_VIEW_HIVE_PROFILE) == 0) {
                    mPresenter.showViewHiveFragment();
                } else if (option.compareToIgnoreCase(AppConstants.OPEN_YOUR_PROFILE) == 0) {
                    mPresenter.showYourProfileFragment();
                } else if (option.compareToIgnoreCase(AppConstants.OPEN_EDIT_PROFILE) == 0) {
                    mPresenter.showEditProfileFragment();
                } else if (option.compareToIgnoreCase(AppConstants.OPEN_SETTINGS_OPTIONS) == 0) {
                    mPresenter.showSettingsOptionsFragment();
                } else if (option.compareToIgnoreCase(AppConstants.OPEN_SETTINGS_CalSocial_SUGGESTIONS) == 0) {
                    mPresenter.showSettingsCalSocialSuggestionsFragment();
                }

            }
        }

    }

    @Override
    protected void setUp() {

    }

    @Override
    public void showContactProfileFragment() {
        moreSettingsSource = AppConstants.ProfilePageMoreSettingsSource.CONTACT_PROFILE;
        goBack.setVisibility(View.VISIBLE);
        editYourProfile.setVisibility(View.GONE);
        moreSettings.setVisibility(View.VISIBLE);
        saveYourProfile.setVisibility(View.GONE);
        removePopup.setVisibility(View.GONE);
        profileName.setText(profileNameText);
        cancelEditProfile.setVisibility(View.GONE);
        onFragmentDetached("");
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .add(R.id.fragmentRoot, ContactProfileFragment.newInstance(), ContactProfileFragment.TAG)
                .commit();
    }

    @Override
    public void showSwarmProfileFragment() {
        moreSettingsSource = AppConstants.ProfilePageMoreSettingsSource.SWARM_PROFILE;
        goBack.setVisibility(View.VISIBLE);
        editYourProfile.setVisibility(View.GONE);
        moreSettings.setVisibility(View.VISIBLE);
        saveYourProfile.setVisibility(View.GONE);
        removePopup.setVisibility(View.GONE);
        profileName.setText(profileNameText);
        cancelEditProfile.setVisibility(View.GONE);
        onFragmentDetached("");
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .add(R.id.fragmentRoot, SwarmProfileFragment.newInstance(), SwarmProfileFragment.TAG)
                .commit();
    }

    @Override
    public void showViewHiveFragment() {
        goBack.setVisibility(View.VISIBLE);
        editYourProfile.setVisibility(View.GONE);
        moreSettings.setVisibility(View.GONE);
        saveYourProfile.setVisibility(View.VISIBLE);
        removePopup.setVisibility(View.GONE);
        profileName.setText("John's Hive");
        cancelEditProfile.setVisibility(View.GONE);
        onFragmentDetached("");
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .add(R.id.fragmentRoot, ViewHiveFragment.newInstance(), ViewHiveFragment.TAG)
                .commit();
    }

    @Override
    public void showEditSwarmFragment() {
        //I am giving the TAG OF SwarmProfileFragment, because that is the fragment which is running,
        //when this function was called.
        goBack.setVisibility(View.GONE);
        editYourProfile.setVisibility(View.GONE);
        moreSettings.setVisibility(View.GONE);
        saveYourProfile.setVisibility(View.VISIBLE);
        removePopup.setVisibility(View.GONE);
        profileName.setText("Edit Swarm");
        cancelEditProfile.setVisibility(View.VISIBLE);
        onFragmentDetached(SwarmProfileFragment.TAG + "-" + AppConstants.IGNORE_SECOND_CHECK);
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .add(R.id.fragmentRoot, EditSwarmFragment.newInstance(), EditSwarmFragment.TAG)
                .commit();
    }

    @Override
    public void showEditProfileFragment() {
        goBack.setVisibility(View.GONE);
        editYourProfile.setVisibility(View.GONE);
        moreSettings.setVisibility(View.GONE);
        saveYourProfile.setVisibility(View.VISIBLE);
        removePopup.setVisibility(View.GONE);
        profileName.setText("Edit Profile");
        cancelEditProfile.setVisibility(View.VISIBLE);
        onFragmentDetached("");
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .add(R.id.fragmentRoot, EditProfileFragment.newInstance(), EditProfileFragment.TAG)
                .commit();
    }

    @Override
    public void showYourProfileFragment() {
        goBack.setVisibility(View.VISIBLE);
        editYourProfile.setVisibility(View.VISIBLE);
        moreSettings.setVisibility(View.GONE);
        saveYourProfile.setVisibility(View.GONE);
        removePopup.setVisibility(View.GONE);
        profileName.setText("My Profile");
        cancelEditProfile.setVisibility(View.GONE);
        onFragmentDetached("");
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .add(R.id.fragmentRoot, YourProfileFragment.newInstance(), YourProfileFragment.TAG)
                .commit();
    }


    @Override
    public void showSettingsOptionsFragment() {
        goBack.setVisibility(View.VISIBLE);
        editYourProfile.setVisibility(View.GONE);
        moreSettings.setVisibility(View.GONE);
        saveYourProfile.setVisibility(View.GONE);
        removePopup.setVisibility(View.GONE);
        profileName.setText("Settings");
        cancelEditProfile.setVisibility(View.GONE);
        onFragmentDetached("");
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .add(R.id.fragmentRoot, SettingsOptionsFragment.newInstance(), SettingsOptionsFragment.TAG)
                .commit();

    }

    @Override
    public void showSettingsCalSocialSuggestionsFragment() {
        goBack.setVisibility(View.VISIBLE);
        editYourProfile.setVisibility(View.GONE);
        moreSettings.setVisibility(View.GONE);
        saveYourProfile.setVisibility(View.GONE);
        removePopup.setVisibility(View.GONE);
        profileName.setText("CalSocial Suggestions");
        cancelEditProfile.setVisibility(View.GONE);
        onFragmentDetached("");
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .add(R.id.fragmentRoot, SettingsCalSocialSuggestionsFragment.newInstance(), SettingsCalSocialSuggestionsFragment.TAG)
                .commit();
    }

    @Override
    public void showSettingsSyncCalendarFragment() {
        goBack.setVisibility(View.VISIBLE);
        editYourProfile.setVisibility(View.GONE);
        moreSettings.setVisibility(View.GONE);
        saveYourProfile.setVisibility(View.GONE);
        removePopup.setVisibility(View.GONE);
        profileName.setText("Sync Calendar");
        cancelEditProfile.setVisibility(View.GONE);
        onFragmentDetached("");
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .add(R.id.fragmentRoot, SettingsSyncCalendarsFragment.newInstance(), SettingsSyncCalendarsFragment.TAG)
                .commit();
    }

    @Override
    public void showSettingsSetYourPrivacyFragment() {
        goBack.setVisibility(View.VISIBLE);
        editYourProfile.setVisibility(View.GONE);
        moreSettings.setVisibility(View.GONE);
        saveYourProfile.setVisibility(View.GONE);
        removePopup.setVisibility(View.GONE);
        profileName.setText("Set Your Privacy");
        cancelEditProfile.setVisibility(View.GONE);
        onFragmentDetached("");
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .add(R.id.fragmentRoot, SettingsSetYourPrivacyFragment.newInstance(), SettingsSetYourPrivacyFragment.TAG)
                .commit();
    }

    @Override
    public void showSettingsHiveConnectionsFragment() {
        goBack.setVisibility(View.VISIBLE);
        editYourProfile.setVisibility(View.GONE);
        moreSettings.setVisibility(View.GONE);
        saveYourProfile.setVisibility(View.GONE);
        removePopup.setVisibility(View.GONE);
        profileName.setText("Hive Connections");
        cancelEditProfile.setVisibility(View.GONE);
        onFragmentDetached("");
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .add(R.id.fragmentRoot, SettingsHiveConnectionsFragment.newInstance(), SettingsHiveConnectionsFragment.TAG)
                .commit();
    }

    @Override
    public void showSettingsBlockedUsersFragment() {
        goBack.setVisibility(View.VISIBLE);
        editYourProfile.setVisibility(View.GONE);
        moreSettings.setVisibility(View.GONE);
        saveYourProfile.setVisibility(View.GONE);
        removePopup.setVisibility(View.GONE);
        profileName.setText("Blocked Users");
        cancelEditProfile.setVisibility(View.GONE);
        onFragmentDetached("");
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .add(R.id.fragmentRoot, SettingsBlockedUsersFragment.newInstance(), SettingsBlockedUsersFragment.TAG)
                .commit();
    }

    @Override
    public void showSettingsContactUsFragment() {
        goBack.setVisibility(View.VISIBLE);
        editYourProfile.setVisibility(View.GONE);
        moreSettings.setVisibility(View.GONE);
        saveYourProfile.setVisibility(View.GONE);
        removePopup.setVisibility(View.GONE);
        profileName.setText("Contact Us");
        cancelEditProfile.setVisibility(View.GONE);
        onFragmentDetached("");
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .add(R.id.fragmentRoot, SettingsContactUsFragment.newInstance(), SettingsContactUsFragment.TAG)
                .commit();
    }

    @Override
    public void showSettingsNotificationsFragment() {
        goBack.setVisibility(View.VISIBLE);
        editYourProfile.setVisibility(View.GONE);
        moreSettings.setVisibility(View.GONE);
        saveYourProfile.setVisibility(View.GONE);
        removePopup.setVisibility(View.GONE);
        profileName.setText("Notifications");
        cancelEditProfile.setVisibility(View.GONE);
        onFragmentDetached("");
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .add(R.id.fragmentRoot, SettingsNotificationsFragment.newInstance(), SettingsNotificationsFragment.TAG)
                .commit();
    }

    @Override
    public void showSettingsContactUsReportFragment() {
        goBack.setVisibility(View.VISIBLE);
        editYourProfile.setVisibility(View.GONE);
        moreSettings.setVisibility(View.GONE);
        saveYourProfile.setVisibility(View.GONE);
        removePopup.setVisibility(View.GONE);
        profileName.setText("Contact Us");
        cancelEditProfile.setVisibility(View.GONE);
        onFragmentDetached("");
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .add(R.id.fragmentRoot, SettingsContactUsReportFragment.newInstance(), SettingsContactUsReportFragment.TAG)
                .commit();
    }

    @Override
    public void showHome() {
        onFragmentDetached("");
        finish();
    }


    @OnClick(R.id.moreSettings)
    void onMoreSettingsClicked() {
        //EventBus.getDefault().post(new ToolBarEvent(AppConstants.SWARM_PROFILE));

        if (AppUtils.sourceScreenFragment.compareToIgnoreCase(HomeFragment.TAG) == 0 && AppUtils.destinationScreenFragment.compareToIgnoreCase(ContactProfileFragment.TAG) == 0) {
            //open the popup dialog
            MoreMenuDialog.newInstance().show(getSupportFragmentManager());
        }
        if (AppUtils.sourceScreenFragment.compareToIgnoreCase(HomeFragment.TAG) == 0 && AppUtils.destinationScreenFragment.compareToIgnoreCase(SwarmProfileFragment.TAG) == 0) {
            mPresenter.showEditSwarmFragment();
        }
    }

    @OnClick(R.id.editYourProfile)
    void onEditYourProfileClicked() {
        mPresenter.showEditProfileFragment();
    }

    @OnClick(R.id.removePopup)
    void onRemovePopupClicked() {

    }

    @OnClick(R.id.goBack)
    void goBack() {
        mPresenter.onGoBackPressed();
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true, priority = 1)
    public void onMessageEvent(OpenFragmentEvent event) {
        if (event.targetFragment.equalsIgnoreCase(SettingsOptionsFragment.TAG)) {
            onFragmentDetached("");
            mPresenter.showSettingsOptionsFragment();
        } else if (event.targetFragment.equalsIgnoreCase(SettingsCalSocialSuggestionsFragment.TAG)) {
            onFragmentDetached("");
            mPresenter.showSettingsCalSocialSuggestionsFragment();
        } else if (event.targetFragment.equalsIgnoreCase(SettingsSyncCalendarsFragment.TAG)) {
            onFragmentDetached("");
            mPresenter.showSettingsSyncCalendarsFragment();
        } else if (event.targetFragment.equalsIgnoreCase(SettingsSetYourPrivacyFragment.TAG)) {
            onFragmentDetached("");
            mPresenter.showSettingsSetYourPrivacy();
        } else if (event.targetFragment.equalsIgnoreCase(SettingsHiveConnectionsFragment.TAG)) {
            onFragmentDetached("");
            mPresenter.showSettingsHiveConnections();
        } else if (event.targetFragment.equalsIgnoreCase(SettingsBlockedUsersFragment.TAG)) {
            onFragmentDetached("");
            mPresenter.showSettingsBlockedUsers();
        } else if (event.targetFragment.equalsIgnoreCase(SettingsContactUsFragment.TAG)) {
            onFragmentDetached("");
            mPresenter.showSettingsContactUs();
        } else if (event.targetFragment.equalsIgnoreCase(SettingsNotificationsFragment.TAG)) {
            onFragmentDetached("");
            mPresenter.showSettingsNotifications();
        } else if (event.targetFragment.equalsIgnoreCase(SettingsContactUsReportFragment.TAG)) {
            onFragmentDetached("");
            mPresenter.showSettingsContactUsReport();
        }

        EventBus.getDefault().removeStickyEvent(event);
    }

    @OnClick(R.id.cancelEditProfile)
    void onCancelEditProfileClicked() {
        mPresenter.onCancelPressed();
    }

    @OnClick(R.id.saveYourProfile)
    void onSaveYourProfileClicked() {
        mPresenter.onSaveClicked();
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