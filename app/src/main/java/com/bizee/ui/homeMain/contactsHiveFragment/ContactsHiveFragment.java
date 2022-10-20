package com.CalSocial.ui.homeMain.contactsHiveFragment;

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
import com.CalSocial.ui.homeMain.adapter.ContactsHiveAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactsHiveFragment extends BaseFragment implements ContactsHiveMvpView {

    public static final String TAG = "ContactsHiveFragment";

    @Inject
    LinearLayoutManager mEventsLayoutManager;

    @Inject
    ContactsHiveAdapter mContactsHiveAdapter;

    @BindView(R.id.membersRV)
    RecyclerView membersRV;

    @BindView(R.id.hiveEmptyMessage)
    TextView hiveEmptyMessage;

    @Inject
    ContactsHivePresenter<ContactsHiveMvpView> mPresenter;

    public static ContactsHiveFragment newInstance() {
        Bundle args = new Bundle();
        ContactsHiveFragment fragment = new ContactsHiveFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contacts_fragment_hive_contacts, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);

            membersRV.setLayoutManager(mEventsLayoutManager);
            membersRV.setItemAnimator(new DefaultItemAnimator());
            membersRV.setAdapter(mContactsHiveAdapter);
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
    public void updateHiveNotificationsRepo(List<ContactsHiveResponse.User> userList) {

        if (userList.size() > 0) {
            hiveEmptyMessage.setVisibility(View.GONE);
            membersRV.setVisibility(View.VISIBLE);
        }
        mContactsHiveAdapter.addItems(userList);
    }

    public void showEditScreen() {
        mContactsHiveAdapter.setMode(1);
        mContactsHiveAdapter.notifyDataSetChanged();

    }

    public void showViewScreen() {
        mContactsHiveAdapter.setMode(0);
        mContactsHiveAdapter.notifyDataSetChanged();

    }
}

