package com.CalSocial.ui.onboarding;

import com.CalSocial.data.DataManager;
import com.CalSocial.ui.base.BasePresenter;
import com.CalSocial.ui.homeMain.home.HomeFragment;
import com.CalSocial.ui.onboarding.additionalInfo.SignupAdditionalInfoFragment;
import com.CalSocial.ui.onboarding.calendarSync.CalendarSyncFragment;
import com.CalSocial.ui.onboarding.contactsFound.ContactsFoundFragment;
import com.CalSocial.ui.onboarding.contactsSync.ContactsSyncFragment;
import com.CalSocial.ui.onboarding.createFirstEvent.CreateFirstEventFragment;
import com.CalSocial.ui.onboarding.landing.LandingFragment;
import com.CalSocial.ui.onboarding.phoneCode.PhoneCodeFragment;
import com.CalSocial.ui.onboarding.signIn.SigninFragment;
import com.CalSocial.ui.onboarding.signUp.SignupFragment;
import com.CalSocial.utils.AppUtils;
import com.CalSocial.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class OnboardingPresenter<V extends OnboardingMvpView> extends BasePresenter<V> implements OnboardingMvpPresenter<V> {

    private static final String TAG = "OnboardingPresenter";

    @Inject
    public OnboardingPresenter(DataManager dataManager,
                               SchedulerProvider schedulerProvider,
                               CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);


    }

    @Override
    public void onScreenCreated() {
        //The app has been created and now show the first onboarding screen
        AppUtils.sourceScreenFragment = LandingFragment.TAG;
        AppUtils.updateSourceDestination(AppUtils.sourceScreenFragment, LandingFragment.TAG);
        getMvpView().showLandingPageFragment();
        //getMvpView().showCalenderSyncFragment();

    }

    @Override
    public void showSignUpFragment() {
        AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, SignupFragment.TAG);
        getMvpView().showSignUpFragment();
    }

    @Override
    public void showPhoneCodeFragment() {
        AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, PhoneCodeFragment.TAG);
        getMvpView().showPhoneCodeFragment();
    }

    @Override
    public void showAdditionalInfoFragment() {
        AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, SignupAdditionalInfoFragment.TAG);
        getMvpView().showAdditionalInfoFragment();
    }

    @Override
    public void showContactsSyncFragment() {
        AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, ContactsSyncFragment.TAG);
        getMvpView().showContactsSyncFragment();
    }

    @Override
    public void showSigninFragment() {
        AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, SigninFragment.TAG);
        getMvpView().showSigninFragment();
    }

    @Override
    public void showLandingPageFragment() {
        AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, LandingFragment.TAG);
        getMvpView().showLandingPageFragment();
    }

    @Override
    public void showContactsFoundFragment() {
        AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, ContactsFoundFragment.TAG);
        getMvpView().showContactsFoundFragment();
    }

    @Override
    public void showCalenderSyncFragment() {
        AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, CalendarSyncFragment.TAG);
        getMvpView().showCalenderSyncFragment();
    }

    @Override
    public void openHomeActivity() {
        AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, HomeFragment.TAG);
        getMvpView().openHomeActivity();
    }

    @Override
    public void showCreateFirstEventFragment() {
        AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, CreateFirstEventFragment.TAG);
        getMvpView().showCreateFirstEventFragment();
    }
}