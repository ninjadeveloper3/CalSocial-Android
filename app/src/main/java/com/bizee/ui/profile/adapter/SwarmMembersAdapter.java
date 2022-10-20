package com.CalSocial.ui.profile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.CalSocial.R;
import com.CalSocial.data.network.model.UsersResponse;
import com.CalSocial.ui.base.BaseViewHolder;

import java.util.List;
import java.util.Random;

public class SwarmMembersAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_MEMBER = 1;

    private Callback mCallback;
    private List<UsersResponse.User> mUsersResponseList;
    private Context context;
    private Random rand;


    public SwarmMembersAdapter(List<UsersResponse.User> userList, Context context) {
        mUsersResponseList = userList;
        this.context = context;
        rand = new Random();
    }

    public void setCallback(SwarmMembersAdapter.Callback callback) {
        mCallback = callback;
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new SwarmMembersViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.profiles_item_swarm_profile_members_item_layout, parent, false), mUsersResponseList);

    }


    @Override
    public int getItemViewType(int position) {
        return VIEW_TYPE_MEMBER;
    }

    @Override
    public int getItemCount() {
        if (mUsersResponseList != null && mUsersResponseList.size() > 0) {
            return mUsersResponseList.size();
        } else {
            return 1;
        }
    }


    public void addItems(List<UsersResponse.User> userList) {
        mUsersResponseList.addAll(userList);
        notifyDataSetChanged();
    }


    public interface Callback {
        void onRepoEmptyViewRetryClick();
    }


}
