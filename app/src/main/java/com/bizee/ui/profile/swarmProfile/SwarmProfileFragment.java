package com.CalSocial.ui.profile.swarmProfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.CalSocial.R;
import com.CalSocial.data.network.model.EventsHomeResponse;
import com.CalSocial.data.network.model.UsersResponse;
import com.CalSocial.di.component.ActivityComponent;
import com.CalSocial.event.OpenContactProfileEvent;
import com.CalSocial.ui.base.BaseFragment;
import com.CalSocial.ui.events.EventsActivity;
import com.CalSocial.ui.homeMain.HomeMainActivity;
import com.CalSocial.ui.profile.adapter.ProfileEventsAdapter;
import com.CalSocial.ui.profile.adapter.SwarmMembersAdapter;
import com.CalSocial.utils.AppConstants;
import com.CalSocial.utils.ViewUtils;
import com.bumptech.glide.Glide;

import net.cachapa.expandablelayout.ExpandableLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SwarmProfileFragment extends BaseFragment implements SwarmProfileMvpView {

    public static final String TAG = "SwarmProfileFragment";

    @BindView(R.id.coverImage)
    ImageView coverImage;

    @BindView(R.id.image1)
    ImageView image1;

    @BindView(R.id.image2)
    ImageView image2;

    @BindView(R.id.image3)
    ImageView image3;

    @BindView(R.id.image4)
    ImageView image4;

    @BindView(R.id.name)
    TextView nameTV;

    @BindView(R.id.favorite)
    ImageView favorite;

    @BindView(R.id.aboutUsDescription)
    TextView aboutUsDescriptionTV;

    @BindView(R.id.eventsScheduledListTitle)
    TextView eventsScheduled;

    @Inject
    LinearLayoutManager mEventsLayoutManager;

    @BindView(R.id.eventsRV)
    RecyclerView mEventsRecyclerView;


    @BindView(R.id.expandEventsView)
    ImageView expandEventsView;

    @BindView(R.id.contractEventsView)
    ImageView contractEventsView;

    @BindView(R.id.eventsScheduledListExpandableLayout)
    ExpandableLayout eventsScheduledListExpandableLayout;

    @BindView(R.id.membersRV)
    RecyclerView mMembersRecyclerView;

    @Inject
    ProfileEventsAdapter mProfileEventsAdapter;

    @Inject
    SwarmMembersAdapter mSwarmMembersAdapter;

    @Inject
    LinearLayoutManager mMembersLayoutManager;

    @Inject
    SwarmProfilePresenter<SwarmProfileMvpView> mPresenter;

    @BindView(R.id.expandMembersView)
    ImageView expandMembersView;

    @BindView(R.id.contractMembersView)
    ImageView contractMembersView;

    @BindView(R.id.membersListExpandableLayout)
    ExpandableLayout membersListExpandableLayout;

    @BindView(R.id.membersListTitle)
    TextView membersListTitle;

    public static SwarmProfileFragment newInstance() {
        Bundle args = new Bundle();
        SwarmProfileFragment fragment = new SwarmProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profiles_fragment_swarm_profile, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);

            mPresenter.getSwarmDetails();

            mEventsRecyclerView.setLayoutManager(mEventsLayoutManager);
            mEventsRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mEventsRecyclerView.setAdapter(mProfileEventsAdapter);
            mPresenter.onEventsRVViewPrepared();
            eventsScheduledListExpandableLayout.expand();

            mMembersRecyclerView.setLayoutManager(mMembersLayoutManager);
            mMembersRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mMembersRecyclerView.setAdapter(mSwarmMembersAdapter);
            mPresenter.onMembersRVViewPrepared();
            membersListExpandableLayout.expand();

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
    public void populateViews(String name, List<String> profileImages, String coverImage, Boolean isFavorite, String aboutUsDescription) {
        nameTV.setText(name);

        if (isFavorite) {
            favorite.setVisibility(View.VISIBLE);
        } else {
            favorite.setVisibility(View.GONE);
        }

        aboutUsDescriptionTV.setText(aboutUsDescription);
    }

    @Override
    public void onFragmentDetached(String TAG) {
        hideLoading();
        getBaseActivity().onFragmentDetached(TAG);
    }

    @Override
    public void updateProfileEventsRepo(List<EventsHomeResponse.Event> eventList) {
        eventsScheduled.setText(eventList.size() + " Events Scheduled");
        mProfileEventsAdapter.addItems(eventList);
    }

    @Override
    public void updateSwarmMembersRepo(List<UsersResponse.User> userList) {
        membersListTitle.setText(userList.size() + " Members");
        mSwarmMembersAdapter.addItems(userList);
    }

    @Override
    public void openCreateMessage() {
        Intent intent = new Intent(getActivity(), HomeMainActivity.class);
        intent.putExtra("screenToOpen", AppConstants.MESSAGES_ONE_TO_MANY_FRAGMENT);
        getActivity().startActivity(intent);

    }

    @Override
    public void openNewEvent() {
        Intent intent = new Intent(getActivity(), EventsActivity.class);
        intent.putExtra("screenToOpen", AppConstants.CREATE_EVENT_FRAGMENT);
        startActivity(intent);
    }

    @OnClick(R.id.message)
    void onMessageClicked() {
        mPresenter.onMessagePressed();
    }

    @OnClick(R.id.newEvent)
    void onNewEventClicked() {
        mPresenter.onAddNewEventClicked();

    }

    @OnClick(R.id.eventsScheduledListTitleRV)
    void onEventsScheduledClicked() {
        if (eventsScheduledListExpandableLayout.isExpanded()) {
            eventsScheduledListExpandableLayout.collapse();
            contractEventsView.setVisibility(View.GONE);
            expandEventsView.setVisibility(View.VISIBLE);

        } else {
            eventsScheduledListExpandableLayout.expand();
            contractEventsView.setVisibility(View.VISIBLE);
            expandEventsView.setVisibility(View.GONE);
        }

    }

    @OnClick(R.id.expandEventsView)
    void onExpandEventsViewClicked() {
        eventsScheduledListExpandableLayout.collapse();
        contractEventsView.setVisibility(View.VISIBLE);
        expandEventsView.setVisibility(View.GONE);
    }

    @OnClick(R.id.contractEventsView)
    void onContractEventsViewClicked() {
        eventsScheduledListExpandableLayout.expand();
        contractEventsView.setVisibility(View.GONE);
        expandEventsView.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.membersListTitleRV)
    void onMembersClicked() {
        if (membersListExpandableLayout.isExpanded()) {
            membersListExpandableLayout.collapse();
            contractMembersView.setVisibility(View.GONE);
            expandMembersView.setVisibility(View.VISIBLE);

        } else {
            membersListExpandableLayout.expand();
            contractMembersView.setVisibility(View.VISIBLE);
            expandMembersView.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.expandMembersView)
    void onExpandMembersViewClicked() {
        membersListExpandableLayout.collapse();
        contractMembersView.setVisibility(View.VISIBLE);
        expandMembersView.setVisibility(View.GONE);
    }

    @OnClick(R.id.contractMembersView)
    void onContractMembersViewClicked() {
        membersListExpandableLayout.expand();
        contractMembersView.setVisibility(View.GONE);
        expandMembersView.setVisibility(View.VISIBLE);
    }


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true, priority = 1)
    public void onMessageEvent(OpenContactProfileEvent event) {

        Glide.with(getActivity())
                .load(event.profileImage)
                .asBitmap()
                .centerCrop()
                .into(ViewUtils.getRoundedImageTarget(getActivity(), image1, 15));

        Glide.with(getActivity())
                .load(event.profileImage)
                .asBitmap()
                .centerCrop()
                .into(ViewUtils.getRoundedImageTarget(getActivity(), image2, 15));

        Glide.with(getActivity())
                .load(event.profileImage)
                .asBitmap()
                .centerCrop()
                .into(ViewUtils.getRoundedImageTarget(getActivity(), image3, 15));

        Glide.with(getActivity())
                .load(event.profileImage)
                .asBitmap()
                .centerCrop()
                .into(ViewUtils.getRoundedImageTarget(getActivity(), image4, 15));
        nameTV.setText(event.name);
        EventBus.getDefault().removeStickyEvent(event);

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


}