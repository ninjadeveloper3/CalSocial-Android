package com.CalSocial.ui.homeMain.contactsSwarmFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.CalSocial.R;
import com.CalSocial.data.network.model.ContactsSwarmResponse;
import com.CalSocial.di.component.ActivityComponent;
import com.CalSocial.ui.base.BaseFragment;
import com.CalSocial.ui.homeMain.adapter.ContactsSwarmAdapter;
import com.CalSocial.utils.AppConstants;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactsSwarmFragment extends BaseFragment implements ContactsSwarmMvpView {

    public static final String TAG = "ContactsSwarmFragment";

    @Inject
    ContactsSwarmPresenter<ContactsSwarmMvpView> mPresenter;

    @Inject
    LinearLayoutManager mEventsLayoutManager;

    @Inject
    ContactsSwarmAdapter mContactsSwarmAdapter;

    @BindView(R.id.membersRV)
    RecyclerView membersRV;

    @BindView(R.id.swarmEmptyMessage)
    TextView swarmEmptyMessage;

    public static ContactsSwarmFragment newInstance() {
        Bundle args = new Bundle();
        ContactsSwarmFragment fragment = new ContactsSwarmFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contacts_fragment_swarm_contacts, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);

            membersRV.setLayoutManager(mEventsLayoutManager);
            membersRV.setItemAnimator(new DefaultItemAnimator());
            membersRV.setAdapter(mContactsSwarmAdapter);
            mPresenter.onContactsSwarmRVViewPrepared();
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
    public void updateSwarmNotificationsRepo(List<ContactsSwarmResponse.Swarm> userList) {
        if (userList.size() > 0) {
            swarmEmptyMessage.setVisibility(View.GONE);
            membersRV.setVisibility(View.VISIBLE);
        }
        mContactsSwarmAdapter.addItems(userList);
    }

    @OnClick(R.id.CalSocialButton)
    void onCreateSwarmClicked() {

        mPresenter.onCreateSwarmClicked(TAG + "-" + AppConstants.OPEN_CREATE_SWARM_FRAGMENT);
    }

    public void showEditScreen() {

        mContactsSwarmAdapter.setMode(1);
        mContactsSwarmAdapter.notifyDataSetChanged();

    }

    public void showViewScreen() {
        mContactsSwarmAdapter.setMode(0);
        mContactsSwarmAdapter.notifyDataSetChanged();

    }

}

