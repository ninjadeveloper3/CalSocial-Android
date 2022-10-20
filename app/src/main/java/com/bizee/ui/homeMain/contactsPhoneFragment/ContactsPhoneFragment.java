package com.CalSocial.ui.homeMain.contactsPhoneFragment;

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
import com.CalSocial.data.network.model.ContactsHiveResponse;
import com.CalSocial.di.component.ActivityComponent;
import com.CalSocial.ui.base.BaseFragment;
import com.CalSocial.ui.homeMain.adapter.ContactsPhoneAdapter;
import com.CalSocial.utils.AppConstants;
import com.CalSocial.widget.CalSocialButton;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactsPhoneFragment extends BaseFragment implements ContactsPhoneMvpView {

    public static final String TAG = "ContactsPhoneFragment";

    @Inject
    ContactsPhonePresenter<ContactsPhoneMvpView> mPresenter;

    @Inject
    LinearLayoutManager mEventsLayoutManager;

    @Inject
    ContactsPhoneAdapter mContactsSwarmAdapter;

    @BindView(R.id.membersRV)
    RecyclerView membersRV;

    @BindView(R.id.phoneEmptyMessage)
    TextView phoneEmptyMessage;

    @BindView(R.id.syncContacts)
    CalSocialButton syncContacts;

    public static ContactsPhoneFragment newInstance() {
        Bundle args = new Bundle();
        ContactsPhoneFragment fragment = new ContactsPhoneFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contacts_fragment_phone_contacts, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);

            membersRV.setLayoutManager(mEventsLayoutManager);
            membersRV.setItemAnimator(new DefaultItemAnimator());
            membersRV.setAdapter(mContactsSwarmAdapter);
            mPresenter.onContactsSwarmRVViewPrepared(getActivity());
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
    public void updateSwarmNotificationsRepo(List<ContactsHiveResponse.User> userList) {
        if (userList.size() > 0) {
            phoneEmptyMessage.setVisibility(View.GONE);
            membersRV.setVisibility(View.VISIBLE);
            syncContacts.setVisibility(View.GONE);
        }
        mContactsSwarmAdapter.addItems(userList);
    }

    @OnClick(R.id.CalSocialButton)
    void onCreateSwarmClicked() {

        mPresenter.onCreateSwarmClicked(TAG + "-" + AppConstants.OPEN_CREATE_SWARM_FRAGMENT);
    }


}

