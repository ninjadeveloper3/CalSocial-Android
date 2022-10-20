package com.CalSocial.ui.onboarding.landing;

import android.animation.LayoutTransition;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.CalSocial.R;
import com.CalSocial.di.component.ActivityComponent;
import com.CalSocial.event.GestureEvent;
import com.CalSocial.ui.base.BaseFragment;
import com.CalSocial.utils.AppConstants;
import com.CalSocial.widget.CalSocialButton;
import com.CalSocial.widget.CustomViewPager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LandingFragment extends BaseFragment implements LandingMvpView {

    public static final String TAG = "LandingFragment";

    @BindView(R.id.landingViewPager)
    CustomViewPager mViewPager;

    @Inject
    LandingPagerAdapter mPagerAdapter;

    @BindView(R.id.logo)
    View logo;

    @BindView(R.id.login)
    TextView login;

    @BindView(R.id.signUp)
    CalSocialButton signUp;

    @BindView(R.id.cl_root_view)
    LinearLayout rootConstraintLayout;

    @BindView(R.id.layoutDots)
    LinearLayout layoutDots;

    @Inject
    LandingPresenter<LandingMvpView> mPresenter;

    public static LandingFragment newInstance() {
        Bundle args = new Bundle();
        LandingFragment fragment = new LandingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private int positionViewPager = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.onboarding_fragment_landing, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
            login.setTypeface(null, Typeface.BOLD);

        }

        DisplayMetrics displayMetrics = getActivity().getResources().getDisplayMetrics();
        float height = (displayMetrics.heightPixels * 0.7f);
      /*  float dpHeight = displayMetrics.heightPixels / displayMetrics.density;
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        long duration = 300L;*/

        mViewPager.setScrollDurationFactor(1);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        signUp.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        mViewPager.setCurrentItem(0);
        addBottomDots(layoutDots, mPagerAdapter.getCount(), 0);
        reinitializeViewPagerConstraints((int) height);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                positionViewPager = position;

            }

            @Override
            public void onPageSelected(int position) {
                addBottomDots(layoutDots, mPagerAdapter.getCount(), position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

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

    @OnClick(R.id.login)
    void onSigninClicked() {
        mPresenter.onSigninClicked(TAG + "-" + AppConstants.OPEN_LOGIN);
    }


    @OnClick(R.id.CalSocialButton)
    void onSignupClicked() {
        mPresenter.onSignupClicked(TAG + "-" + AppConstants.OPEN_SIGNUP);
    }


    private void reinitializeViewPagerConstraints(int height) {
        //Since I am changing the height of teh viewpager I also have to
        //set the constraints again, because they get messed up.

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, height);
        layoutParams.setMargins(0, 0, 0, 0);
        mViewPager.setLayoutParams(layoutParams);

    }


    private void addBottomDots(LinearLayout layout_dots, int size, int current) {
        //TODO: This is a temporary implementation. The project will be eventually converted to androidx and
        //we will then use an available third-party library for showing the dots.
        ImageView[] dots = new ImageView[size];
        layout_dots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(getActivity());
            int width_height = 25;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(width_height, width_height));
            params.setMargins(10, 10, 10, 10);
            dots[i].setLayoutParams(params);
            dots[i].setImageResource(R.drawable.shape_circle_outline);
            layout_dots.addView(dots[i]);
        }
        if (dots.length > 0) {
            dots[current].setImageResource(R.drawable.shape_circle);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true, priority = 1)
    public void onMessageEvent(GestureEvent event) {
        long duration = 300L;

        if (event.gesture.equalsIgnoreCase("left scroll")) {
            Log.e("login y", login.getY() + "");
            Log.e("logo y", logo.getY() + "");
            DisplayMetrics displayMetrics = getActivity().getResources().getDisplayMetrics();
            float dpPixels = (50 / displayMetrics.density);
            Log.e("dpPixels", dpPixels + "");
            final float moveToLeftLocation = ((logo.getWidth() / 3) - 50) * -1;//(dpWidth * (-1)) + 50;
            //final float moveToTopLocation = (logo.getY() - (login.getY() + login.getHeight()) + dpPixels) * -1;
            final float moveToTopLocation = (logo.getY()+dpPixels) * -1;

            //I am starting at position 0 is because I want the animation to start as soon as I start to
            //scroll the viewpager but the first page should be shown.
            if (positionViewPager == 0) {
                logo.animate().scaleX(0.5F).setDuration(duration).setStartDelay(0);
                logo.animate().scaleY(0.5F).setDuration(duration).setStartDelay(0);
                logo.animate().translationX(moveToLeftLocation).setDuration(duration).setStartDelay(0);
                logo.animate().translationY(moveToTopLocation).setDuration(duration).setStartDelay(0);
            }

        } else {

            if (positionViewPager == 0) {
                logo.animate().scaleX(1.0F).setDuration(duration).setStartDelay(0);
                logo.animate().scaleY(1.0F).setDuration(duration).setStartDelay(0);
                logo.animate().translationX(0).setDuration(duration).setStartDelay(0);
                logo.animate().translationY(0).setDuration(duration).setStartDelay(0);
                //mViewPager.animate().translationY(0).setDuration(500);
            }


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