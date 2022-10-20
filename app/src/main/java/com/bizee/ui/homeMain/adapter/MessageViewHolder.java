package com.CalSocial.ui.homeMain.adapter;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.CalSocial.R;
import com.CalSocial.data.network.model.MessagesResponse;
import com.CalSocial.event.OpenFragmentEvent;
import com.CalSocial.ui.base.BaseViewHolder;
import com.CalSocial.ui.homeMain.messages.MessagesFragment;
import com.CalSocial.ui.homeMain.messagesChats.MessagesChatFragment;
import com.CalSocial.ui.homeMain.messagesChatsOneToMany.MessagesChatOMFragment;
import com.CalSocial.widget.SwipeToRevealHidden;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageViewHolder extends BaseViewHolder implements SwipeToRevealHidden.Callback {

    @BindView(R.id.messageItem)
    RelativeLayout messageItem;

    @BindView(R.id.unreadMessage)
    ImageView unreadMessage;

    @BindView(R.id.profileImage)
    ImageView profileImage;

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.excerpt)
    TextView excerpt;

    @BindView(R.id.timeOfMessage)
    TextView timeOfMessage;

    @BindView(R.id.deleteMessage)
    ImageView deleteMessage;

    @BindView(R.id.swipeToReveal)
    SwipeToRevealHidden swipeToRevealHidden;

    private int position = 0;
    private List<MessagesResponse.Message> mMessagesHomeResponseList;

    public MessageViewHolder(View itemView, List<MessagesResponse.Message> messagesResponseList) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mMessagesHomeResponseList = messagesResponseList;

    }

    @Override
    protected void clear() {

    }


    public void onBind(int position) {
        super.onBind(position);

        if (position < mMessagesHomeResponseList.size()) {

            swipeToRevealHidden.setCallback(this);

            messageItem.setVisibility(View.VISIBLE);
            final MessagesResponse.Message message = mMessagesHomeResponseList.get(position);
            String titleTex = "";
            if (message.getParticipants().length > 2) {
                for (int i = 0; i < message.getParticipants().length; i++) {
                    if (i == message.getParticipants().length - 2) {
                        titleTex = titleTex + message.getParticipants()[i] + " & ";
                    } else if (i == message.getParticipants().length - 1) {
                        titleTex = titleTex + message.getParticipants()[i];
                    } else {
                        titleTex = titleTex + message.getParticipants()[i] + ", ";
                    }

                }
            } else {
                titleTex = titleTex + message.getParticipants()[0];

            }

            title.setText(titleTex);

            timeOfMessage.setText(message.getTime());
            excerpt.setText(message.getExcerpt());

            final int position1 = position;
            this.position = position;
           /* messageItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position1 > 2) {
                    //The one-to-one message is in the locations 3 and 4 of the list
                        EventBus.getDefault().postSticky(new OpenFragmentEvent(MessagesFragment.TAG, MessagesChatFragment.TAG));
                    } else {
                        EventBus.getDefault().postSticky(new OpenFragmentEvent(MessagesFragment.TAG, MessagesChatOMFragment.TAG));
                    }

                }
            });

            deleteMessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("app", "delete clicked");
                }
            });*/
        }

    }


    @Override
    public void onClickEventCalled(MotionEvent e, boolean swipeOpened) {

        final int pos = this.position;

        if (swipeOpened) {
            deleteMessage.post(new Runnable() {
                @Override
                public void run() {
                    //If the delete message is revealed then only make the part of the messageItem not covered by the
                    //delete button to be clickable.
                    if (e.getX() < deleteMessage.getX()) {
                        //The messageItem has been clicked
                        if (pos > 2) {
                            //The one-to-one message is in the locations 3 and 4 of the list
                            EventBus.getDefault().postSticky(new OpenFragmentEvent(MessagesFragment.TAG, MessagesChatFragment.TAG));
                        } else {
                            EventBus.getDefault().postSticky(new OpenFragmentEvent(MessagesFragment.TAG, MessagesChatOMFragment.TAG));
                        }
                    } else {
                        //The delete button has been clicked

                        Log.e("app", "delete the item");
                    }

                }
            });
        } else {
            //The delete button is not showing, so allow the whole of the messageItem to be clickable.
            if (e.getX() < deleteMessage.getX()) {
                if (pos > 2) {
                    //The one-to-one message is in the locations 3 and 4 of the list
                    EventBus.getDefault().postSticky(new OpenFragmentEvent(MessagesFragment.TAG, MessagesChatFragment.TAG));
                } else {
                    EventBus.getDefault().postSticky(new OpenFragmentEvent(MessagesFragment.TAG, MessagesChatOMFragment.TAG));
                }
            }
        }

    }
}
