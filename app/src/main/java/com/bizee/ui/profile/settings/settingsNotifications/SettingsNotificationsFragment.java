package com.CalSocial.ui.profile.settings.settingsNotifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.CalSocial.R;
import com.CalSocial.di.component.ActivityComponent;
import com.CalSocial.ui.base.BaseFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class SettingsNotificationsFragment extends BaseFragment implements SettingsNotificationsMvpView {

    public static final String TAG = "SettingsNotificationsFragment";

    @Inject
    SettingsNotificationsPresenter<SettingsNotificationsMvpView> mPresenter;

    public static SettingsNotificationsFragment newInstance() {
        Bundle args = new Bundle();
        SettingsNotificationsFragment fragment = new SettingsNotificationsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profiles_fragment_settings_notifications, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
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

}