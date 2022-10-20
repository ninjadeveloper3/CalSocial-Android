package com.CalSocial.ui.homeMain.adapter;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.CalSocial.R;
import com.CalSocial.data.network.model.EventsHomeResponse;
import com.CalSocial.ui.base.BaseViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventNonCalSocialEventViewHolder extends BaseViewHolder {

    @BindView(R.id.startTime)
    TextView startTime;

    @BindView(R.id.endTime)
    TextView endTime;

    @BindView(R.id.eventTitle)
    TextView eventTitle;

    @BindView(R.id.nonCalSocialEventItemRV)
    RelativeLayout nonCalSocialEventItemRV;

    private List<EventsHomeResponse.Event> mEventsHomeResponseList;
    private Context context;

    public EventNonCalSocialEventViewHolder(View itemView, List<EventsHomeResponse.Event> eventsHomeResponseList, Context context) {
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
            nonCalSocialEventItemRV.setVisibility(View.VISIBLE);
        }

    }
}
