package com.CalSocial.ui.homeMain.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.CalSocial.R;
import com.CalSocial.data.network.model.NotificationsResponse;
import com.CalSocial.ui.base.BaseViewHolder;

import java.util.List;
import java.util.Random;

public class NotificationsAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE = 0;

    private Callback mCallback;
    private List<NotificationsResponse.Notification> mNotificationsResponseList;
    private Context context;
    private Random rand;


    public NotificationsAdapter(List<NotificationsResponse.Notification> eventList, Context context) {
        mNotificationsResponseList = eventList;
        this.context = context;
        rand = new Random();
    }

    public void setCallback(NotificationsAdapter.Callback callback) {
        mCallback = callback;
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new NotificationViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.notifications_item_notifications_layout, parent, false), mNotificationsResponseList, context);

    }


    @Override
    public int getItemViewType(int position) {

        return VIEW_TYPE;
    }

    @Override
    public int getItemCount() {
        if (mNotificationsResponseList != null && mNotificationsResponseList.size() > 0) {
            return mNotificationsResponseList.size();
        } else {
            return 1;
        }
    }


    public void addItems(List<NotificationsResponse.Notification> notificationList) {
        mNotificationsResponseList.addAll(notificationList);
        notifyDataSetChanged();
    }


    public interface Callback {
        void onRepoEmptyViewRetryClick();
    }


}
