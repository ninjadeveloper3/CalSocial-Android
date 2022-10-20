package com.CalSocial.ui.homeMain.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.CalSocial.R;
import com.CalSocial.data.network.model.EventsHomeResponse;
import com.CalSocial.ui.base.BaseViewHolder;
import com.CalSocial.ui.events.EventsActivity;
import com.CalSocial.utils.AppConstants;
import com.CalSocial.utils.ViewUtils;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventCalSocialEventViewHolder extends BaseViewHolder {


    @BindView(R.id.startTime)
    TextView startTime;

    @BindView(R.id.endTime)
    TextView endTime;

    @BindView(R.id.eventImage)
    ImageView eventImage;

    @BindView(R.id.eventTitle)
    TextView eventTitle;

    @BindView(R.id.goingStatus)
    TextView goingStatus;

    @BindView(R.id.eventCalSocialCV)
    CardView eventCalSocialCV;

    private List<EventsHomeResponse.Event> mEventsHomeResponseList;
    private Context context;


    public EventCalSocialEventViewHolder(View itemView, List<EventsHomeResponse.Event> eventsHomeResponseList, Context context) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mEventsHomeResponseList = eventsHomeResponseList;
        this.context = context;
    }

    @Override
    protected void clear() {

    }


    public void onBind(int position) {
        super.onBind(position);

        if (position < mEventsHomeResponseList.size()) {
            final EventsHomeResponse.Event event = mEventsHomeResponseList.get(position);
            eventTitle.setText(event.getTitle());
            startTime.setText(event.getStartTime());
            endTime.setText(event.getEndTime());
            goingStatus.setText(event.getGoingStatus());
            eventCalSocialCV.setVisibility(View.VISIBLE);

            Glide.with(itemView.getContext())
                    .load(R.drawable.profile_image)
                    .asBitmap()
                    .centerCrop()
                    .into(ViewUtils.getRoundedImageTarget(context, eventImage, 15));

            eventCalSocialCV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), EventsActivity.class);
                    intent.putExtra("screenToOpen", AppConstants.EVENT_DETAIL_FRAGMENT);
                    intent.putExtra("eventId", mEventsHomeResponseList.get(position).getId());
                    itemView.getContext().startActivity(intent);
                }
            });
        }

    }
}
