package com.CalSocial.ui.homeMain.messagesChatsOneToMany;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.CalSocial.R;
import com.CalSocial.data.network.model.MessagesResponse;
import com.CalSocial.di.component.ActivityComponent;
import com.CalSocial.ui.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class MessagesChatOMFragment extends BaseFragment implements MessagesChatOMMvpView {

    public static final String TAG = "MessagesChatOMFragment";

      /* @Inject
    LinearLayoutManager mMessagesLayoutManager;

    @BindView(R.id.messagesRV)
    RecyclerView mMessagesRecyclerView;

    @Inject
    MessagesAdapter mMessagesAdapter;*/

    @Inject
    MessagesChatOMPresenter<MessagesChatOMMvpView> mPresenter;

    public static MessagesChatOMFragment newInstance() {
        Bundle args = new Bundle();
        MessagesChatOMFragment fragment = new MessagesChatOMFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.messages_item_chat_one_to_many_layout, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
        }

        /*mMessagesRecyclerView.setLayoutManager(mMessagesLayoutManager);
        mMessagesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mMessagesRecyclerView.setAdapter(mMessagesAdapter);
        mPresenter.onMessagesRVViewPrepared();*/

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
        //mMessagesAdapter.addItems(messageList);

    }
}