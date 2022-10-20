package com.CalSocial.ui.onboarding.contactsFound;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.CalSocial.R;
import com.CalSocial.data.network.model.ContactsFoundResponse;
import com.CalSocial.di.component.ActivityComponent;
import com.CalSocial.ui.base.BaseFragment;
import com.CalSocial.ui.onboarding.contactsFound.adapter.ContactsFoundAdapter;
import com.CalSocial.utils.AppConstants;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ContactsFoundFragment extends BaseFragment implements ContactsFoundMvpView, ContactsFoundAdapter.Callback {

    public static final String TAG = "ContactsFoundFragment";

    @Inject
    ContactsFoundPresenter<ContactsFoundMvpView> mPresenter;

    @Inject
    ContactsFoundAdapter mContactsFoundAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    @BindView(R.id.contactsFoundRV)
    RecyclerView mRecyclerView;


    public static ContactsFoundFragment newInstance() {
        Bundle args = new Bundle();
        ContactsFoundFragment fragment = new ContactsFoundFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.onboaring_fragment_contacts_found, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
            mContactsFoundAdapter.setCallback(this);
        }

        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mContactsFoundAdapter);
        mPresenter.onViewPrepared();

        return view;
    }

    @Override
    protected void setUp(View view) {
    }


    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }


    @OnClick(R.id.skipForNow)
    void onSkipForNowClicked() {
        mPresenter.onSkipForNowClicked(TAG + "-" + AppConstants.CREATE_FIRST_EVENT);
    }

    @Override
    public void onFragmentDetached(String TAG) {
        hideLoading();
        getBaseActivity().onFragmentDetached(TAG);
    }

    @Override
    public void updateRepo(List<ContactsFoundResponse.User> userList) {
        mContactsFoundAdapter.addItems(userList);
    }

    @OnClick(R.id.CalSocialButton)
    void onSyncContactsClicked() {

        //mPresenter.onSyncContactsClicked(TAG + "-" + AppConstants.CONTACTS_FOUND);
    }

    @Override
    public void onRepoEmptyViewRetryClick() {

    }
}