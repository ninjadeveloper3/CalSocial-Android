package com.CalSocial.ui.onboarding;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.CalSocial.R;
import com.CalSocial.event.GestureEvent;
import com.CalSocial.ui.base.BaseActivity;
import com.CalSocial.ui.homeMain.HomeMainActivity;
import com.CalSocial.ui.onboarding.additionalInfo.SignupAdditionalInfoFragment;
import com.CalSocial.ui.onboarding.calendarSync.CalendarSyncFragment;
import com.CalSocial.ui.onboarding.contactsFound.ContactsFoundFragment;
import com.CalSocial.ui.onboarding.contactsSync.ContactsSyncFragment;
import com.CalSocial.ui.onboarding.createFirstEvent.CreateFirstEventFragment;
import com.CalSocial.ui.onboarding.landing.LandingFragment;
import com.CalSocial.ui.onboarding.phoneCode.PhoneCodeFragment;
import com.CalSocial.ui.onboarding.signIn.SigninFragment;
import com.CalSocial.ui.onboarding.signUp.SignupFragment;
import com.CalSocial.utils.AppConstants;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class OnboardingActivity extends BaseActivity implements OnboardingMvpView, GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {

    @BindView(R.id.logo)
    View logo;

    @BindView(R.id.fragmentRoot)
    LinearLayout rootLL;

    @Inject
    OnboardingPresenter<OnboardingMvpView> mPresenter;

    private GestureDetectorCompat mDetector;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, OnboardingActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onboarding_activity_onboarding);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(OnboardingActivity.this);
        mPresenter.onScreenCreated();
        mDetector = new GestureDetectorCompat(getApplicationContext(), this);
        mDetector.setOnDoubleTapListener(this);

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

            if (option.compareToIgnoreCase(AppConstants.OPEN_LOGIN) == 0) {
                mPresenter.showSigninFragment();
            } else if (option.compareToIgnoreCase(AppConstants.PROCEED_TO_PHONE_VERIFICATION_SIGNUP) == 0) {
                mPresenter.showPhoneCodeFragment();
            } else if (option.compareToIgnoreCase(AppConstants.OPEN_LANDING) == 0) {
                mPresenter.showLandingPageFragment();
            } else if (option.compareToIgnoreCase(AppConstants.OPEN_SIGNUP) == 0) {
                mPresenter.showSignUpFragment();
            } else if (option.compareToIgnoreCase(AppConstants.PROCEED_TO_ADDITIONAL_INFO_PHONE_CODE) == 0) {
                mPresenter.showAdditionalInfoFragment();
            } else if (option.compareToIgnoreCase(AppConstants.CONTACTS_SYNC) == 0) {
                mPresenter.showContactsSyncFragment();
            } else if (option.compareToIgnoreCase(AppConstants.CALENDER_SYNC) == 0) {
                mPresenter.showCalenderSyncFragment();
            } else if (option.compareToIgnoreCase(AppConstants.OPEN_HOME) == 0) {
                mPresenter.openHomeActivity();
            } else if (option.compareToIgnoreCase(AppConstants.CREATE_FIRST_EVENT) == 0) {
                mPresenter.showCreateFirstEventFragment();
            } else if (option.compareToIgnoreCase(AppConstants.CONTACTS_FOUND) == 0) {
                mPresenter.showContactsFoundFragment();
            }

        }

    }

    @Override
    protected void setUp() {

    }

    @Override
    public void showSignUpFragment() {
        showLogo();
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .add(R.id.fragmentRoot, SignupFragment.newInstance(), SignupFragment.TAG)
                .commit();
    }

    @Override
    public void showPhoneCodeFragment() {
        showLogo();
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .add(R.id.fragmentRoot, PhoneCodeFragment.newInstance(), PhoneCodeFragment.TAG)
                .commit();
    }


    @Override
    public void showAdditionalInfoFragment() {
        showLogo();
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .add(R.id.fragmentRoot, SignupAdditionalInfoFragment.newInstance(), SignupAdditionalInfoFragment.TAG)
                .commit();
    }

    @Override
    public void showContactsSyncFragment() {

        String[] permissions = {Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS};
        requestPermissionsSafely(permissions, 88);
        showLogo();
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .add(R.id.fragmentRoot, ContactsSyncFragment.newInstance(), ContactsSyncFragment.TAG)
                .commit();

    }

    @Override
    public void showSigninFragment() {
        showLogo();
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .add(R.id.fragmentRoot, SigninFragment.newInstance(), SigninFragment.TAG)
                .commit();
    }

    @Override
    public void showLandingPageFragment() {

        String[] permissions = {Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS};
        requestPermissionsSafely(permissions, 88);
        hideLogo();
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .add(R.id.fragmentRoot, LandingFragment.newInstance(), LandingFragment.TAG)
                .commit();
    }

    @Override
    public void showContactsFoundFragment() {
        showLogo();
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .add(R.id.fragmentRoot, ContactsFoundFragment.newInstance(), ContactsFoundFragment.TAG)
                .commit();
    }

    @Override
    public void showCalenderSyncFragment() {
        String[] permissions = {Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR};
        requestPermissionsSafely(permissions, 88);
        showLogo();
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .add(R.id.fragmentRoot, CalendarSyncFragment.newInstance(), CalendarSyncFragment.TAG)
                .commit();
    }

    @Override
    public void showCreateFirstEventFragment() {
        showLogo();
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .add(R.id.fragmentRoot, CreateFirstEventFragment.newInstance(), CreateFirstEventFragment.TAG)
                .commit();
    }


    @Override
    public void openHomeActivity() {
        Intent intent = HomeMainActivity.getStartIntent(OnboardingActivity.this);
        startActivity(intent);
        finish();
    }

    private void showLogo() {
        logo.setVisibility(View.VISIBLE);
    }

    private void hideLogo() {
        logo.setVisibility(View.GONE);
    }

    @Override
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        super.requestPermissionsSafely(permissions, requestCode);
    }

    @Override
    public boolean hasPermission(String permission) {
        return super.hasPermission(permission);
    }

    @Override
    public void onBackPressed() {
        finish();
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        //I need to override the dispatchTouchEvent method so that
        //the scrollup gesture can work on the viewpager as well, which
        //the viewpager otherwise rejects if this method is not overridden.

        if (this.mDetector.onTouchEvent(event)) {
            return true;
        }
        return super.dispatchTouchEvent(event);
    }


    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

        if (e1.getX() > e2.getX()) {
            //this is left scroll
            //The view is to be moved to the left
            EventBus.getDefault().postSticky(new GestureEvent("left scroll", e1, e2, distanceX, distanceY));
        } else {
            //this is right scroll
            //move the view out of the screen to the right

            EventBus.getDefault().postSticky(new GestureEvent("right scroll", e1, e2, distanceX, distanceY));

        }
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}