package com.CalSocial.ui.events.eventDetail;

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
import com.CalSocial.data.network.model.CommentsResponse;
import com.CalSocial.data.network.model.EventAttendeesResponse;
import com.CalSocial.di.component.ActivityComponent;
import com.CalSocial.ui.base.BaseFragment;
import com.CalSocial.ui.events.adapter.AttendeesAdapter;
import com.CalSocial.ui.events.adapter.CommentsAdapter;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventDetailDetailFragment extends BaseFragment implements EventDetailMvpView {

    public static final String TAG = "EventDetailDetailFragment";

    @Inject
    EventDetailPresenter<EventDetailMvpView> mPresenter;

    @Inject
    LinearLayoutManager mAttendeesLayoutManager;

    @BindView(R.id.attendeesRV)
    RecyclerView mAttendeesRecyclerView;

    @Inject
    AttendeesAdapter mAttendeesAdapter;

    @Inject
    LinearLayoutManager mCommentsLayoutManager;

    @BindView(R.id.commentsRV)
    RecyclerView mCommentsRecyclerView;

    @Inject
    CommentsAdapter mCommentsAdapter;

    @BindView(R.id.commentsTitle)
    TextView commentsTitle;

    @BindView(R.id.bodyExpandableLayout)
    ExpandableLayout bodyExpandableLayout;

    @BindView(R.id.commentsExpandableLayout)
    ExpandableLayout commentsExpandableLayout;

    @BindView(R.id.expandCommentsView)
    ImageView expandCommentsView;

    @BindView(R.id.contractCommentsView)
    ImageView contractCommentsView;

    public static EventDetailDetailFragment newInstance() {
        Bundle args = new Bundle();
        EventDetailDetailFragment fragment = new EventDetailDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.events_fragment_event_detail, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);

            mAttendeesLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mAttendeesRecyclerView.setLayoutManager(mAttendeesLayoutManager);
            mAttendeesRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mAttendeesRecyclerView.setAdapter(mAttendeesAdapter);
            mPresenter.onAttendeesRVViewPrepared();

            mCommentsRecyclerView.setLayoutManager(mCommentsLayoutManager);
            mCommentsRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mCommentsRecyclerView.setAdapter(mCommentsAdapter);
            mPresenter.onCommentsRVViewPrepared();

            //bodyExpandableLayout.expand();
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
    public void updateAttendeesRepo(List<EventAttendeesResponse.User> userList) {
        mAttendeesAdapter.addItems(userList);
    }

    @Override
    public void updateCommentsRepo(List<CommentsResponse.Comment> commentList) {
        mCommentsAdapter.addItems(commentList);
    }

    @OnClick(R.id.commentsTitleRV)
    void onCommentsTitleClicked() {
        if (!commentsExpandableLayout.isExpanded()) {
            bodyExpandableLayout.collapse();
            commentsExpandableLayout.expand();
            expandCommentsView.setVisibility(View.VISIBLE);
            contractCommentsView.setVisibility(View.GONE);
        } else {
            bodyExpandableLayout.expand();
            commentsExpandableLayout.collapse();
            expandCommentsView.setVisibility(View.GONE);
            contractCommentsView.setVisibility(View.VISIBLE);
        }

    }

}