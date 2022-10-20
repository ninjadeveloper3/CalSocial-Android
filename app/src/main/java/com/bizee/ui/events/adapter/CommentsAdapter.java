package com.CalSocial.ui.events.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.CalSocial.R;
import com.CalSocial.data.network.model.CommentsResponse;
import com.CalSocial.ui.base.BaseViewHolder;

import java.util.List;
import java.util.Random;

public class CommentsAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE = 0;
    private Callback mCallback;
    private List<CommentsResponse.Comment> mCommentResponseList;
    private Context context;
    private Random rand;


    public CommentsAdapter(List<CommentsResponse.Comment> commentResponseList, Context context) {
        mCommentResponseList = commentResponseList;
        this.context = context;
        rand = new Random();
    }

    public void setCallback(CommentsAdapter.Callback callback) {
        mCallback = callback;
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new CommentsViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.events_item_comment_layout, parent, false), mCommentResponseList, context);

    }


    @Override
    public int getItemViewType(int position) {
        if (mCommentResponseList != null && mCommentResponseList.size() > 0) {
            return VIEW_TYPE;
        }

        return VIEW_TYPE;

    }

    @Override
    public int getItemCount() {
        if (mCommentResponseList != null && mCommentResponseList.size() > 0) {
            return mCommentResponseList.size();
        } else {
            return 1;
        }
    }


    public void addItems(List<CommentsResponse.Comment> commentList) {
        mCommentResponseList.addAll(commentList);
        notifyDataSetChanged();
    }


    public interface Callback {
        void onRepoEmptyViewRetryClick();
    }

}
