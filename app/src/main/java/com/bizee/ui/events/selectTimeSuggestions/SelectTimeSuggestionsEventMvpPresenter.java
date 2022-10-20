package com.CalSocial.ui.events.selectTimeSuggestions;


import com.CalSocial.ui.base.MvpPresenter;

public interface SelectTimeSuggestionsEventMvpPresenter<V extends SelectTimeSuggestionsEventMvpView> extends MvpPresenter<V> {

    void openTimeSuggestions(String TAG);

    void onSuggestionsRVViewPrepared();

    void onAttendeesRVViewPrepared();

}