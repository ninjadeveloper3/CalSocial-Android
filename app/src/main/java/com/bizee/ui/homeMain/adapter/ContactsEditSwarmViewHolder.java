package com.CalSocial.ui.homeMain.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.CalSocial.R;
import com.CalSocial.data.network.model.ContactsSwarmResponse;
import com.CalSocial.ui.base.BaseViewHolder;
import com.CalSocial.utils.ViewUtils;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactsEditSwarmViewHolder extends BaseViewHolder {

    @BindView(R.id.image)
    ImageView image;

    @BindView(R.id.favorite)
    ImageView favorite;

    @BindView(R.id.swarmName)
    TextView swarmName;

    @BindView(R.id.removeSwarm)
    ImageView removeSwarm;

    @BindView(R.id.leaveSwarm)
    TextView leaveSwarm;

    private List<ContactsSwarmResponse.Swarm> mSwarmsResponseList;
    private Context context;

    public ContactsEditSwarmViewHolder(View itemView, List<ContactsSwarmResponse.Swarm> mSwarmsResponseList, Context context) {
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

            if (position % 4 == 0) {
                removeSwarm.setVisibility(View.GONE);
                leaveSwarm.setVisibility(View.VISIBLE);
            } else {
                removeSwarm.setVisibility(View.VISIBLE);
                leaveSwarm.setVisibility(View.GONE);
            }

            Glide.with(itemView.getContext())
                    .load(swarm.getProfileImageUrl())
                    .asBitmap()
                    .centerCrop()
                    .into(ViewUtils.getRoundedImageTarget(context, image, 15));

        }

    }


}
