package com.CalSocial.ui.events.selectDateTime;


import com.CalSocial.ui.base.MvpPresenter;

public interface SelectDateTimeEventMvpPresenter<V extends SelectDateTimeEventMvpView> extends MvpPresenter<V> {

void openTimeSuggestions(String TAG);

}