package com.CalSocial.ui.events.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.CalSocial.R;
import com.CalSocial.data.network.model.EventAttendeesResponse;
import com.CalSocial.ui.base.BaseViewHolder;
import com.CalSocial.ui.profile.ProfileActivity;
import com.CalSocial.utils.AppConstants;
import com.CalSocial.utils.ViewUtils;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CalSocialTimeSuggestionsAttendeeViewHolder extends BaseViewHolder {

    @BindView(R.id.addGuestAttendeeLL)
    LinearLayout addGuestAttendeeLL;

    @BindView(R.id.attendeeCV)
    CardView attendeeCV;

    @BindView(R.id.attendeeImage)
    ImageView attendeeImage;

    @BindView(R.id.attendeeName)
    TextView attendeeName;

    @BindView(R.id.attendeeStatus)
    ImageView attendeeStatus;

    private List<EventAttendeesResponse.User> mUsersResponseList;
    private Context context;

    public CalSocialTimeSuggestionsAttendeeViewHolder(View itemView, List<EventAttendeesResponse.User> userList, Context context) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mUsersResponseList = userList;
        this.context = context;

    }

    @Override
    protected void clear() {

    }

    public void onBind(int position) {
        super.onBind(position);

        addGuestAttendeeLL.setVisibility(View.GONE);
        attendeeCV.setVisibility(View.VISIBLE);

        if (position < mUsersResponseList.size()) {

            final EventAttendeesResponse.User user = mUsersResponseList.get(position);

            if (user.getProfileImageUrl() != null) {
                Glide.with(itemView.getContext())
                        .load(user.getProfileImageUrl())
                        .asBitmap()
                        .centerCrop()
                        .into(ViewUtils.getRoundedImageTarget(context, attendeeImage, 15));

           /* image.setBackground(context.getResources().getDrawable(R.drawable.rounded_profile_card_white));
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), ProfileActivity.class);
                    intent.putExtra("screenToOpen", AppConstants.CONTACT_PROFILE_FRAGMENT);
                    intent.putExtra("userId", mUserResponseList.get(position).getId());
                    itemView.getContext().startActivity(intent);
                }
            });*/
            }

            attendeeName.setText(user.getFirstName());

            if (user.getGoingStatus().compareToIgnoreCase(AppConstants.GOING_EVENT) == 0) {
                attendeeStatus.setBackground(context.getDrawable(R.drawable.attendee_going_status_shape));
            } else if (user.getGoingStatus().compareToIgnoreCase(AppConstants.NOT_GOING_EVENT) == 0) {
                attendeeStatus.setBackground(context.getDrawable(R.drawable.attendee_not_going_status_shape));
            } else if (user.getGoingStatus().compareToIgnoreCase(AppConstants.MAYBE_GOING_EVENT) == 0) {
                attendeeStatus.setBackground(context.getDrawable(R.drawable.attendee_maybe_status_shape));
            } else if (user.getGoingStatus().compareToIgnoreCase(AppConstants.NO_RESPONSE_EVENT) == 0) {
                attendeeStatus.setBackground(context.getDrawable(R.drawable.attendee_no_response_status_shape));
            }


            attendeeCV.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (position == 0) {
                        Intent intent = new Intent(itemView.getContext(), ProfileActivity.class);
                        intent.putExtra("screenToOpen", AppConstants.YOUR_PROFILE_FRAGMENT);
                        intent.putExtra("userId", mUsersResponseList.get(position).getId());
                        intent.putExtra("name", mUsersResponseList.get(position).getFirstName() + " " + mUsersResponseList.get(position).getLastName());
                        intent.putExtra("profileImage", mUsersResponseList.get(position).getProfileImageUrl());
                        itemView.getContext().startActivity(intent);
                    } else {
                        Intent intent = new Intent(itemView.getContext(), ProfileActivity.class);
                        intent.putExtra("screenToOpen", AppConstants.CONTACT_PROFILE_FRAGMENT);
                        intent.putExtra("userId", mUsersResponseList.get(position).getId());
                        intent.putExtra("name", mUsersResponseList.get(position).getFirstName() + " " + mUsersResponseList.get(position).getLastName());
                        intent.putExtra("profileImage", mUsersResponseList.get(position).getProfileImageUrl());
                        itemView.getContext().startActivity(intent);
                    }

                    return false;
                }
            });

        }


    }
}
