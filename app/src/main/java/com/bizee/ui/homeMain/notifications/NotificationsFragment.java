package com.CalSocial.ui.homeMain.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.CalSocial.R;
import com.CalSocial.data.network.model.NotificationsResponse;
import com.CalSocial.di.component.ActivityComponent;
import com.CalSocial.ui.base.BaseFragment;
import com.CalSocial.ui.homeMain.adapter.NotificationsAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationsFragment extends BaseFragment implements NotificationsMvpView {

    public static final String TAG = "NotificationsFragment";

    @Inject
    LinearLayoutManager mEventsLayoutManager;

    @BindView(R.id.notificationsRV)
    RecyclerView mNotificationsRecyclerView;

    @Inject
    NotificationsAdapter mNotificationsAdapter;

    @Inject
    NotificationsPresenter<NotificationsMvpView> mPresenter;

    public static NotificationsFragment newInstance() {
        Bundle args = new Bundle();
        NotificationsFragment fragment = new NotificationsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notifications_fragment_notifications, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
        }

        mNotificationsRecyclerView.setLayoutManager(mEventsLayoutManager);
        mNotificationsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mNotificationsRecyclerView.setAdapter(mNotificationsAdapter);
        mPresenter.onNotificationsRVViewPrepared();

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
    public void updateNotificationsRepo(List<NotificationsResponse.Notification> notificationList) {
        mNotificationsAdapter.addItems(notificationList);

    }



}