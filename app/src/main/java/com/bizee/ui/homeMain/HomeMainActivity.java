package com.CalSocial.ui.homeMain;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.CalSocial.R;
import com.CalSocial.event.GestureEvent;
import com.CalSocial.event.OpenFragmentEvent;
import com.CalSocial.ui.base.BaseActivity;
import com.CalSocial.ui.events.EventsActivity;
import com.CalSocial.ui.homeMain.contacts.ContactsFragment;
import com.CalSocial.ui.homeMain.contactsCreateMessagesSelectMembersFragment.ContactsCreateMessagesSelectMembersFragment;
import com.CalSocial.ui.homeMain.contactsCreateSwarmFragment.ContactsCreateSwarmFragment;
import com.CalSocial.ui.homeMain.contactsCreateSwarmSelectMembersFragment.ContactsCreateSwarmSelectMembersFragment;
import com.CalSocial.ui.homeMain.home.HomeFragment;
import com.CalSocial.ui.homeMain.messages.MessagesFragment;
import com.CalSocial.ui.homeMain.messagesChats.MessagesChatFragment;
import com.CalSocial.ui.homeMain.messagesChatsOneToMany.MessagesChatOMFragment;
import com.CalSocial.ui.homeMain.messagesDetails.MessagesDetailsFragment;
import com.CalSocial.ui.homeMain.notifications.NotificationsFragment;
import com.CalSocial.ui.profile.ProfileActivity;
import com.CalSocial.utils.AppConstants;
import com.CalSocial.utils.ViewUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class HomeMainActivity extends BaseActivity implements HomeMainMvpView, GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {

    @Inject
    HomeMainPresenter<HomeMainMvpView> mPresenter;

    @BindView(R.id.fragmentRoot)
    LinearLayout rootLL;

    @BindView(R.id.userProfileImage)
    ImageView userProfileImage;

    @BindView(R.id.homeTitle)
    ImageView homeTitle;

    @BindView(R.id.homeSearch)
    ImageView homeSearch;

    @BindView(R.id.homeTitleText)
    TextView homeTitleText;

    @BindView(R.id.goBack)
    ImageView goBack;

    @BindView(R.id.cancelContactChanges)
    TextView cancelContactChanges;

    @BindView(R.id.edit)
    TextView edit;

    @BindView(R.id.done)
    TextView done;

    @BindView(R.id.addContact)
    ImageView addContact;

    @BindView(R.id.createSwarm)
    TextView createSwarm;

    @BindView(R.id.createMessage)
    TextView createMessage;

    @BindView(R.id.newMessage)
    ImageView newMessage;

    @BindView(R.id.addSwarmContacts)
    TextView addSwarmContacts;

    @BindView(R.id.cancelNewSwarm)
    TextView cancelNewSwarm;

    @BindView(R.id.goBackAddContact)
    ImageView goBackAddContact;

    @BindView(R.id.goBackNewMessage)
    ImageView goBackNewMessage;

    @BindView(R.id.moreSettings)
    ImageView moreSettings;

    @BindView(R.id.goBackMessageDetails)
    ImageView goBackMessageDetails;

    @BindView(R.id.homeText)
    TextView homeText;

    @BindView(R.id.contactsText)
    TextView contactsText;

    @BindView(R.id.messagesText)
    TextView messagesText;

    @BindView(R.id.notificationsText)
    TextView notificationsText;

    private Calendar calendar;
    private static Boolean isCalendarExpanded = false;
    private GestureDetectorCompat mDetector;
    private SimpleDateFormat dateFormat;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, HomeMainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity_home);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(HomeMainActivity.this);
        mDetector = new GestureDetectorCompat(getApplicationContext(), this);
        mDetector.setOnDoubleTapListener(this);
        setUp();


        homeText.setTypeface(Typeface.createFromAsset(getAssets(),
                "font/hk_grotesk_regular.ttf"));

        contactsText.setTypeface(Typeface.createFromAsset(getAssets(),
                "font/hk_grotesk_regular.ttf"));

        messagesText.setTypeface(Typeface.createFromAsset(getAssets(),
                "font/hk_grotesk_regular.ttf"));

        notificationsText.setTypeface(Typeface.createFromAsset(getAssets(),
                "font/hk_grotesk_regular.ttf"));

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null && bundle.getString("screenToOpen") != null) {
            mPresenter.onScreenCreated(bundle.getString("screenToOpen"));
        } else {
            //open the home fragment
            mPresenter.onScreenCreated(AppConstants.HOME_FRAGMENT);
        }

        Glide.with(this)
                .load("https://images.unsplash.com/photo-1539605480396-a61f99da1041?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=150&q=80")
                .asBitmap()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ViewUtils.getRoundedImageTarget(this, userProfileImage, 15));


        //ViewUtils.getRoundedImageTarget(this, userProfileImage, 15);
    }


    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected void setUp() {
    }

    @Override
    public void onFragmentAttached() {
    }

    @Override
    public void onFragmentDetached(String tag) {

        if (tag.equalsIgnoreCase("")) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            List<Fragment> fragments = fragmentManager.getFragments();
            for (int i = 0; i < fragments.size(); i++) {
                if (fragmentManager.findFragmentByTag(fragments.get(i).getTag()) != null) {
                    fragmentManager.beginTransaction()
                            .disallowAddToBackStack()
                            .remove(fragments.get(i))
                            .commitNow();
                }
            }

        } else {

            String option = tag.split("-")[1];
            tag = tag.split("-")[0];
            FragmentManager fragmentManager = getSupportFragmentManager();
            Fragment fragment = fragmentManager.findFragmentByTag(tag);
            if (fragment != null) {
                fragmentManager
                        .beginTransaction()
                        .disallowAddToBackStack()
                        .remove(fragment)
                        .commitNow();
            }
            if (option.compareToIgnoreCase(AppConstants.OPEN_HOME_FRAGMENT) == 0) {
                mPresenter.showHomeFragment();
            } else if (option.compareToIgnoreCase(AppConstants.OPEN_NOTIFICATIONS_FRAGMENT) == 0) {
                mPresenter.showNotificationsFragment();
            } else if (option.compareToIgnoreCase(AppConstants.OPEN_CREATE_SWARM_FRAGMENT) == 0) {
                mPresenter.showContactsCreateSwarmFragment();
            } else if (option.compareToIgnoreCase(AppConstants.OPEN_CREATE_SWARM_SELECT_MEMBERS_FRAGMENT) == 0) {
                mPresenter.showContactsCreateSwarmSelectMembersFragment();
            }


        }

    }

    @Override
    public void showHomeFragment() {
        //if ((getCurrentFragment() == null) || (getCurrentFragment() != null && !getCurrentFragment().equalsIgnoreCase(ContactsCalSocialNetworkFragment.TAG))) {
        onFragmentDetached("");
        goBack.setVisibility(View.GONE);
        userProfileImage.setVisibility(View.VISIBLE);
        cancelContactChanges.setVisibility(View.GONE);
        edit.setVisibility(View.GONE);
        homeTitle.setVisibility(View.VISIBLE);
        homeTitleText.setText("");
        homeTitleText.setVisibility(View.GONE);
        homeSearch.setVisibility(View.VISIBLE);
        done.setVisibility(View.GONE);
        createSwarm.setVisibility(View.GONE);
        createMessage.setVisibility(View.GONE);
        addContact.setVisibility(View.GONE);
        newMessage.setVisibility(View.GONE);
        addSwarmContacts.setVisibility(View.GONE);
        cancelNewSwarm.setVisibility(View.GONE);
        goBackAddContact.setVisibility(View.GONE);
        goBackNewMessage.setVisibility(View.GONE);
        moreSettings.setVisibility(View.GONE);

        Glide.with(this)
                .load("https://images.unsplash.com/photo-1539605480396-a61f99da1041?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=150&q=80")
                .asBitmap()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ViewUtils.getRoundedImageTarget(this, userProfileImage, 15));

        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .add(R.id.fragmentRoot, HomeFragment.newInstance(), HomeFragment.TAG)
                .commit();
        //}


    }


    @Override
    public void showNotificationsFragment() {
        //if (getCurrentFragment() != null && !getCurrentFragment().equalsIgnoreCase(NotificationsFragment.TAG)) {

        onFragmentDetached("");

        goBack.setVisibility(View.GONE);
        userProfileImage.setVisibility(View.GONE);
        cancelContactChanges.setVisibility(View.GONE);
        edit.setVisibility(View.GONE);
        homeTitle.setVisibility(View.GONE);
        homeTitleText.setText("Notifications");
        homeTitleText.setVisibility(View.VISIBLE);
        homeSearch.setVisibility(View.GONE);
        done.setVisibility(View.GONE);
        createSwarm.setVisibility(View.GONE);
        createMessage.setVisibility(View.GONE);
        addContact.setVisibility(View.GONE);
        newMessage.setVisibility(View.GONE);
        addSwarmContacts.setVisibility(View.GONE);
        cancelNewSwarm.setVisibility(View.GONE);
        goBackAddContact.setVisibility(View.GONE);
        goBackNewMessage.setVisibility(View.GONE);
        moreSettings.setVisibility(View.GONE);

        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .add(R.id.fragmentRoot, NotificationsFragment.newInstance(), NotificationsFragment.TAG)
                .commit();
        //}
    }

    @Override
    public void showContactsFragment() {
        //if (getCurrentFragment() != null && !getCurrentFragment().equalsIgnoreCase(ContactsFragment.TAG)) {
        onFragmentDetached("");
        goBack.setVisibility(View.GONE);
        userProfileImage.setVisibility(View.GONE);
        cancelContactChanges.setVisibility(View.GONE);
        edit.setVisibility(View.VISIBLE);
        homeTitle.setVisibility(View.GONE);
        homeTitleText.setText("Contacts");
        homeTitleText.setVisibility(View.VISIBLE);
        homeSearch.setVisibility(View.GONE);
        done.setVisibility(View.GONE);
        createSwarm.setVisibility(View.GONE);
        createMessage.setVisibility(View.GONE);
        addContact.setVisibility(View.VISIBLE);
        newMessage.setVisibility(View.GONE);
        addSwarmContacts.setVisibility(View.GONE);
        cancelNewSwarm.setVisibility(View.GONE);
        goBackAddContact.setVisibility(View.GONE);
        goBackNewMessage.setVisibility(View.GONE);
        moreSettings.setVisibility(View.GONE);

        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .add(R.id.fragmentRoot, ContactsFragment.newInstance(), ContactsFragment.TAG)
                .commit();
        //}
    }

    @Override
    public void showMessagesFragment() {
        //if (getCurrentFragment() != null && !getCurrentFragment().equalsIgnoreCase(MessagesDetailsFragment.TAG)) {
        onFragmentDetached("");
        goBack.setVisibility(View.GONE);
        userProfileImage.setVisibility(View.GONE);
        cancelContactChanges.setVisibility(View.GONE);
        edit.setVisibility(View.GONE);
        homeTitle.setVisibility(View.GONE);
        homeTitleText.setText("Messages");
        homeTitleText.setVisibility(View.VISIBLE);
        homeSearch.setVisibility(View.GONE);
        done.setVisibility(View.GONE);
        createSwarm.setVisibility(View.GONE);
        createMessage.setVisibility(View.GONE);
        addContact.setVisibility(View.GONE);
        newMessage.setVisibility(View.VISIBLE);
        addSwarmContacts.setVisibility(View.GONE);
        cancelNewSwarm.setVisibility(View.GONE);
        goBackAddContact.setVisibility(View.GONE);
        goBackNewMessage.setVisibility(View.GONE);
        moreSettings.setVisibility(View.GONE);

        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .add(R.id.fragmentRoot, MessagesFragment.newInstance(), MessagesFragment.TAG)
                .commit();
        //}
    }

    @Override
    public void showContactsCreateSwarmFragment() {

        onFragmentDetached("");
        goBack.setVisibility(View.GONE);
        userProfileImage.setVisibility(View.GONE);
        cancelContactChanges.setVisibility(View.GONE);
        edit.setVisibility(View.GONE);
        homeTitle.setVisibility(View.GONE);
        homeTitleText.setText("New Swarm");
        homeTitleText.setVisibility(View.VISIBLE);
        homeSearch.setVisibility(View.GONE);
        done.setVisibility(View.GONE);
        createSwarm.setVisibility(View.VISIBLE);
        createMessage.setVisibility(View.GONE);
        addContact.setVisibility(View.GONE);
        newMessage.setVisibility(View.GONE);
        addSwarmContacts.setVisibility(View.GONE);
        cancelNewSwarm.setVisibility(View.VISIBLE);
        goBackAddContact.setVisibility(View.GONE);
        goBackNewMessage.setVisibility(View.GONE);
        moreSettings.setVisibility(View.GONE);

        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .add(R.id.fragmentRoot, ContactsCreateSwarmFragment.newInstance(), ContactsCreateSwarmFragment.TAG)
                .commit();
    }

    @Override
    public void showContactsCreateSwarmSelectMembersFragment() {
        onFragmentDetached("");
        goBack.setVisibility(View.VISIBLE);
        userProfileImage.setVisibility(View.GONE);
        cancelContactChanges.setVisibility(View.GONE);
        edit.setVisibility(View.GONE);
        homeTitle.setVisibility(View.GONE);
        homeTitleText.setText("New Swarm");
        homeTitleText.setVisibility(View.VISIBLE);
        homeSearch.setVisibility(View.GONE);
        done.setVisibility(View.GONE);
        createSwarm.setVisibility(View.GONE);
        createMessage.setVisibility(View.GONE);
        addContact.setVisibility(View.GONE);
        newMessage.setVisibility(View.GONE);
        addSwarmContacts.setVisibility(View.VISIBLE);
        cancelNewSwarm.setVisibility(View.GONE);
        goBackAddContact.setVisibility(View.GONE);
        goBackNewMessage.setVisibility(View.GONE);
        moreSettings.setVisibility(View.GONE);

        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .add(R.id.fragmentRoot, ContactsCreateSwarmSelectMembersFragment.newInstance(), ContactsCreateSwarmSelectMembersFragment.TAG)
                .commit();
    }

    @Override
    public void openNewEvent() {
        Intent intent = new Intent(getApplicationContext(), EventsActivity.class);
        intent.putExtra("screenToOpen", AppConstants.CREATE_EVENT_FRAGMENT);
        startActivity(intent);
    }

    @Override
    public void showMessagesAddContactsFragment() {
        onFragmentDetached("");
        goBack.setVisibility(View.GONE);
        userProfileImage.setVisibility(View.GONE);
        cancelContactChanges.setVisibility(View.GONE);
        edit.setVisibility(View.GONE);
        homeTitle.setVisibility(View.GONE);
        homeTitleText.setText("New Message");
        homeTitleText.setVisibility(View.VISIBLE);
        homeSearch.setVisibility(View.GONE);
        done.setVisibility(View.GONE);
        createSwarm.setVisibility(View.GONE);
        createMessage.setVisibility(View.VISIBLE);
        addContact.setVisibility(View.GONE);
        newMessage.setVisibility(View.GONE);
        addSwarmContacts.setVisibility(View.GONE);
        cancelNewSwarm.setVisibility(View.GONE);
        goBackAddContact.setVisibility(View.GONE);
        goBackNewMessage.setVisibility(View.VISIBLE);
        moreSettings.setVisibility(View.GONE);

        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .add(R.id.fragmentRoot, ContactsCreateMessagesSelectMembersFragment.newInstance(), ContactsCreateMessagesSelectMembersFragment.TAG)
                .commit();
    }

    @Override
    public void showMessageChatFragment() {
        onFragmentDetached("");
        goBack.setVisibility(View.GONE);
        userProfileImage.setVisibility(View.GONE);
        cancelContactChanges.setVisibility(View.GONE);
        edit.setVisibility(View.GONE);
        homeTitle.setVisibility(View.GONE);
        homeTitleText.setText("Joanna");
        homeTitleText.setVisibility(View.VISIBLE);
        homeSearch.setVisibility(View.GONE);
        done.setVisibility(View.GONE);
        createSwarm.setVisibility(View.GONE);
        createMessage.setVisibility(View.GONE);
        addContact.setVisibility(View.GONE);
        newMessage.setVisibility(View.GONE);
        addSwarmContacts.setVisibility(View.GONE);
        cancelNewSwarm.setVisibility(View.GONE);
        goBackAddContact.setVisibility(View.GONE);
        goBackNewMessage.setVisibility(View.VISIBLE);
        moreSettings.setVisibility(View.GONE);

        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .add(R.id.fragmentRoot, MessagesChatFragment.newInstance(), MessagesChatFragment.TAG)
                .commit();
    }

    @Override
    public void showMessageChatOneToManyFragment() {
        onFragmentDetached("");
        goBack.setVisibility(View.GONE);
        userProfileImage.setVisibility(View.GONE);
        cancelContactChanges.setVisibility(View.GONE);
        edit.setVisibility(View.GONE);
        homeTitle.setVisibility(View.GONE);
        homeTitleText.setText("Joanna, Monica, Dave  &...");
        homeTitleText.setVisibility(View.VISIBLE);
        homeSearch.setVisibility(View.GONE);
        done.setVisibility(View.GONE);
        createSwarm.setVisibility(View.GONE);
        createMessage.setVisibility(View.GONE);
        addContact.setVisibility(View.GONE);
        newMessage.setVisibility(View.GONE);
        addSwarmContacts.setVisibility(View.GONE);
        cancelNewSwarm.setVisibility(View.GONE);
        goBackAddContact.setVisibility(View.GONE);
        goBackNewMessage.setVisibility(View.VISIBLE);
        moreSettings.setVisibility(View.VISIBLE);

        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .add(R.id.fragmentRoot, MessagesChatOMFragment.newInstance(), MessagesChatOMFragment.TAG)
                .commit();
    }

    @Override
    public void showMessageDetailsFragment() {
        onFragmentDetached("");
        goBack.setVisibility(View.GONE);
        userProfileImage.setVisibility(View.GONE);
        cancelContactChanges.setVisibility(View.GONE);
        edit.setVisibility(View.GONE);
        homeTitle.setVisibility(View.GONE);
        homeTitleText.setText("Details");
        homeTitleText.setVisibility(View.VISIBLE);
        homeSearch.setVisibility(View.GONE);
        done.setVisibility(View.GONE);
        createSwarm.setVisibility(View.GONE);
        createMessage.setVisibility(View.GONE);
        addContact.setVisibility(View.GONE);
        newMessage.setVisibility(View.GONE);
        addSwarmContacts.setVisibility(View.GONE);
        cancelNewSwarm.setVisibility(View.GONE);
        goBackAddContact.setVisibility(View.GONE);
        goBackNewMessage.setVisibility(View.VISIBLE);
        moreSettings.setVisibility(View.GONE);

        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .add(R.id.fragmentRoot, MessagesDetailsFragment.newInstance(), MessagesDetailsFragment.TAG)
                .commit();
    }

    @Override
    public void showContactProfile() {
        //TODO: give actual values
        onFragmentDetached("");
        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        intent.putExtra("screenToOpen", AppConstants.CONTACT_PROFILE_FRAGMENT);
        intent.putExtra("userId", 1);
        intent.putExtra("name", "John Smith");
        intent.putExtra("profileImage", "");
        startActivity(intent);
        finish();
    }

    @Override
    public void showSwarmProfile() {
        //TODO: give actual values
        onFragmentDetached("");
        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        intent.putExtra("screenToOpen", AppConstants.SWARM_PROFILE_FRAGMENT);
        intent.putExtra("userId", 2);
        intent.putExtra("name", "John Smith");
        intent.putExtra("profileImage", "");
        startActivity(intent);
        finish();
    }

    @Override
    public void goBackToContactProfile() {
        //The profile screen is a separate activity, so when i am going back from the
        //messages screen i have to finish the home activity which houses the messages screen/fragment
        onFragmentDetached("");
        finish();
    }

    @Override
    public void goBackToSwarmProfile() {
        //The swarm screen is a separate activity, so when i am going back from the
        //messages screen i have to finish the home activity which houses the messages screen/fragment
        onFragmentDetached("");
        finish();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        //I need to override the dispatchTouchEvent method so that
        //the scrollup gesture can work on the viewpager as well, which
        //the viewpager otherwise rejects if this method is not overridden.

        if (this.mDetector.onTouchEvent(event)) {
            return true;
        }
        return super.dispatchTouchEvent(event);
    }


    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        EventBus.getDefault().postSticky(new GestureEvent("onScroll", e1, e2, distanceX, distanceY));

        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    private String getCurrentFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments.size() > 0) {
            return fragments.get(0).getTag();
        }

        return null;

    }


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true, priority = 1)
    public void onMessageEvent(OpenFragmentEvent event) {
        if (event.targetFragment.equalsIgnoreCase(MessagesChatFragment.TAG)) {
            onFragmentDetached("");
            mPresenter.showMessageChatFragment();
        } else if (event.targetFragment.equalsIgnoreCase(MessagesChatOMFragment.TAG)) {
            onFragmentDetached("");
            mPresenter.showMessageChatOMFragment();
        }

        EventBus.getDefault().removeStickyEvent(event);

    }

    @Override
    protected void onResume() {
        super.onResume();

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

    @Override
    public void showYourProfile() {
        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        intent.putExtra("screenToOpen", AppConstants.YOUR_PROFILE_FRAGMENT);
        intent.putExtra("userId", 1);
        intent.putExtra("name", "1");
        intent.putExtra("profileImage", "");
        startActivity(intent);
    }

    @OnClick(R.id.home)
    void onHomeClicked() {
        mPresenter.showHomeFragment();
    }


    @OnClick(R.id.contacts)
    void onContactsClicked() {
        mPresenter.showContactsFragment();
    }

    @OnClick(R.id.messages)
    void onMessagesClicked() {
        mPresenter.showMessagesFragment();
    }

    @OnClick(R.id.notifications)
    void onNotificationsClicked() {
        mPresenter.showNotificationsFragment();
    }


    @OnClick(R.id.addNewEvent)
    void onAddNewEventClicked() {

        mPresenter.onAddNewEventClicked();

    }

    @OnClick(R.id.edit)
    void onEditContactsClicked() {
        //onFragmentDetached("");
        goBack.setVisibility(View.GONE);
        userProfileImage.setVisibility(View.GONE);
        cancelContactChanges.setVisibility(View.VISIBLE);
        edit.setVisibility(View.GONE);
        homeTitle.setVisibility(View.GONE);
        homeTitleText.setText("Contacts");
        homeTitleText.setVisibility(View.VISIBLE);
        homeSearch.setVisibility(View.GONE);
        done.setVisibility(View.VISIBLE);
        createSwarm.setVisibility(View.GONE);
        createMessage.setVisibility(View.GONE);
        addContact.setVisibility(View.GONE);
        newMessage.setVisibility(View.GONE);
        addSwarmContacts.setVisibility(View.GONE);
        cancelNewSwarm.setVisibility(View.GONE);
        goBackAddContact.setVisibility(View.GONE);
        goBackNewMessage.setVisibility(View.GONE);
        moreSettings.setVisibility(View.GONE);

        FragmentManager fragmentManager = getSupportFragmentManager();
        ContactsFragment fragment = (ContactsFragment) fragmentManager.findFragmentByTag(ContactsFragment.TAG);
        if (fragment != null) {
            fragment.editButtonClicked();
        }
    }

    @OnClick(R.id.cancelContactChanges)
    void onCancelContactsClicked() {
        //onFragmentDetached("");
        goBack.setVisibility(View.GONE);
        userProfileImage.setVisibility(View.GONE);
        cancelContactChanges.setVisibility(View.GONE);
        edit.setVisibility(View.VISIBLE);
        homeTitle.setVisibility(View.GONE);
        homeTitleText.setText("Contacts");
        homeTitleText.setVisibility(View.VISIBLE);
        homeSearch.setVisibility(View.GONE);
        done.setVisibility(View.GONE);
        createSwarm.setVisibility(View.GONE);
        createMessage.setVisibility(View.GONE);
        addContact.setVisibility(View.VISIBLE);
        newMessage.setVisibility(View.GONE);
        addSwarmContacts.setVisibility(View.GONE);
        cancelNewSwarm.setVisibility(View.GONE);
        goBackAddContact.setVisibility(View.GONE);
        goBackNewMessage.setVisibility(View.GONE);
        moreSettings.setVisibility(View.GONE);

        FragmentManager fragmentManager = getSupportFragmentManager();
        ContactsFragment fragment = (ContactsFragment) fragmentManager.findFragmentByTag(ContactsFragment.TAG);
        if (fragment != null) {
            fragment.cancelButtonClicked();
        }
    }


    @OnClick(R.id.cancelNewSwarm)
    void onCancelNewSwarmClicked() {
        onFragmentDetached("");
        mPresenter.showContactsFragment();
    }

    @OnClick(R.id.createSwarm)
    void onCreateNewSwarmClicked() {
        onFragmentDetached("");
        mPresenter.showContactsFragment();
    }

    @OnClick(R.id.createMessage)
    void onCreateNewMessageClicked() {
        onFragmentDetached("");
        mPresenter.showMessageChatOMFragment();
    }


    @OnClick(R.id.done)
    void onDoneContactsClicked() {
        //onFragmentDetached("");
        goBack.setVisibility(View.GONE);
        userProfileImage.setVisibility(View.GONE);
        cancelContactChanges.setVisibility(View.GONE);
        edit.setVisibility(View.VISIBLE);
        homeTitle.setVisibility(View.GONE);
        homeTitleText.setText("Contacts");
        homeTitleText.setVisibility(View.VISIBLE);
        homeSearch.setVisibility(View.GONE);
        done.setVisibility(View.GONE);
        createSwarm.setVisibility(View.GONE);
        createMessage.setVisibility(View.GONE);
        addContact.setVisibility(View.VISIBLE);
        newMessage.setVisibility(View.GONE);
        addSwarmContacts.setVisibility(View.GONE);
        cancelNewSwarm.setVisibility(View.GONE);
        goBackAddContact.setVisibility(View.GONE);
        goBackNewMessage.setVisibility(View.GONE);
        moreSettings.setVisibility(View.GONE);

        FragmentManager fragmentManager = getSupportFragmentManager();
        ContactsFragment fragment = (ContactsFragment) fragmentManager.findFragmentByTag(ContactsFragment.TAG);
        if (fragment != null) {
            fragment.cancelButtonClicked();
        }
    }

    @OnClick(R.id.addContact)
    void onAddNewContactsClicked() {
        //onFragmentDetached("");
        goBack.setVisibility(View.GONE);
        userProfileImage.setVisibility(View.GONE);
        cancelContactChanges.setVisibility(View.GONE);
        edit.setVisibility(View.GONE);
        homeTitle.setVisibility(View.GONE);
        homeTitleText.setText("Add New Contact");
        homeTitleText.setVisibility(View.VISIBLE);
        homeSearch.setVisibility(View.GONE);
        done.setVisibility(View.GONE);
        createSwarm.setVisibility(View.GONE);
        createMessage.setVisibility(View.GONE);
        addContact.setVisibility(View.GONE);
        newMessage.setVisibility(View.GONE);
        addSwarmContacts.setVisibility(View.GONE);
        cancelNewSwarm.setVisibility(View.GONE);
        goBackAddContact.setVisibility(View.VISIBLE);
        goBackNewMessage.setVisibility(View.GONE);
        moreSettings.setVisibility(View.GONE);

        FragmentManager fragmentManager = getSupportFragmentManager();
        ContactsFragment fragment = (ContactsFragment) fragmentManager.findFragmentByTag(ContactsFragment.TAG);
        if (fragment != null) {
            fragment.showAddNewContactScreen();
        }
    }

    @OnClick(R.id.goBack)
    void onGoBackClicked() {
        onFragmentDetached("");
        mPresenter.onGoBackClicked(HomeFragment.TAG);
    }

    @OnClick(R.id.goBackAddContact)
    void onGoBackAddContactClicked() {
        onFragmentDetached("");
        mPresenter.onGoBackClicked(ContactsFragment.TAG);
    }

    @OnClick(R.id.goBackNewMessage)
    void onGoBackNewMessageClicked() {
        onFragmentDetached("");
        mPresenter.onGoBackClicked(MessagesFragment.TAG);
    }

    @OnClick(R.id.goBackMessageDetails)
    void onGoBackMessageDetailsClicked() {
        onFragmentDetached("");
        mPresenter.onGoBackClicked(MessagesChatFragment.TAG);
    }


    @OnClick(R.id.newMessage)
    void onNewMessageClicked() {
        onFragmentDetached("");
        mPresenter.showMessagesAddContactsFragment();
    }

    @Override
    public void onBackPressed() {
        //I am overriding the back button. I do not want the default behaviour
        onFragmentDetached("");
        finish();
    }


    @OnClick(R.id.moreSettings)
    void onMoreSettingsClicked() {
        //EventBus.getDefault().post(new ToolBarEvent(AppConstants.SWARM_PROFILE));
        onFragmentDetached("");
        mPresenter.showMessageDetailsFragment();

    }

    @OnClick(R.id.userProfileImage)
    void onUserProfileImageClicked() {
        //open the settings screen

        mPresenter.showYourProfile();


    }


}