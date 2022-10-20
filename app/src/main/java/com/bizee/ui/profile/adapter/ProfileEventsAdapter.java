package com.CalSocial.ui.profile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.CalSocial.R;
import com.CalSocial.data.network.model.EventsHomeResponse;
import com.CalSocial.ui.base.BaseViewHolder;

import java.util.List;
import java.util.Random;

public class ProfileEventsAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_CalSocial_EVENT = 1;

    private Callback mCallback;
    private List<EventsHomeResponse.Event> mEventsResponseList;
    private Context context;
    private Random rand;


    public ProfileEventsAdapter(List<EventsHomeResponse.Event> eventList, Context context) {
        mEventsResponseList = eventList;
        this.context = context;
        rand = new Random();
    }

    public void setCallback(ProfileEventsAdapter.Callback callback) {
        mCallback = callback;
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new EventCalSocialEventViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.profiles_item_events_CalSocial_event_item_layout, parent, false), mEventsResponseList);

    }


    @Override
    public int getItemViewType(int position) {
        return VIEW_TYPE_CalSocial_EVENT;
    }

    @Override
    public int getItemCount() {
        if (mEventsResponseList != null && mEventsResponseList.size() > 0) {
            return mEventsResponseList.size();
        } else {
            return 1;
        }
    }


    public void addItems(List<EventsHomeResponse.Event> eventList) {
        mEventsResponseList.addAll(eventList);
        notifyDataSetChanged();
    }


    public interface Callback {
        void onRepoEmptyViewRetryClick();
    }


}
