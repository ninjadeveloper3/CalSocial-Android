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

public class EventDateViewHolder extends BaseViewHolder {

    @BindView(R.id.dateItemTV)
    TextView dateItemTV;

    @BindView(R.id.dateItemRV)
    RelativeLayout dateItemRV;
    private Context context;

    private List<EventsHomeResponse.Event> mEventsHomeResponseList;

    public EventDateViewHolder(View itemView, List<EventsHomeResponse.Event> eventsHomeResponseList, Context context) {
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
            dateItemRV.setVisibility(View.VISIBLE);
            dateItemTV.setText(event.getDateOfEvent());

        }

    }
}
