package com.CalSocial.ui.events.selectTimeSuggestions;

import com.CalSocial.data.network.model.EventAttendeesResponse;
import com.CalSocial.data.network.model.TimeSuggestionsResponse;
import com.CalSocial.ui.base.MvpView;

import java.util.List;

public interface SelectTimeSuggestionsEventMvpView extends MvpView {

    void onFragmentDetached(String TAG);

    void updateSuggestionsRepo(List<TimeSuggestionsResponse.Suggestion> suggestionsList);

    void updateAttendeesRepo(List<EventAttendeesResponse.User> userList);


}