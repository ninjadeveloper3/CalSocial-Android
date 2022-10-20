package com.CalSocial.ui.profile.editProfile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.CalSocial.R;
import com.CalSocial.di.component.ActivityComponent;
import com.CalSocial.ui.base.BaseFragment;
import com.CalSocial.widget.CalSocialEditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditProfileFragment extends BaseFragment implements EditProfileMvpView {

    public static final String TAG = "EditProfileFragment";

    @BindView(R.id.coverImage)
    ImageView coverImage;

    @BindView(R.id.profileImage)
    ImageView profileImage;

    @BindView(R.id.name)
    CalSocialEditText nameET;

    @BindView(R.id.location)
    CalSocialEditText locationET;

    @BindView(R.id.bio)
    CalSocialEditText bioET;

    @BindView(R.id.bucketOne)
    CalSocialEditText bucketOneET;

    @BindView(R.id.bucketTwo)
    CalSocialEditText bucketTwoET;

    @BindView(R.id.bucketThree)
    CalSocialEditText bucketThree;

    @BindView(R.id.bucketListItemsExtra)
    LinearLayout bucketListItemsExtra;

    @Inject
    EditProfilePresenter<EditProfileMvpView> mPresenter;

    public static EditProfileFragment newInstance() {
        Bundle args = new Bundle();
        EditProfileFragment fragment = new EditProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profiles_fragment_edit_profile, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
        }

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
    public void onFragmentDetached(String TAG) {
        hideLoading();
        getBaseActivity().onFragmentDetached(TAG);
    }

    @OnClick(R.id.addBucketItem)
    void onAddBucketItemClicked() {

    }

    @OnClick(R.id.changeCoverImage)
    void onChangeCoverImage() {

    }

    @OnClick(R.id.changePicture)
    void onChangePictureClick() {

    }
}