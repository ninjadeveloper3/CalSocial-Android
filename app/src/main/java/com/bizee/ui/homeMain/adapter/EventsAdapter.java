package com.CalSocial.ui.homeMain.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.CalSocial.R;
import com.CalSocial.data.network.model.EventsHomeResponse;
import com.CalSocial.ui.base.BaseViewHolder;

import java.util.List;
import java.util.Random;

public class EventsAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_DATE = 0;
    public static final int VIEW_TYPE_CalSocial_EVENT = 1;
    public static final int VIEW_TYPE_NON_CalSocial_EVENT = 2;

    private Callback mCallback;
    private List<EventsHomeResponse.Event> mEventsHomeResponseList;
    private Context context;
    private Random rand;


    public EventsAdapter(List<EventsHomeResponse.Event> eventList, Context context) {
        mEventsHomeResponseList = eventList;
        this.context = context;
        rand = new Random();
    }

    public void setCallback(EventsAdapter.Callback callback) {
        mCallback = callback;
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case VIEW_TYPE_DATE:
                return new EventDateViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_events_date_item_layout, parent, false), mEventsHomeResponseList, context);
            case VIEW_TYPE_CalSocial_EVENT:
                return new EventCalSocialEventViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_events_CalSocial_event_item_layout, parent, false), mEventsHomeResponseList, context);
            case VIEW_TYPE_NON_CalSocial_EVENT:
            default:
                return new EventNonCalSocialEventViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_events_non_CalSocial_event_item_layout, parent, false), mEventsHomeResponseList, context);
        }

    }


    @Override
    public int getItemViewType(int position) {
        if (mEventsHomeResponseList != null && mEventsHomeResponseList.size() > 0) {

            if (mEventsHomeResponseList.get(position).getEventType().compareToIgnoreCase("date") == 0) {
                return VIEW_TYPE_DATE;

            } else if (mEventsHomeResponseList.get(position).getEventType().compareToIgnoreCase("CalSocial") == 0) {
                return VIEW_TYPE_CalSocial_EVENT;

            } else {
                return VIEW_TYPE_NON_CalSocial_EVENT;

            }
        } else {
            return VIEW_TYPE_NON_CalSocial_EVENT;

        }
    }

    @Override
    public int getItemCount() {
        if (mEventsHomeResponseList != null && mEventsHomeResponseList.size() > 0) {
            return mEventsHomeResponseList.size();
        } else {
            return 1;
        }
    }


    public void addItems(List<EventsHomeResponse.Event> eventList) {
        mEventsHomeResponseList.addAll(eventList);
        notifyDataSetChanged();
    }


    public interface Callback {
        void onRepoEmptyViewRetryClick();
    }


}
