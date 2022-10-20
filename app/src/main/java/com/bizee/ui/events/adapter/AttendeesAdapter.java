package com.CalSocial.ui.events.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.CalSocial.R;
import com.CalSocial.data.network.model.EventAttendeesResponse;
import com.CalSocial.ui.base.BaseViewHolder;

import java.util.List;
import java.util.Random;

public class AttendeesAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE = 0;
    private Callback mCallback;
    private List<EventAttendeesResponse.User> mUserResponseList;
    private Context context;
    private Random rand;


    public AttendeesAdapter(List<EventAttendeesResponse.User> userResponseList, Context context) {
        mUserResponseList = userResponseList;
        this.context = context;
        rand = new Random();
    }

    public void setCallback(AttendeesAdapter.Callback callback) {
        mCallback = callback;
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new AttendeeViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.events_item_event_attendees_layout, parent, false), mUserResponseList, context);

    }


    @Override
    public int getItemViewType(int position) {
        if (mUserResponseList != null && mUserResponseList.size() > 0) {
            return VIEW_TYPE;
        }

        return VIEW_TYPE;

    }

    @Override
    public int getItemCount() {
        if (mUserResponseList != null && mUserResponseList.size() > 0) {
            return mUserResponseList.size();
        } else {
            return 1;
        }
    }


    public void addItems(List<EventAttendeesResponse.User> userList) {
        mUserResponseList.addAll(userList);
        notifyDataSetChanged();
    }


    public interface Callback {
        void onRepoEmptyViewRetryClick();
    }

}
