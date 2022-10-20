package com.CalSocial.ui.homeMain.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.CalSocial.R;
import com.CalSocial.data.network.model.UsersResponse;
import com.CalSocial.ui.base.BaseViewHolder;
import com.CalSocial.ui.events.EventsActivity;
import com.CalSocial.ui.profile.ProfileActivity;
import com.CalSocial.utils.AppConstants;
import com.CalSocial.utils.ViewUtils;
import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoritesAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_SWARM = 1;
    public static final int VIEW_TYPE_CONTACT = 2;

    private Callback mCallback;
    private List<UsersResponse.User> mUserResponseList;
    private Context context;
    private Random rand;


    public FavoritesAdapter(List<UsersResponse.User> userResponseList, Context context) {
        mUserResponseList = userResponseList;
        this.context = context;
        rand = new Random();
    }

    public void setCallback(FavoritesAdapter.Callback callback) {
        mCallback = callback;
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case VIEW_TYPE_SWARM:
                return new FavoritesAdapter.SwarmFavoriteViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_favorites_slider_swarm_item_layout, parent, false));
            case VIEW_TYPE_CONTACT:
                return new FavoritesAdapter.ContactFavoriteViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_favorites_slider_contact_item_layout, parent, false));
            case VIEW_TYPE_EMPTY:
            default:
                return new FavoritesAdapter.EmptyFavoriteViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_favorites_slider_empty_layout, parent, false));
        }

    }


    @Override
    public int getItemViewType(int position) {
        if (mUserResponseList != null && mUserResponseList.size() > 0) {

            if (position % 2 == 0) {
                return VIEW_TYPE_SWARM;

            } else {
                return VIEW_TYPE_CONTACT;
            }


        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public int getItemCount() {
        if (mUserResponseList != null && mUserResponseList.size() > 0) {
            return mUserResponseList.size();
        } else {
            return 1;
        }
    }


    public void addItems(List<UsersResponse.User> userList) {
        mUserResponseList.addAll(userList);
        notifyDataSetChanged();
    }


    public interface Callback {
        void onRepoEmptyViewRetryClick();
    }

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

    public class ContactFavoriteViewHolder extends BaseViewHolder {

        @BindView(R.id.image)
        ImageView image;

        @BindView(R.id.title)
        TextView title;

        public ContactFavoriteViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void clear() {

        }

        public void onBind(int position) {
            super.onBind(position);

            final UsersResponse.User user = mUserResponseList.get(position);

            if (user.getProfileImageUrl() != null) {
                Glide.with(itemView.getContext())
                        .load(user.getProfileImageUrl())
                        .asBitmap()
                        .centerCrop()
                        .into(ViewUtils.getRoundedImageTarget(context, image, 15));

                image.setBackground(context.getResources().getDrawable(R.drawable.rounded_profile_card_white));
                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(itemView.getContext(), EventsActivity.class);
                        intent.putExtra("screenToOpen", AppConstants.CREATE_EVENT_FRAGMENT);
                        intent.putExtra("eventId", mUserResponseList.get(position).getId());
                        itemView.getContext().startActivity(intent);
                    }
                });


                image.setOnLongClickListener(new View.OnLongClickListener() {

                    @Override
                    public boolean onLongClick(View v) {
                        Intent intent = new Intent(itemView.getContext(), ProfileActivity.class);
                        intent.putExtra("screenToOpen", AppConstants.CONTACT_PROFILE_FRAGMENT);
                        intent.putExtra("userId", mUserResponseList.get(position).getId());
                        intent.putExtra("name", mUserResponseList.get(position).getFirstName() + " " + mUserResponseList.get(position).getLastName());
                        intent.putExtra("profileImage", mUserResponseList.get(position).getProfileImageUrl());
                        itemView.getContext().startActivity(intent);
                        return false;
                    }


                });

            }

            title.setText(user.getFirstName());


           /* if (repo.getTitle() != null) {
                titleTextView.setText(repo.getTitle());
            }

            if (repo.getDescription() != null) {
                contentTextView.setText(repo.getDescription());
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (repo.getProjectUrl() != null) {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.addCategory(Intent.CATEGORY_BROWSABLE);
                        intent.setData(Uri.parse(repo.getProjectUrl()));
                        itemView.getContext().startActivity(intent);
                    }
                }
            });*/
        }
    }


    public class SwarmFavoriteViewHolder extends BaseViewHolder {


        @BindView(R.id.image1)
        ImageView image1;

        @BindView(R.id.image2)
        ImageView image2;

        @BindView(R.id.image3)
        ImageView image3;

        @BindView(R.id.image4)
        ImageView image4;

        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.favorite)
        FrameLayout favorite;

        public SwarmFavoriteViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void clear() {

        }

        public void onBind(int position) {
            super.onBind(position);

            final UsersResponse.User user = mUserResponseList.get(position);

            if (user.getProfileImageUrl() != null) {
                Glide.with(itemView.getContext())
                        .load(user.getProfileImageUrl())
                        .asBitmap()
                        .centerCrop()
                        .into(ViewUtils.getRoundedImageTarget(context, image1, 15));

                image1.setBackground(context.getResources().getDrawable(R.drawable.rounded_profile_card_white));

                Glide.with(itemView.getContext())
                        .load(user.getProfileImageUrl())
                        .asBitmap()
                        .centerCrop()
                        .into(ViewUtils.getRoundedImageTarget(context, image2, 15));

                image2.setBackground(context.getResources().getDrawable(R.drawable.rounded_profile_card_white));

                Glide.with(itemView.getContext())
                        .load(user.getProfileImageUrl())
                        .asBitmap()
                        .centerCrop()
                        .into(ViewUtils.getRoundedImageTarget(context, image3, 15));

                image3.setBackground(context.getResources().getDrawable(R.drawable.rounded_profile_card_white));

                Glide.with(itemView.getContext())
                        .load(user.getProfileImageUrl())
                        .asBitmap()
                        .centerCrop()
                        .into(ViewUtils.getRoundedImageTarget(context, image4, 15));

                image4.setBackground(context.getResources().getDrawable(R.drawable.rounded_profile_card_white));


            }

            favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(itemView.getContext(), EventsActivity.class);
                    intent.putExtra("screenToOpen", AppConstants.CREATE_EVENT_FRAGMENT);
                    intent.putExtra("eventId", mUserResponseList.get(position).getId());
                    itemView.getContext().startActivity(intent);
                }
            });


            favorite.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), ProfileActivity.class);
                    intent.putExtra("screenToOpen", AppConstants.SWARM_PROFILE_FRAGMENT);
                    intent.putExtra("userId", mUserResponseList.get(position).getId());
                    intent.putExtra("name", mUserResponseList.get(position).getFirstName() + " " + mUserResponseList.get(position).getLastName());
                    intent.putExtra("profileImage", mUserResponseList.get(position).getProfileImageUrl());
                    itemView.getContext().startActivity(intent);
                    return false;
                }


            });

            title.setText(user.getFirstName());


           /* if (repo.getTitle() != null) {
                titleTextView.setText(repo.getTitle());
            }

            if (repo.getDescription() != null) {
                contentTextView.setText(repo.getDescription());
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (repo.getProjectUrl() != null) {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.addCategory(Intent.CATEGORY_BROWSABLE);
                        intent.setData(Uri.parse(repo.getProjectUrl()));
                        itemView.getContext().startActivity(intent);
                    }
                }
            });*/
        }
    }
}
