package com.CalSocial.ui.homeMain.home;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.applandeo.materialcalendarview.listeners.OnCalendarPageChangeListener;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.CalSocial.R;
import com.CalSocial.data.network.model.EventsHomeResponse;
import com.CalSocial.data.network.model.UsersResponse;
import com.CalSocial.di.component.ActivityComponent;
import com.CalSocial.event.GestureEvent;
import com.CalSocial.ui.base.BaseFragment;
import com.CalSocial.ui.homeMain.adapter.EventsAdapter;
import com.CalSocial.ui.homeMain.adapter.FavoritesAdapter;

import net.cachapa.expandablelayout.ExpandableLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment implements HomeMvpView, FavoritesAdapter.Callback {

    public static final String TAG = "HomeFragment";

    @Inject
    HomePresenter<HomeMvpView> mPresenter;

    @Inject
    LinearLayoutManager mFavoritesLayoutManager;

    @BindView(R.id.favoritesRV)
    RecyclerView mFavoritesRecyclerView;

    @Inject
    FavoritesAdapter mFavoritesAdapter;

    @BindView(R.id.calendarView)
    CalendarView calendarView;

    @BindView(R.id.currentDate)
    TextView currentDateTV;

    @BindView(R.id.expandableLayout)
    ExpandableLayout expandableLayout;

    @BindView(R.id.eventsRV)
    RecyclerView mEventsRecyclerView;

    @Inject
    EventsAdapter mEventsAdapter;

    @Inject
    LinearLayoutManager mEventsLayoutManager;

    private Calendar calendar;
    private static Boolean isCalendarExpanded = false;
    private SimpleDateFormat dateFormat;

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment_home, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
        }

        mEventsRecyclerView.setNestedScrollingEnabled(false);
        mEventsRecyclerView.setLayoutManager(mEventsLayoutManager);
        mEventsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mEventsRecyclerView.setAdapter(mEventsAdapter);
        mPresenter.onEventsRVViewPrepared();


        init();

        return view;
    }

    protected void init() {
        dateFormat = new SimpleDateFormat("EEEE, MMMM dd, yyyy");

        mFavoritesLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mFavoritesRecyclerView.setLayoutManager(mFavoritesLayoutManager);
        mFavoritesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mFavoritesRecyclerView.setAdapter(mFavoritesAdapter);
        mPresenter.onFavoritesRVViewPrepared();

        List<EventDay> events = new ArrayList<>();
        calendar = Calendar.getInstance();
        //calendar.set(2020, 1, 26);
        //events.add(new EventDay(calendar, R.mipmap.ic_launcher));
/*//or
        events.add(new EventDay(calendar, new Drawable()));
//or if you want to specify event label color
        events.add(new EventDay(calendar, R.drawable.sample_icon, Color.parseColor("#228B22")));*/
        calendarView.setEvents(events);
        calendarView.setHeaderVisibility(View.GONE);
        calendarView.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);

        currentDateTV.setText(dateFormat.format(calendar.getTime()));

        calendarView.setOnForwardPageChangeListener(new OnCalendarPageChangeListener() {
            @Override
            public void onChange() {
                calendar.add(Calendar.MONTH, 1);
                currentDateTV.setText(dateFormat.format(calendar.getTime()));
            }
        });

        calendarView.setOnPreviousPageChangeListener(new OnCalendarPageChangeListener() {
            @Override
            public void onChange() {
                calendar.add(Calendar.MONTH, -1);
                currentDateTV.setText(dateFormat.format(calendar.getTime()));

            }
        });

        //((TextView)calendarView.findViewById(R.id.dayLabel)).setBackgroundResource(R.drawable.add_to_hive);

        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                /*Calendar clickedDayCalendar = eventDay.getCalendar();
                Log.e("selected day", dateFormat.format(clickedDayCalendar.getTime()));
                List<Calendar> calendars = new ArrayList<>();
                calendars.add(clickedDayCalendar);*/
                //calendarView.setSelectedDates(calendars);
                //calendarView.setHighlightedDays(calendars);
                //eventDay.isEnabled();

                mPresenter.getEventsForSelectedDay(eventDay);

            }
        });

        try {
            calendarView.setDate(calendar);
        } catch (OutOfDateRangeException e) {
            e.printStackTrace();
        }

        mEventsRecyclerView.setLayoutManager(mEventsLayoutManager);
        mEventsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mEventsRecyclerView.setAdapter(mEventsAdapter);
        mPresenter.onEventsRVViewPrepared();

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
    public void onRepoEmptyViewRetryClick() {

    }

    @Override
    public void updateFavoritesRepo(List<UsersResponse.User> userList) {
        mFavoritesAdapter.addItems(userList);

    }

    @Override
    public void updateEventsHomeRepo(List<EventsHomeResponse.Event> eventList) {
        mEventsAdapter.addItems(eventList);

    }

    @Override
    public void toggleCalendar() {
        if (isCalendarExpanded) {
            expandableLayout.collapse();

        } else {
            expandableLayout.expand();
        }

        isCalendarExpanded = !isCalendarExpanded;
    }

    @Override
    public void getNextMonth() {
        try {
            calendar.add(Calendar.MONTH, 1);
            calendarView.setDate(calendar);
            currentDateTV.setText(dateFormat.format(calendar.getTime()));

        } catch (OutOfDateRangeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getPreviousMonth() {
        try {

            calendar.add(Calendar.MONTH, -1);
            calendarView.setDate(calendar);
            currentDateTV.setText(dateFormat.format(calendar.getTime()));

        } catch (OutOfDateRangeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void repopulateEventsRV() {

    }

    @Override
    public void openContactProfile() {

    }

    @Override
    public void openSwarmProfile() {

    }

    @Override
    public void closeCalendarOnScroll(GestureEvent event) {
        MotionEvent e1 = event.e1;
        MotionEvent e2 = event.e2;
        float distanceX = event.distanceX;
        float distanceY = event.distanceY;

        if (isCalendarExpanded) {
            int yLocRecycleView = mFavoritesRecyclerView.getTop() + mFavoritesRecyclerView.getHeight();
            if (e2.getY() > yLocRecycleView) {
                if (Math.abs(distanceY) > 30) {
                    //This condition hides the calendar on scroll up.
                    if (e1.getY() >= e2.getY()) {

                        mPresenter.toggleCalendar();
                    }
                }

            }
        }
    }

    @OnClick(R.id.currentDate)
    void onCalendarHeaderClicked() {
        mPresenter.toggleCalendar();
    }


    @OnClick(R.id.previousMonth)
    void onPreviousMonthClicked() {
        mPresenter.getPreviousMonth();
    }

    @OnClick(R.id.nextMonth)
    void onNextMonthClicked() {
        mPresenter.getNextMonth();
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true, priority = 1)
    public void onMessageEvent(GestureEvent event) {
        if (event.gesture.equalsIgnoreCase("onScroll")) {

            mPresenter.closeCalendarOnScroll(event);

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

