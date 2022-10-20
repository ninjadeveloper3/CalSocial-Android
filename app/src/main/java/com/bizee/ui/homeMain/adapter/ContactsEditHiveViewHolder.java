package com.CalSocial.ui.homeMain.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.CalSocial.R;
import com.CalSocial.data.network.model.ContactsHiveResponse;
import com.CalSocial.ui.base.BaseViewHolder;
import com.CalSocial.utils.ViewUtils;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactsEditHiveViewHolder extends BaseViewHolder {

    @BindView(R.id.image)
    ImageView image;

    @BindView(R.id.favorite)
    ImageView favorite;

    @BindView(R.id.unfavorite)
    ImageView unfavorite;

    @BindView(R.id.memberName)
    TextView memberName;

    @BindView(R.id.removeGuest)
    ImageView removeGuest;

    private List<ContactsHiveResponse.User> mUsersResponseList;
    private Context context;

    public ContactsEditHiveViewHolder(View itemView, List<ContactsHiveResponse.User> mUsersResponseList, Context context) {
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
            final ContactsHiveResponse.User user = mUsersResponseList.get(position);
            if (user.getFavorite()) {
                favorite.setVisibility(View.VISIBLE);
                unfavorite.setVisibility(View.GONE);
            } else {
                favorite.setVisibility(View.INVISIBLE);
                unfavorite.setVisibility(View.VISIBLE);
            }
            memberName.setText(user.getFirstName() + " " + user.getLastName());

            Glide.with(itemView.getContext())
                    .load(user.getProfileImageUrl())
                    .asBitmap()
                    .centerCrop()
                    .into(ViewUtils.getRoundedImageTarget(context, image, 15));

        }

    }


}
