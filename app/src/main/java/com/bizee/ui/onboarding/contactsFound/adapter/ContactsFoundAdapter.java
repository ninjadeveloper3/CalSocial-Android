package com.CalSocial.ui.onboarding.contactsFound.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.CalSocial.R;
import com.CalSocial.data.network.model.ContactsFoundResponse;
import com.CalSocial.ui.base.BaseViewHolder;

import java.util.List;
import java.util.Random;

public class ContactsFoundAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_CONTACTS_FOUND = 0;

    private Callback mCallback;
    private List<ContactsFoundResponse.User> mUserResponseList;
    private Context context;
    private Random rand;


    public ContactsFoundAdapter(List<ContactsFoundResponse.User> userResponseList, Context context) {
        mUserResponseList = userResponseList;
        this.context = context;
        rand = new Random();
    }

    public void setCallback(ContactsFoundAdapter.Callback callback) {
        mCallback = callback;
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContactFoundViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.onboarding_item_contacts_found_item_layout, parent, false), mUserResponseList);
    }


    @Override
    public int getItemViewType(int position) {
        if (mUserResponseList != null && mUserResponseList.size() > 0) {
            return VIEW_TYPE_CONTACTS_FOUND;
        }

        return VIEW_TYPE_CONTACTS_FOUND;
    }

    @Override
    public int getItemCount() {
        if (mUserResponseList != null && mUserResponseList.size() > 0) {
            return mUserResponseList.size();
        } else {
            return 1;
        }
    }


    public void addItems(List<ContactsFoundResponse.User> userList) {
        mUserResponseList.addAll(userList);
        notifyDataSetChanged();
    }

    public interface Callback {
        void onRepoEmptyViewRetryClick();
    }

}
