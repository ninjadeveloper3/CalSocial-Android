package com.CalSocial.ui.events;

import com.CalSocial.data.DataManager;
import com.CalSocial.ui.base.BasePresenter;
import com.CalSocial.ui.events.addGuest.AddGuestFragment;
import com.CalSocial.ui.events.createEvent.CreateEventFragment;
import com.CalSocial.ui.events.eventDetail.EventDetailDetailFragment;
import com.CalSocial.ui.events.selectDateTime.SelectDateTimeEventFragment;
import com.CalSocial.ui.events.selectTimeSuggestions.SelectTimeSuggestionsEventFragment;
import com.CalSocial.ui.homeMain.home.HomeFragment;
import com.CalSocial.utils.AppConstants;
import com.CalSocial.utils.AppUtils;
import com.CalSocial.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class EventsPresenter<V extends EventsMvpView> extends BasePresenter<V> implements EventsMvpPresenter<V> {


    @Inject
    public EventsPresenter(DataManager dataManager,
                           SchedulerProvider schedulerProvider,
                           CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onScreenCreated(String screenToOpen) {

        if (screenToOpen.compareToIgnoreCase(AppConstants.EVENT_DETAIL_FRAGMENT) == 0) {
            AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, EventDetailDetailFragment.TAG);
            getMvpView().showEventDetailFragment();

        } else if (screenToOpen.compareToIgnoreCase(AppConstants.CREATE_EVENT_FRAGMENT) == 0) {
            AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, CreateEventFragment.TAG);
            getMvpView().showCreateEventFragment();

        } else if (screenToOpen.compareToIgnoreCase(AppConstants.ADD_GUEST_FRAGMENT) == 0) {
            AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, AddGuestFragment.TAG);
            getMvpView().showAddGuestFragment();

        }
    }

    @Override
    public void showEventDetailFragment() {
        AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, EventDetailDetailFragment.TAG);
        getMvpView().showEventDetailFragment();
    }

    @Override
    public void showCreateEventFragment() {
        AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, CreateEventFragment.TAG);
        getMvpView().showCreateEventFragment();
    }

    @Override
    public void showAddGuestFragment() {
        AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, AddGuestFragment.TAG);
        getMvpView().showAddGuestFragment();
    }

    @Override
    public void showSelectDateTimeEventFragment() {
        AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, SelectDateTimeEventFragment.TAG);
        getMvpView().showSelectDateTimeEventFragment();
    }

    @Override
    public void showCalSocialSuggestionsEventFragment() {
        AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, SelectTimeSuggestionsEventFragment.TAG);
        getMvpView().showCalSocialSuggestionsEventFragment();
    }

    @Override
    public void onCancelClicked() {
        AppUtils.updateSourceDestination(AppUtils.destinationScreenFragment, HomeFragment.TAG);
        getMvpView().goToHomeScreen();
    }

}