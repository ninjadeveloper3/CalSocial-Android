package com.CalSocial.ui.homeMain.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.CalSocial.R;
import com.CalSocial.data.network.model.ContactsHiveResponse;
import com.CalSocial.ui.base.BaseViewHolder;
import com.CalSocial.utils.ViewUtils;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactsCreateSwarmSelectMembersViewHolder extends BaseViewHolder {

    @BindView(R.id.image)
    ImageView image;

    @BindView(R.id.memberName)
    TextView memberName;

    @BindView(R.id.favorite)
    ImageView favorite;

    @BindView(R.id.selectGuest)
    ImageView selectGuest;

    @BindView(R.id.selectGuestSelected)
    ImageView selectGuestSelected;

    @BindView(R.id.contactsFoundItem)
    RelativeLayout contactsFoundItem;

    private List<ContactsHiveResponse.User> mUsersResponseList;
    private Context context;

    public ContactsCreateSwarmSelectMembersViewHolder(View itemView, List<ContactsHiveResponse.User> mUsersResponseList, Context context) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.mUsersResponseList = mUsersResponseList;
        this.context = context;

    }

    @Override
    protected void clear() {

    }

    public void onBind(int position) {
        super.onBind(position);

        if (position < mUsersResponseList.size()) {

            contactsFoundItem.setVisibility(View.VISIBLE);
            if (position == 0) {
                selectGuest.setVisibility(View.INVISIBLE);
                selectGuestSelected.setVisibility(View.VISIBLE);
            }

            final ContactsHiveResponse.User user = mUsersResponseList.get(position);
            memberName.setText(user.getFirstName() + " " + user.getLastName());
            Glide.with(itemView.getContext())
                    .load(user.getProfileImageUrl())
                    .asBitmap()
                    .centerCrop()
                    .into(ViewUtils.getRoundedImageTarget(context, image, 15));

        }

    }


}
