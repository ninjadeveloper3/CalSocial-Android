package com.CalSocial.ui.homeMain.contactsCreateMessagesSelectMembersFragment;

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
import com.CalSocial.data.network.model.NewMessageSelectContactsResponse;
import com.CalSocial.di.component.ActivityComponent;
import com.CalSocial.ui.base.BaseFragment;
import com.CalSocial.ui.homeMain.adapter.ContactsCreateNewMessageSelectMembersAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactsCreateMessagesSelectMembersFragment extends BaseFragment implements ContactsCreateMessagesSelectMembersMvpView {

    public static final String TAG = "ContactsCreateMessagesSelectMembersFragment";

    @Inject
    LinearLayoutManager mMembersLayoutManager;

    @Inject
    ContactsCreateNewMessageSelectMembersAdapter mMembersAdapter;

    @BindView(R.id.membersRV)
    RecyclerView membersRV;

    @Inject
    ContactsCreateMessagesSelectMembersPresenter<ContactsCreateMessagesSelectMembersMvpView> mPresenter;

    public static ContactsCreateMessagesSelectMembersFragment newInstance() {
        Bundle args = new Bundle();
        ContactsCreateMessagesSelectMembersFragment fragment = new ContactsCreateMessagesSelectMembersFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contacts_fragment_swarm_select_members, container, false);

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
    public void updateMembersRepo(List<NewMessageSelectContactsResponse.User> userList) {

        if (userList.size() > 0) {

            membersRV.setVisibility(View.VISIBLE);
        }
        mMembersAdapter.addItems(userList);
    }

}

