package com.CalSocial.ui.homeMain.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.CalSocial.R;
import com.CalSocial.data.network.model.ContactsHiveResponse;
import com.CalSocial.ui.base.BaseViewHolder;

import java.util.List;

public class ContactsHiveAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_VIEW = 0;
    public static final int VIEW_TYPE_EDIT = 1;

    private Callback mCallback;
    private List<ContactsHiveResponse.User> mUsersResponseList;
    private Context context;
    private int mode = 0;

    public ContactsHiveAdapter(List<ContactsHiveResponse.User> userList, Context context, int mode) {
        mUsersResponseList = userList;
        this.context = context;
        this.mode = mode;

    }

    public void setCallback(ContactsHiveAdapter.Callback callback) {
        mCallback = callback;
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        if (viewType == VIEW_TYPE_VIEW) {
            return new ContactsHiveViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.contacts_item_hive_layout, parent, false), mUsersResponseList, context);


        } else {
            return new ContactsEditHiveViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.contacts_item_edit_hive_members_layout, parent, false), mUsersResponseList, context);

        }

    }


    @Override
    public int getItemViewType(int position) {

        if (mode == 0) {
            return VIEW_TYPE_VIEW;
        } else {
            return VIEW_TYPE_EDIT;
        }

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

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

}
