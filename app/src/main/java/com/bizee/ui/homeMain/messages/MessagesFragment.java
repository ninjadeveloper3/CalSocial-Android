package com.CalSocial.ui.homeMain.messages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.CalSocial.R;
import com.CalSocial.data.network.model.MessagesResponse;
import com.CalSocial.di.component.ActivityComponent;
import com.CalSocial.event.GestureEvent;
import com.CalSocial.ui.base.BaseFragment;
import com.CalSocial.ui.homeMain.adapter.MessagesAdapter;
import com.CalSocial.widget.SwipeToRevealHidden;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessagesFragment extends BaseFragment implements MessagesMvpView {

    public static final String TAG = "MessagesFragment";

    @Inject
    LinearLayoutManager mMessagesLayoutManager;

    @BindView(R.id.messagesRV)
    RecyclerView mMessagesRecyclerView;

    @Inject
    MessagesAdapter mMessagesAdapter;

    @Inject
    MessagesPresenter<MessagesMvpView> mPresenter;

    private SwipeToRevealHidden swipeToRevealHidden;

    public static MessagesFragment newInstance() {
        Bundle args = new Bundle();
        MessagesFragment fragment = new MessagesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.messages_fragment_messages, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
        }

        mMessagesRecyclerView.setLayoutManager(mMessagesLayoutManager);
        mMessagesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mMessagesRecyclerView.setAdapter(mMessagesAdapter);
        mPresenter.onMessagesRVViewPrepared();

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

    }

    @Override
    public void updateMessagesRepo(List<MessagesResponse.Message> messageList) {
        mMessagesAdapter.addItems(messageList);

    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true, priority = 1)
    public void onMessageEvent(GestureEvent event) {
        if (event.gesture.equalsIgnoreCase("onScroll")) {

            //mPresenter.closeCalendarOnScroll(event);

        }
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