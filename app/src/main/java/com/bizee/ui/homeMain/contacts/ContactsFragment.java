package com.CalSocial.ui.homeMain.contacts;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.CalSocial.R;
import com.CalSocial.di.component.ActivityComponent;
import com.CalSocial.ui.base.BaseFragment;
import com.CalSocial.ui.homeMain.adapter.ContactsPagerAdapter;
import com.CalSocial.ui.homeMain.contactsHiveFragment.ContactsHiveFragment;
import com.CalSocial.ui.homeMain.contactsSwarmFragment.ContactsSwarmFragment;
import com.google.android.material.tabs.TabLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactsFragment extends BaseFragment implements ContactsMvpView {

    public static final String TAG = "ContactsFragment";

    @Inject
    ContactsPresenter<ContactsMvpView> mPresenter;

    @BindView(R.id.contactsViewPager)
    ViewPager mContactsViewPager;

    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;

    @BindView(R.id.cl_root_view)
    ConstraintLayout rootConstraintLayout;

    @Inject
    ContactsPagerAdapter mPagerAdapter;

    public static ContactsFragment newInstance() {
        Bundle args = new Bundle();
        ContactsFragment fragment = new ContactsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contacts_fragment_contacts, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
        }

        mPagerAdapter.setCount(2);

        mPagerAdapter.setMode(0);
        mContactsViewPager.setAdapter(mPagerAdapter);

        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.hive)));
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.swarms)));

        mContactsViewPager.setOffscreenPageLimit(mTabLayout.getTabCount());

        mContactsViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        LinearLayout tabLayout = (LinearLayout) ((ViewGroup) mTabLayout.getChildAt(0)).getChildAt(0);
        TextView tabTextView = (TextView) tabLayout.getChildAt(1);
        tabTextView.setTypeface(tabTextView.getTypeface(), Typeface.NORMAL);
        tabTextView.setAllCaps(false);


        tabLayout = (LinearLayout) ((ViewGroup) mTabLayout.getChildAt(0)).getChildAt(1);
        tabTextView = (TextView) tabLayout.getChildAt(1);
        tabTextView.setTypeface(tabTextView.getTypeface(), Typeface.NORMAL);
        tabTextView.setAllCaps(false);


        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                LinearLayout tabLayout = (LinearLayout) ((ViewGroup) mTabLayout.getChildAt(0)).getChildAt(tab.getPosition());
                TextView tabTextView = (TextView) tabLayout.getChildAt(1);
                tabTextView.setTypeface(tabTextView.getTypeface(), Typeface.BOLD);
                tabTextView.setAllCaps(false);
                mContactsViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                LinearLayout tabLayout = (LinearLayout) ((ViewGroup) mTabLayout.getChildAt(0)).getChildAt(tab.getPosition());
                TextView tabTextView = (TextView) tabLayout.getChildAt(1);
                tabTextView.setTypeface(tabTextView.getTypeface(), Typeface.NORMAL);
                tabTextView.setAllCaps(false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        DisplayMetrics displayMetrics = getActivity().getResources().getDisplayMetrics();
        float height = (displayMetrics.heightPixels);
        reinitializeViewPagerConstraints((int) height);//950

        return view;
    }

    private void reinitializeViewPagerConstraints(int height) {
        //Since I am changing the height of teh viewpager I also have to
        //set the constraints again, because they get messed up.

        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT, height);
        layoutParams.setMargins(0, 0, 0, 0);
        mContactsViewPager.setLayoutParams(layoutParams);

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(rootConstraintLayout);

        constraintSet.connect(R.id.contactsViewPager, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 0);
        constraintSet.connect(R.id.contactsViewPager, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 0);
        constraintSet.connect(R.id.contactsViewPager, ConstraintSet.TOP, R.id.tabLayout, ConstraintSet.BOTTOM, 0);

        constraintSet.applyTo(rootConstraintLayout);
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
    public void showHiveEditScreen(Fragment fragment) {
        ((ContactsHiveFragment) fragment).showEditScreen();
    }

    @Override
    public void showSwarmEditScreen(Fragment fragment) {
        ((ContactsSwarmFragment) fragment).showEditScreen();
    }

    @Override
    public void showHiveViewScreen(Fragment fragment) {
        ((ContactsHiveFragment) fragment).showViewScreen();
    }

    @Override
    public void showSwarmViewScreen(Fragment fragment) {
        ((ContactsSwarmFragment) fragment).showViewScreen();
    }

    public void editButtonClicked() {
        mPresenter.editButtonClicked(getFragmentManager());
    }

    public void cancelButtonClicked() {
        mPresenter.cancelButtonClicked(getFragmentManager());
    }

    public void showAddNewContactScreen() {

        mTabLayout.removeAllTabs();
        mPagerAdapter.setCount(2);
        mPagerAdapter.setMode(1);
        mContactsViewPager.setAdapter(mPagerAdapter);
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.phone_contacts)));
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.CalSocial_network)));

        mContactsViewPager.setOffscreenPageLimit(mTabLayout.getTabCount());

        mContactsViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        LinearLayout tabLayout = (LinearLayout) ((ViewGroup) mTabLayout.getChildAt(0)).getChildAt(0);
        TextView tabTextView = (TextView) tabLayout.getChildAt(1);
        tabTextView.setTypeface(tabTextView.getTypeface(), Typeface.NORMAL);
        tabTextView.setAllCaps(false);


        tabLayout = (LinearLayout) ((ViewGroup) mTabLayout.getChildAt(0)).getChildAt(1);
        tabTextView = (TextView) tabLayout.getChildAt(1);
        tabTextView.setTypeface(tabTextView.getTypeface(), Typeface.NORMAL);
        tabTextView.setAllCaps(false);


        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                LinearLayout tabLayout = (LinearLayout) ((ViewGroup) mTabLayout.getChildAt(0)).getChildAt(tab.getPosition());
                TextView tabTextView = (TextView) tabLayout.getChildAt(1);
                tabTextView.setTypeface(tabTextView.getTypeface(), Typeface.BOLD);
                tabTextView.setAllCaps(false);
                mContactsViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                LinearLayout tabLayout = (LinearLayout) ((ViewGroup) mTabLayout.getChildAt(0)).getChildAt(tab.getPosition());
                TextView tabTextView = (TextView) tabLayout.getChildAt(1);
                tabTextView.setTypeface(tabTextView.getTypeface(), Typeface.NORMAL);
                tabTextView.setAllCaps(false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

}