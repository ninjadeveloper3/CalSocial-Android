package com.CalSocial.ui.profile.viewHive;

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
import com.CalSocial.ui.profile.adapter.HiveMembersAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewHiveFragment extends BaseFragment implements ViewHiveMvpView {

    public static final String TAG = "ViewHiveFragment";

    @BindView(R.id.membersRV)
    RecyclerView mMembersRecyclerView;

    @Inject
    HiveMembersAdapter mMembersAdapter;

    @Inject
    LinearLayoutManager mMembersLayoutManager;

    @Inject
    ViewHivePresenter<ViewHiveMvpView> mPresenter;

    public static ViewHiveFragment newInstance() {
        Bundle args = new Bundle();
        ViewHiveFragment fragment = new ViewHiveFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profiles_fragment_view_hive_contact_profile, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);

            mMembersRecyclerView.setLayoutManager(mMembersLayoutManager);
            mMembersRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mMembersRecyclerView.setAdapter(mMembersAdapter);
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

    @Override
    public void onFragmentDetached(String TAG) {
        hideLoading();
        getBaseActivity().onFragmentDetached(TAG);
    }

    @Override
    public void updateSwarmMembersRepo(List<UsersResponse.User> userList) {
        mMembersAdapter.addItems(userList);
    }


}