package com.CalSocial.ui.events.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.CalSocial.R;
import com.CalSocial.data.network.model.UsersResponse;
import com.CalSocial.ui.base.BaseViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddGuestViewHolder extends BaseViewHolder {

    @BindView(R.id.memberName)
    TextView memberName;

    @BindView(R.id.details)
    TextView details;

    @BindView(R.id.image)
    ImageView image;

    @BindView(R.id.favorite)
    ImageView favorite;

    @BindView(R.id.selectGuest)
    ImageView selectGuest;

    private List<UsersResponse.User> mUsersResponseList;


    public AddGuestViewHolder(View itemView, List<UsersResponse.User> userList) {
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
                details.setText("Not in your hive");
                favorite.setVisibility(View.GONE);
            } else if (position % 4 == 0) {
                details.setVisibility(View.GONE);
                favorite.setVisibility(View.VISIBLE);

            } else {
                details.setVisibility(View.GONE);
                favorite.setVisibility(View.GONE);

            }

        }

    }
}
