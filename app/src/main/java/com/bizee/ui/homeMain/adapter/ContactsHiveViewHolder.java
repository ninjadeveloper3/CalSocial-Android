package com.CalSocial.ui.homeMain.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.CalSocial.R;
import com.CalSocial.data.network.model.ContactsHiveResponse;
import com.CalSocial.ui.base.BaseViewHolder;
import com.CalSocial.ui.events.EventsActivity;
import com.CalSocial.ui.homeMain.HomeMainActivity;
import com.CalSocial.utils.AppConstants;
import com.CalSocial.utils.ViewUtils;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactsHiveViewHolder extends BaseViewHolder {

    @BindView(R.id.image)
    ImageView image;

    @BindView(R.id.favorite)
    ImageView favorite;

    @BindView(R.id.memberName)
    TextView memberName;

    @BindView(R.id.newMessage)
    ImageView newMessage;

    @BindView(R.id.createEvent)
    ImageView createEvent;

    private List<ContactsHiveResponse.User> mUsersResponseList;
    private Context context;

    public ContactsHiveViewHolder(View itemView, List<ContactsHiveResponse.User> mUsersResponseList, Context context) {
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
            } else {
                favorite.setVisibility(View.GONE);
            }
            memberName.setText(user.getFirstName() + " " + user.getLastName());

            Glide.with(itemView.getContext())
                    .load(user.getProfileImageUrl())
                    .asBitmap()
                    .centerCrop()
                    .into(ViewUtils.getRoundedImageTarget(context, image, 15));


            newMessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, HomeMainActivity.class);
                    intent.putExtra("screenToOpen", AppConstants.MESSAGES_ONE_TO_ONE_FRAGMENT);
                    context.startActivity(intent);
                }
            });

            createEvent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, EventsActivity.class);
                    intent.putExtra("screenToOpen", AppConstants.CREATE_EVENT_FRAGMENT);
                    context.startActivity(intent);
                }
            });
        }

    }


}
