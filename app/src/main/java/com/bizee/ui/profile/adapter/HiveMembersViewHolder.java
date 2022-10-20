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

public class HiveMembersViewHolder extends BaseViewHolder {

    @BindView(R.id.memberName)
    TextView memberName;

    @BindView(R.id.status)
    TextView status;

    @BindView(R.id.image)
    ImageView image;

    @BindView(R.id.addToHive)
    ImageView addToHive;

    @BindView(R.id.requestSent)
    TextView requestSent;

    private List<UsersResponse.User> mUsersResponseList;


    public HiveMembersViewHolder(View itemView, List<UsersResponse.User> userList) {
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

            if (position % 3 == 0) {
                status.setText("Not in your hive");
                addToHive.setVisibility(View.VISIBLE);
            } else if (position % 4 == 0) {
                status.setVisibility(View.GONE);
                addToHive.setVisibility(View.GONE);
                requestSent.setVisibility(View.VISIBLE);

            } else {
                status.setVisibility(View.GONE);
                addToHive.setVisibility(View.GONE);
                requestSent.setVisibility(View.GONE);

            }

        }

    }
}
