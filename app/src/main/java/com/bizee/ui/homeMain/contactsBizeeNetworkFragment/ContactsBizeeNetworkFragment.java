package com.CalSocial.ui.homeMain.contactsCalSocialNetworkFragment;

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
import com.CalSocial.ui.homeMain.adapter.ContactsCalSocialNetworkAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactsCalSocialNetworkFragment extends BaseFragment implements ContactsCalSocialNetworkMvpView {

    public static final String TAG = "ContactsCalSocialNetworkFragment";

    @Inject
    ContactsCalSocialNetworkPresenter<ContactsCalSocialNetworkMvpView> mPresenter;

    @Inject
    LinearLayoutManager mEventsLayoutManager;

    @Inject
    ContactsCalSocialNetworkAdapter mContactsSwarmAdapter;

    @BindView(R.id.membersRV)
    RecyclerView membersRV;

    public static ContactsCalSocialNetworkFragment newInstance() {
        Bundle args = new Bundle();
        ContactsCalSocialNetworkFragment fragment = new ContactsCalSocialNetworkFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contacts_fragment_CalSocial_network, container, false);

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
    public void updateSwarmNotificationsRepo(List<ContactsHiveResponse.User> userList) {

        mContactsSwarmAdapter.addItems(userList);
    }


}

