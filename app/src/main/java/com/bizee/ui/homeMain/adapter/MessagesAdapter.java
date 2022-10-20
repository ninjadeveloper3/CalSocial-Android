package com.CalSocial.ui.homeMain.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.CalSocial.R;
import com.CalSocial.data.network.model.MessagesResponse;
import com.CalSocial.ui.base.BaseViewHolder;

import java.util.List;
import java.util.Random;

public class MessagesAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE = 0;

    private Callback mCallback;
    private List<MessagesResponse.Message> mMessagesResponseList;
    private Context context;
    private Random rand;


    public MessagesAdapter(List<MessagesResponse.Message> eventList, Context context) {
        mMessagesResponseList = eventList;
        this.context = context;
        rand = new Random();
    }

    public void setCallback(MessagesAdapter.Callback callback) {
        mCallback = callback;
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new MessageViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.messages_item_messages_layout, parent, false), mMessagesResponseList);

    }


    @Override
    public int getItemViewType(int position) {
        return VIEW_TYPE;
    }

    @Override
    public int getItemCount() {
        if (mMessagesResponseList != null && mMessagesResponseList.size() > 0) {
            return mMessagesResponseList.size();
        } else {
            return 1;
        }
    }


    public void addItems(List<MessagesResponse.Message> messageList) {
        mMessagesResponseList.addAll(messageList);
        notifyDataSetChanged();
    }


    public interface Callback {
        void onRepoEmptyViewRetryClick();
    }


}
