package com.CalSocial.ui.events.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.error.ANError;
import com.CalSocial.R;
import com.CalSocial.data.network.model.EventAttendeesResponse;
import com.CalSocial.data.network.model.TimeSuggestionsResponse;
import com.CalSocial.event.OpenFragmentEvent;
import com.CalSocial.ui.base.BaseViewHolder;
import com.CalSocial.ui.events.createEvent.CreateEventFragment;
import com.CalSocial.ui.events.selectTimeSuggestions.SelectTimeSuggestionsEventFragment;
import com.CalSocial.ui.events.selectTimeSuggestions.SelectTimeSuggestionsEventMvpView;
import com.CalSocial.ui.events.selectTimeSuggestions.SelectTimeSuggestionsEventPresenter;

import net.cachapa.expandablelayout.ExpandableLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class TimeSuggestionsViewHolder extends BaseViewHolder {

    @BindView(R.id.date)
    TextView date;

    @BindView(R.id.time)
    TextView time;

    @BindView(R.id.possibility)
    TextView possibility;

    @BindView(R.id.selectTimeRV)
    RelativeLayout selectTimeRV;

    @BindView(R.id.eventsScheduledListExpandableLayout)
    ExpandableLayout eventsScheduledListExpandableLayout;

    @BindView(R.id.attendeesRV)
    RecyclerView attendeesRV;

    @BindView(R.id.useTimeButton)
    Button useTimeButton;

    private LinearLayoutManager mAttendeesLinearLayoutManager;

    private CalSocialTimeSuggesstionsAttendeesAdapter mAttendeesAdapter;

    private List<TimeSuggestionsResponse.Suggestion> mSuggestionsResponseList;
    private Context context;
    private SelectTimeSuggestionsEventPresenter<SelectTimeSuggestionsEventMvpView> presenter;

    public TimeSuggestionsViewHolder(View itemView, List<TimeSuggestionsResponse.Suggestion> suggestionList, Context context, SelectTimeSuggestionsEventPresenter<SelectTimeSuggestionsEventMvpView> presenter) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mSuggestionsResponseList = suggestionList;
        this.context = context;
        this.presenter = presenter;

    }

    @Override
    protected void clear() {

    }

    public void onBind(int position) {
        super.onBind(position);

        if (position < mSuggestionsResponseList.size()) {

            final TimeSuggestionsResponse.Suggestion suggestion = mSuggestionsResponseList.get(position);

            date.setText(suggestion.getDate());
            time.setText(suggestion.getTime());
            possibility.setText(suggestion.getCalSocialScore());

            selectTimeRV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (eventsScheduledListExpandableLayout.isExpanded()) {
                        eventsScheduledListExpandableLayout.collapse();
                    } else {
                        eventsScheduledListExpandableLayout.expand();

                    }
                }
            });

            mAttendeesLinearLayoutManager = new LinearLayoutManager(context);
            mAttendeesLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mAttendeesAdapter = new CalSocialTimeSuggesstionsAttendeesAdapter(new ArrayList<EventAttendeesResponse.User>(), context);
            attendeesRV.setLayoutManager(mAttendeesLinearLayoutManager);
            attendeesRV.setItemAnimator(new DefaultItemAnimator());
            attendeesRV.setAdapter(mAttendeesAdapter);
            ononAttendeesRVViewPrepared();

            useTimeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().postSticky(new OpenFragmentEvent(SelectTimeSuggestionsEventFragment.TAG, CreateEventFragment.TAG));
                }
            });


        }

    }

    public void repopulateRV(List<EventAttendeesResponse.User> userList) {
        if (mAttendeesAdapter != null) {
            mAttendeesAdapter.addItems(userList);
        }
    }

    public void ononAttendeesRVViewPrepared() {
        presenter.getCompositeDisposable().add(presenter.getDataManager()
                .getEventAttendeesApiCall()
                .subscribeOn(presenter.getSchedulerProvider().io())
                .observeOn(presenter.getSchedulerProvider().ui())
                .subscribe(new Consumer<EventAttendeesResponse>() {
                    @Override
                    public void accept(@NonNull EventAttendeesResponse usersResponse)
                            throws Exception {
                        if (usersResponse != null && usersResponse.getData() != null) {
                            repopulateRV(usersResponse.getData());
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable)
                            throws Exception {
                        if (!presenter.isViewAttached()) {
                            return;
                        }

                        // handle the error here
                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            presenter.handleApiError(anError);
                        }
                    }
                }));
    }
}
