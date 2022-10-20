package com.CalSocial.ui.homeMain.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.CalSocial.R;
import com.CalSocial.data.network.model.NewMessageSelectContactsResponse;
import com.CalSocial.ui.base.BaseViewHolder;
import com.CalSocial.utils.ViewUtils;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactsCreateNewMessageSelectMembersViewHolder extends BaseViewHolder {

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

    @BindView(R.id.details)
    TextView details;

    private List<NewMessageSelectContactsResponse.User> mUsersResponseList;
    private Context context;

    public ContactsCreateNewMessageSelectMembersViewHolder(View itemView, List<NewMessageSelectContactsResponse.User> mUsersResponseList, Context context) {
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

            final NewMessageSelectContactsResponse.User user = mUsersResponseList.get(position);

            if (user.getType().equalsIgnoreCase("swarm")) {
                details.setVisibility(View.VISIBLE);
                String detailsText = "";
                if (user.getMembers().length > 5) {
                    detailsText = user.getMembers()[0] + ", " + user.getMembers()[1] + ", " + user.getMembers()[2] + " and " + (user.getMembers().length - 3) + " more";
                } else if (user.getMembers().length > 3) {
                    detailsText = user.getMembers()[0] + ", " + user.getMembers()[1] + ", " + user.getMembers()[2] + " and " + (user.getMembers().length - 3) + " more";
                } else if (user.getMembers().length > 1) {
                    detailsText = user.getMembers()[0] + " and " + user.getMembers()[1];
                }
                details.setText(detailsText);

            } else {
                details.setVisibility(View.GONE);
            }
            memberName.setText(user.getName());
            Glide.with(itemView.getContext())
                    .load(user.getProfileImageUrl())
                    .asBitmap()
                    .centerCrop()
                    .into(ViewUtils.getRoundedImageTarget(context, image, 15));

           /* if (user.getFavorite()) {
                favorite.setVisibility(View.VISIBLE);
            } else {
                favorite.setVisibility(View.GONE);
            }*/


        }

    }


}
