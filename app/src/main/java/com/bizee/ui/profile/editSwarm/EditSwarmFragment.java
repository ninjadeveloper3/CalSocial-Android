package com.CalSocial.ui.profile.editSwarm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.CalSocial.R;
import com.CalSocial.data.network.model.UsersResponse;
import com.CalSocial.di.component.ActivityComponent;
import com.CalSocial.ui.base.BaseFragment;
import com.CalSocial.ui.profile.adapter.EditSwarmMembersAdapter;
import com.CalSocial.utils.AppConstants;
import com.CalSocial.widget.CalSocialEditText;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditSwarmFragment extends BaseFragment implements EditSwarmMvpView {

    public static final String TAG = "EditSwarmFragment";

    @BindView(R.id.swarm)
    CalSocialEditText swarmET;

    @BindView(R.id.aboutUs)
    CalSocialEditText aboutUsET;

    @BindView(R.id.membersRV)
    RecyclerView mMembersRecyclerView;

    @Inject
    EditSwarmPresenter<EditSwarmMvpView> mPresenter;

    @Inject
    EditSwarmMembersAdapter mSwarmMembersAdapter;

    @Inject
    LinearLayoutManager mMembersLayoutManager;

    public static EditSwarmFragment newInstance() {
        Bundle args = new Bundle();
        EditSwarmFragment fragment = new EditSwarmFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profiles_fragment_edit_swarm, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);

            mMembersRecyclerView.setLayoutManager(mMembersLayoutManager);
            mMembersRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mMembersRecyclerView.setAdapter(mSwarmMembersAdapter);
            mPresenter.onMembersRVViewPrepared();

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

    @OnClick(R.id.CalSocialButton)
    void onAddMemberClicked() {
        mPresenter.onAddMemberClicked(TAG + "-" + AppConstants.OPEN_VIEW_HIVE_PROFILE);
    }

    @OnClick(R.id.deleteSwarm)
    void onDeleteSwarmClicked() {

    }

    @Override
    public void onFragmentDetached(String TAG) {
        hideLoading();
        getBaseActivity().onFragmentDetached(TAG);
    }

    @Override
    public void updateSwarmMembersRepo(List<UsersResponse.User> userList) {
        mSwarmMembersAdapter.addItems(userList);
    }
}