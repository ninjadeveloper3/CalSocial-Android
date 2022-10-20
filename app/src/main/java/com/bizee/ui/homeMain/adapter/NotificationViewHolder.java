package com.CalSocial.ui.homeMain.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.CalSocial.R;
import com.CalSocial.data.network.model.NotificationsResponse;
import com.CalSocial.ui.base.BaseViewHolder;
import com.CalSocial.utils.ViewUtils;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationViewHolder extends BaseViewHolder {

    @BindView(R.id.notificationAlert)
    ImageView notificationAlert;

    @BindView(R.id.image)
    ImageView image;

    @BindView(R.id.memberName)
    TextView memberName;

    @BindView(R.id.notificationText)
    TextView notificationText;

    @BindView(R.id.notificationTime)
    TextView notificationTime;

    @BindView(R.id.notificationItem)
    RelativeLayout notificationItem;

    private List<NotificationsResponse.Notification> mNotificationsResponseList;
    private Context context;

    public NotificationViewHolder(View itemView, List<NotificationsResponse.Notification> eventsHomeResponseList, Context context) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mNotificationsResponseList = eventsHomeResponseList;
        this.context = context;

    }

    @Override
    protected void clear() {

    }

    public void onBind(int position) {
        super.onBind(position);

        if (position < mNotificationsResponseList.size()) {
            notificationItem.setVisibility(View.VISIBLE);
            final NotificationsResponse.Notification notification = mNotificationsResponseList.get(position);
            if (notification.getNotificationStatus().equalsIgnoreCase("new")) {
                notificationAlert.setVisibility(View.VISIBLE);
            } else {
                notificationAlert.setVisibility(View.INVISIBLE);
            }

            memberName.setText(notification.getFirstName() + " " + notification.getLastName());
            notificationText.setText(notification.getNotification());
            notificationTime.setText(notification.getNotificationTime());
            Glide.with(itemView.getContext())
                    .load(notification.getProfileImageUrl())
                    .asBitmap()
                    .centerCrop()
                    .into(ViewUtils.getRoundedImageTarget(context, image, 15));

            notificationItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

    }


}
