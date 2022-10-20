package com.CalSocial.ui.onboarding.contactsFound.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.CalSocial.R;
import com.CalSocial.data.network.model.ContactsFoundResponse;
import com.CalSocial.ui.base.BaseViewHolder;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactFoundViewHolder extends BaseViewHolder {

    @BindView(R.id.contactName)
    TextView contactName;

    @BindView(R.id.contactSelector)
    ImageView contactSelector;

    private List<ContactsFoundResponse.User> userList;

    @Inject
    public ContactFoundViewHolder(View itemView, List<ContactsFoundResponse.User> userList) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.userList = userList;
    }

    @Override
    protected void clear() {

    }

    public void onBind(int position) {
        super.onBind(position);

        if (position < userList.size()) {
            final ContactsFoundResponse.User user = userList.get(position);
            contactName.setText(user.getFirstName() + " " + user.getLastName());
        }

    }
}
