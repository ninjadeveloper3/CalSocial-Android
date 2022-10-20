package com.CalSocial.ui.homeMain.adapter;

import android.view.View;
import android.widget.TextView;

import com.CalSocial.R;
import com.CalSocial.ui.base.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EmptyFavoriteViewHolder extends BaseViewHolder {

    @BindView(R.id.message)
    TextView message;

    public EmptyFavoriteViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    protected void clear() {

    }

}

