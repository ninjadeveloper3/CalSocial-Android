package com.CalSocial.ui.profile.yourProfile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.CalSocial.R;
import com.CalSocial.di.component.ActivityComponent;
import com.CalSocial.ui.base.BaseFragment;
import com.CalSocial.utils.AppConstants;
import com.CalSocial.utils.ViewUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YourProfileFragment extends BaseFragment implements YourProfileMvpView {

    public static final String TAG = "YourProfileFragment";

    @BindView(R.id.coverImage)
    ImageView coverImage;

    @BindView(R.id.profileImage)
    ImageView profileImage;

    @BindView(R.id.name)
    TextView nameTV;

    @BindView(R.id.location)
    TextView locationTV;

    @BindView(R.id.bioDescription)
    TextView bioDescriptionTV;

    @BindView(R.id.bucketListTitle)
    TextView bucketListItemTitle;

    @BindView(R.id.bucketItemOne)
    LinearLayout bucketItemOne;

    @BindView(R.id.bucketItemOneText)
    TextView bucketItemOneText;

    @BindView(R.id.bucketItemTwo)
    LinearLayout bucketItemTwo;

    @BindView(R.id.bucketItemTwoText)
    TextView bucketItemTwoText;

    @BindView(R.id.bucketItemThree)
    LinearLayout bucketItemThree;

    @BindView(R.id.bucketItemThreeText)
    TextView bucketItemThreeText;

    @BindView(R.id.bucketItemFour)
    LinearLayout bucketItemFour;

    @BindView(R.id.bucketItemFourText)
    TextView bucketItemFourText;

    @BindView(R.id.bucketItemFive)
    LinearLayout bucketItemFive;

    @BindView(R.id.bucketItemFiveText)
    TextView bucketItemFiveText;


    @Inject
    YourProfilePresenter<YourProfileMvpView> mPresenter;

    public static YourProfileFragment newInstance() {
        Bundle args = new Bundle();
        YourProfileFragment fragment = new YourProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profiles_fragment_your_profile, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
        }

        mPresenter.getProfileDetails();

        Glide.with(getActivity())
                .load("https://images.unsplash.com/photo-1539605480396-a61f99da1041?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1                                                        50&q=80")
                .asBitmap()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ViewUtils.getRoundedImageTarget(getActivity(), profileImage, 15));


        return view;
    }

    @Override
    protected void setUp(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    /*@OnClick(R.id.nav_back_btn)
    void onNavBackClick() {
        getBaseActivity().onFragmentDetached(TAG);
    }*/

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }


    @Override
    public void populateViews(String name, String location, String profileImage, String coverImage, String bioDescription, List<String> bucketList) {
        LinearLayout[] bucketItems = {bucketItemOne, bucketItemTwo, bucketItemThree, bucketItemFour, bucketItemFive};
        TextView[] bucketItemsTextView = {bucketItemOneText, bucketItemTwoText, bucketItemThreeText, bucketItemFourText, bucketItemFiveText};
        nameTV.setText(name);
        locationTV.setText(location);
        bioDescriptionTV.setText(bioDescription);

        if (bucketList.size() > 0) {
            bucketListItemTitle.setVisibility(View.VISIBLE);
            for (int i = 0; i < bucketList.size(); i++) {
                bucketItems[i].setVisibility(View.VISIBLE);
                bucketItemsTextView[i].setText(bucketList.get(i));
            }

        }
    }

    @Override
    public void onFragmentDetached(String TAG) {
        hideLoading();
        getBaseActivity().onFragmentDetached(TAG);
    }

    @OnClick(R.id.settings)
    void onSettingsClicked() {

        mPresenter.showSettingsOptionsFragment(TAG + "-" + AppConstants.OPEN_SETTINGS_OPTIONS);
    }

}