package com.CalSocial.ui.homeMain.adapter;

import android.view.View;
import android.widget.ImageView;

import com.CalSocial.R;
import com.CalSocial.ui.base.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SwarmFavoriteViewHolder extends BaseViewHolder {


    @BindView(R.id.image1)
    ImageView image1;

    @BindView(R.id.image2)
    ImageView image2;

    @BindView(R.id.image3)
    ImageView image3;

    @BindView(R.id.image4)
    ImageView image4;

    public SwarmFavoriteViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    protected void clear() {

    }
}
