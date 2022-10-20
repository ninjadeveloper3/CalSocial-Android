package com.CalSocial.ui.profile.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.CalSocial.R;
import com.CalSocial.data.network.model.UsersResponse;
import com.CalSocial.ui.base.BaseViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SwarmMembersViewHolder extends BaseViewHolder {

    @BindView(R.id.memberName)
    TextView memberName;

    @BindView(R.id.status)
    TextView status;

    @BindView(R.id.image)
    ImageView image;

    @BindView(R.id.addToHive)
    ImageView addToHive;


    private List<UsersResponse.User> mUsersResponseList;


    public SwarmMembersViewHolder(View itemView, List<UsersResponse.User> userList) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mUsersResponseList = userList;

    }

    @Override
    protected void clear() {

    }


    public void onBind(int position) {
        super.onBind(position);

        if (position < mUsersResponseList.size()) {
            final UsersResponse.User user = mUsersResponseList.get(position);
            memberName.setText(user.getFirstName() + " " + user.getLastName());
            if (position == 0) {
                status.setText("Swarm Owner");
                addToHive.setVisibility(View.GONE);
            } else {
                status.setText("Not in your hive");
            }

        }

    }
}
