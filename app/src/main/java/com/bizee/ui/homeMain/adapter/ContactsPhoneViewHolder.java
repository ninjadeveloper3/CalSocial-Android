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

public class ContactsPhoneViewHolder extends BaseViewHolder {

    @BindView(R.id.image)
    ImageView image;

    @BindView(R.id.memberName)
    TextView memberName;

    @BindView(R.id.status)
    TextView status;

    @BindView(R.id.addToHive)
    ImageView addToHive;

    @BindView(R.id.sendRequest)
    ImageView sendRequest;

    @BindView(R.id.requestSent)
    TextView requestSent;

    private List<ContactsHiveResponse.User> mUsersResponseList;
    private Context context;

    public ContactsPhoneViewHolder(View itemView, List<ContactsHiveResponse.User> mUsersResponseList, Context context) {
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

            memberName.setText(user.getFirstName() + " " + user.getLastName());

            /*if (user.getCalSocial()) {
                status.setText("");
                status.setVisibility(View.GONE);
                addToHive.setVisibility(View.VISIBLE);
                sendRequest.setVisibility(View.GONE);
                requestSent.setVisibility(View.GONE);
            } else {
                status.setText("I don't have CalSocial");
                status.setVisibility(View.VISIBLE);
                addToHive.setVisibility(View.GONE);
                sendRequest.setVisibility(View.VISIBLE);
                requestSent.setVisibility(View.GONE);
            }*/

            if (position % 5 == 0) {
                status.setText("I don't have CalSocial");
                addToHive.setVisibility(View.GONE);
                sendRequest.setVisibility(View.VISIBLE);
                requestSent.setVisibility(View.GONE);
            } else if (position % 2 == 0 || position % 3 == 0) {
                status.setText("");
                status.setVisibility(View.GONE);
                addToHive.setVisibility(View.VISIBLE);
                sendRequest.setVisibility(View.GONE);
                requestSent.setVisibility(View.GONE);
            } else if (position % 1 == 0) {
                status.setText("");
                status.setVisibility(View.GONE);
                addToHive.setVisibility(View.GONE);
                sendRequest.setVisibility(View.GONE);
                requestSent.setVisibility(View.VISIBLE);
            }


            if (user.getProfileImageUrl().equalsIgnoreCase("")) {
                Glide.with(itemView.getContext())
                        .load(R.drawable.profile_pic)
                        .asBitmap()
                        .centerCrop()
                        .into(ViewUtils.getRoundedImageTarget(context, image, 15));
            } else {
                Glide.with(itemView.getContext())
                        .load(user.getProfileImageUrl())
                        .asBitmap()
                        .centerCrop()
                        .into(ViewUtils.getRoundedImageTarget(context, image, 15));
            }


        }

    }


}
