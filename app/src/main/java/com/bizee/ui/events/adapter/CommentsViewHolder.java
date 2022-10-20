package com.CalSocial.ui.events.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.CalSocial.R;
import com.CalSocial.data.network.model.CommentsResponse;
import com.CalSocial.ui.base.BaseViewHolder;
import com.CalSocial.utils.ViewUtils;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentsViewHolder extends BaseViewHolder {

    @BindView(R.id.commenterProfileImage)
    ImageView commenterProfileImage;

    @BindView(R.id.commenterName)
    TextView commenterName;

    @BindView(R.id.commentTime)
    TextView commentTime;

    @BindView(R.id.message)
    TextView message;

    @BindView(R.id.imageLL)
    LinearLayout imageLL;

    @BindView(R.id.image)
    ImageView image;

    private List<CommentsResponse.Comment> mCommentsResponseList;
    private Context context;

    public CommentsViewHolder(View itemView, List<CommentsResponse.Comment> commentList, Context context) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mCommentsResponseList = commentList;
        this.context = context;

    }

    @Override
    protected void clear() {

    }

    public void onBind(int position) {
        super.onBind(position);

        if (position < mCommentsResponseList.size()) {

            final CommentsResponse.Comment comment = mCommentsResponseList.get(position);

            if (comment.getMessageType().equalsIgnoreCase("picture")) {
                Glide.with(itemView.getContext())
                        .load(comment.getProfileImageUrl())
                        .asBitmap()
                        .centerCrop()
                        .into(ViewUtils.getRoundedImageTarget(context, image, 15));
                imageLL.setVisibility(View.VISIBLE);
                message.setVisibility(View.GONE);
            } else {
                imageLL.setVisibility(View.GONE);
                message.setVisibility(View.VISIBLE);
                message.setText(comment.getMessage());
            }

            if (comment.getProfileImageUrl() != null) {
                Glide.with(itemView.getContext())
                        .load(comment.getProfileImageUrl())
                        .asBitmap()
                        .centerCrop()
                        .into(ViewUtils.getRoundedImageTarget(context, commenterProfileImage, 15));

            }

            commenterName.setText(comment.getCommenterName());
            commentTime.setText(comment.getTimeOfMessage());

        }

    }
}
