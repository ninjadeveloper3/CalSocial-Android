package com.CalSocial.ui.homeMain.adapter;

import android.view.View;
import android.widget.ImageView;

import com.CalSocial.R;
import com.CalSocial.ui.base.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactFavoriteViewHolder extends BaseViewHolder {

    @BindView(R.id.image)
    ImageView image;

    public ContactFavoriteViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    protected void clear() {

    }
}
