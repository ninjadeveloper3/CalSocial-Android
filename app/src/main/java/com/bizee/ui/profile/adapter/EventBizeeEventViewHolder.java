package com.CalSocial.ui.profile.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.CalSocial.R;
import com.CalSocial.data.network.model.EventsHomeResponse;
import com.CalSocial.ui.base.BaseViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventCalSocialEventViewHolder extends BaseViewHolder {

    @BindView(R.id.dayOfEvent)
    TextView dayOfEvent;

    @BindView(R.id.dateOfEvent)
    TextView dateOfEvent;

    @BindView(R.id.eventImage)
    ImageView eventImage;

    @BindView(R.id.eventTitle)
    TextView eventTitle;

    @BindView(R.id.goingStatus)
    TextView goingStatus;

    private List<EventsHomeResponse.Event> mEventsHomeResponseList;


    public EventCalSocialEventViewHolder(View itemView, List<EventsHomeResponse.Event> eventsHomeResponseList) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mEventsHomeResponseList = eventsHomeResponseList;

    }

    @Override
    protected void clear() {

    }


    public void onBind(int position) {
        super.onBind(position);

        if (position < mEventsHomeResponseList.size()) {
            final EventsHomeResponse.Event event = mEventsHomeResponseList.get(position);
            eventTitle.setText(event.getTitle());
            String day = event.getDateOfEvent().split(", ")[0];
            String date = event.getDateOfEvent().split(", ")[1];
            String month = date.split(" ")[1];
            date = date.split(" ")[0];
            dayOfEvent.setText(day.substring(0, 3));
            dateOfEvent.setText(month + " " + date);
            goingStatus.setText(event.getGoingStatus());
        }

    }
}
