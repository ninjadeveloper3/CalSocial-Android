package com.CalSocial.ui.homeMain.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.CalSocial.R;
import com.CalSocial.data.network.model.ContactsSwarmResponse;
import com.CalSocial.ui.base.BaseViewHolder;
import com.CalSocial.ui.events.EventsActivity;
import com.CalSocial.ui.homeMain.HomeMainActivity;
import com.CalSocial.utils.AppConstants;
import com.CalSocial.utils.ViewUtils;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactsSwarmViewHolder extends BaseViewHolder {

    @BindView(R.id.image)
    ImageView image;

    @BindView(R.id.favorite)
    ImageView favorite;

    @BindView(R.id.swarmName)
    TextView swarmName;

    @BindView(R.id.status)
    TextView status;

    @BindView(R.id.newMessage)
    ImageView newMessage;

    @BindView(R.id.createEvent)
    ImageView createEvent;

    private List<ContactsSwarmResponse.Swarm> mSwarmsResponseList;
    private Context context;

    public ContactsSwarmViewHolder(View itemView, List<ContactsSwarmResponse.Swarm> mSwarmsResponseList, Context context) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.mSwarmsResponseList = mSwarmsResponseList;
        this.context = context;

    }

    @Override
    protected void clear() {

    }

    public void onBind(int position) {
        super.onBind(position);

        if (position < mSwarmsResponseList.size()) {
            final ContactsSwarmResponse.Swarm swarm = mSwarmsResponseList.get(position);
            if (swarm.getFavorite()) {
                favorite.setVisibility(View.VISIBLE);
            } else {
                favorite.setVisibility(View.GONE);
            }
            swarmName.setText(swarm.getSwarmName());

            String statusText = "";
            for (int i = 0; i < swarm.getMembers().length; i++) {
                statusText = statusText + ", " + swarm.getMembers()[i];
            }

            if (swarm.getMembers().length > 5) {
                statusText = swarm.getMembers()[0] + ", " + swarm.getMembers()[1] + ", " + swarm.getMembers()[2] + " and " + (swarm.getMembers().length - 3) + " more";
            } else if (swarm.getMembers().length > 3) {
                statusText = swarm.getMembers()[0] + ", " + swarm.getMembers()[1] + ", " + swarm.getMembers()[2] + " and " + (swarm.getMembers().length - 3) + " more";
            } else if (swarm.getMembers().length > 1) {
                statusText = swarm.getMembers()[0] + " and " + swarm.getMembers()[1];
            }

            status.setText(statusText);

            Glide.with(itemView.getContext())
                    .load(swarm.getProfileImageUrl())
                    .asBitmap()
                    .centerCrop()
                    .into(ViewUtils.getRoundedImageTarget(context, image, 15));

            newMessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, HomeMainActivity.class);
                    intent.putExtra("screenToOpen", AppConstants.MESSAGES_ONE_TO_MANY_FRAGMENT);
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
