package com.CalSocial.ui.homeMain.contactsCreateSwarmFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.CalSocial.R;
import com.CalSocial.data.network.model.ContactsHiveResponse;
import com.CalSocial.di.component.ActivityComponent;
import com.CalSocial.ui.base.BaseFragment;
import com.CalSocial.ui.homeMain.adapter.ContactsCreateSwarmMembersAdapter;
import com.CalSocial.utils.AppConstants;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactsCreateSwarmFragment extends BaseFragment implements ContactsCreateSwarmMvpView {

    public static final String TAG = "ContactsCreateSwarmFragment";

    @Inject
    LinearLayoutManager mMembersLayoutManager;

    @Inject
    ContactsCreateSwarmMembersAdapter mMembersAdapter;

    @BindView(R.id.membersRV)
    RecyclerView membersRV;

    @Inject
    ContactsCreateSwarmPresenter<ContactsCreateSwarmMvpView> mPresenter;

    public static ContactsCreateSwarmFragment newInstance() {
        Bundle args = new Bundle();
        ContactsCreateSwarmFragment fragment = new ContactsCreateSwarmFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contacts_fragment_create_swarm, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);

            membersRV.setLayoutManager(mMembersLayoutManager);
            membersRV.setItemAnimator(new DefaultItemAnimator());
            membersRV.setAdapter(mMembersAdapter);
            mPresenter.onContactsHiveRVViewPrepared();
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
    public void updateMembersRepo(List<ContactsHiveResponse.User> userList) {

        if (userList.size() > 0) {

            membersRV.setVisibility(View.VISIBLE);
        }
        mMembersAdapter.addItems(userList);
    }

    @OnClick(R.id.CalSocialButton)
    void onAddMemberClicked() {

        mPresenter.onAddMemberClicked(TAG + "-" + AppConstants.OPEN_CREATE_SWARM_SELECT_MEMBERS_FRAGMENT);
    }
}

