package com.CalSocial.ui.homeMain.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.CalSocial.R;
import com.CalSocial.data.network.model.ContactsHiveResponse;
import com.CalSocial.ui.base.BaseViewHolder;

import java.util.List;

public class ContactsCreateSwarmMembersAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE = 0;

    private Callback mCallback;
    private List<ContactsHiveResponse.User> mUsersResponseList;
    private Context context;

    public ContactsCreateSwarmMembersAdapter(List<ContactsHiveResponse.User> userList, Context context) {
        mUsersResponseList = userList;
        this.context = context;
    }

    public void setCallback(ContactsCreateSwarmMembersAdapter.Callback callback) {
        mCallback = callback;
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ContactsCreateSwarmMembersViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.contacts_item_create_swarm_members_layout, parent, false), mUsersResponseList, context);

    }


    @Override
    public int getItemViewType(int position) {

        return VIEW_TYPE;
    }

    @Override
    public int getItemCount() {
        if (mUsersResponseList != null && mUsersResponseList.size() > 0) {
            return mUsersResponseList.size();
        } else {
            return 1;
        }
    }


    public void addItems(List<ContactsHiveResponse.User> userList) {
        mUsersResponseList.addAll(userList);
        notifyDataSetChanged();
    }


    public interface Callback {
        void onRepoEmptyViewRetryClick();
    }


}
