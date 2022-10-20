package com.CalSocial.ui.profile.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.CalSocial.R;
import com.CalSocial.data.network.model.UsersResponse;
import com.CalSocial.ui.base.BaseViewHolder;
import com.CalSocial.utils.ViewUtils;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditSwarmMembersViewHolder extends BaseViewHolder {

    @BindView(R.id.memberName)
    TextView memberName;

    @BindView(R.id.status)
    TextView status;

    @BindView(R.id.image)
    ImageView image;

    @BindView(R.id.removeMember)
    ImageView removeMember;

    @BindView(R.id.contactsFoundItem)
    RelativeLayout contactsFoundItem;


    private List<UsersResponse.User> mUsersResponseList;
    private Context context;

    public EditSwarmMembersViewHolder(View itemView, List<UsersResponse.User> userList, Context context) {
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

        if (position < mUsersResponseList.size()) {

            contactsFoundItem.setVisibility(View.VISIBLE);

            if (mUsersResponseList.get(position).getProfileImageUrl() != null) {
                Glide.with(itemView.getContext())
                        .load(mUsersResponseList.get(position).getProfileImageUrl())
                        .asBitmap()
                        .centerCrop()
                        .into(ViewUtils.getRoundedImageTarget(context, image, 15));
            }
            final UsersResponse.User user = mUsersResponseList.get(position);
            memberName.setText(user.getFirstName() + " " + user.getLastName());
            if (position == 0) {
                status.setText("Swarm Owner");
                removeMember.setVisibility(View.INVISIBLE);
            } else {
                status.setVisibility(View.INVISIBLE);
                removeMember.setVisibility(View.VISIBLE);
            }

        }

    }
}
