package com.CalSocial.ui.onboarding.phoneCode;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.CalSocial.R;
import com.CalSocial.di.component.ActivityComponent;
import com.CalSocial.event.NewMessageEvent;
import com.CalSocial.ui.base.BaseFragment;
import com.CalSocial.ui.dialog.alert.AlertDialog;
import com.CalSocial.utils.AppConstants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PhoneCodeFragment extends BaseFragment implements PhoneCodeMvpView {

    public static final String TAG = "PhoneCodeFragment";

    @BindView(R.id.code_digits)
    View codeDigits;

    @BindView(R.id.code_digit_1)
    EditText digit1;

    @BindView(R.id.code_digit_2)
    EditText digit2;

    @BindView(R.id.code_digit_3)
    EditText digit3;

    @BindView(R.id.code_digit_4)
    EditText digit4;

    @BindView(R.id.code_digit_5)
    EditText digit5;

    @BindView(R.id.code_digit_6)
    EditText digit6;

    @BindView(R.id.resendCode)
    TextView resendCode;

    @Inject
    PhoneCodePresenter<PhoneCodeMvpView> mPresenter;

    public static PhoneCodeFragment newInstance() {
        Bundle args = new Bundle();
        PhoneCodeFragment fragment = new PhoneCodeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.onboarding_fragment_phone_code, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
            resendCode.setTypeface(null, Typeface.BOLD);

        }

        digit1.requestFocus();

        /**
         * The following code moves the focus to the next edit text once a single digit has
         * been entered into the edit text. This has been implemented in order to improve the
         * user experience.
         */


        digit1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count == 1) {
                    digit2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        digit2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (count == 1) {
                    digit3.requestFocus();
                } else if (count == 0) {
                    digit1.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        });
        digit3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count == 1) {
                    digit4.requestFocus();
                } else if (count == 0) {
                    digit2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        digit4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count == 1) {
                    digit5.requestFocus();
                } else if (count == 0) {
                    digit3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        digit5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count == 1) {
                    digit6.requestFocus();
                } else if (count == 0) {
                    digit4.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        digit6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count == 0) {
                    digit5.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

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


    @OnClick(R.id.CalSocialButton)
    void onPhoneContinueClicked() {

        String[] digits = {
                digit1.getText().toString().trim(),
                digit2.getText().toString().trim(),
                digit3.getText().toString().trim(),
                digit4.getText().toString().trim(),
                digit5.getText().toString().trim(),
                digit6.getText().toString().trim()
        };

        mPresenter.onPhoneContinueClicked(digits, TAG + "-" + AppConstants.PROCEED_TO_ADDITIONAL_INFO_PHONE_CODE);

    }

    @OnClick(R.id.resendCodeTv)
    void onResendCodeClicked() {
        mPresenter.onResendCodeClicked();
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

    @Override
    public void populateDigits(String[] digits) {

        //The digits will be populated when the app will detect that an sms message with the
        //code has been received. The presenter will call this function then.
        digit1.setText(digits[0]);
        digit2.setText(digits[1]);
        digit3.setText(digits[2]);
        digit4.setText(digits[3]);
        digit5.setText(digits[4]);
        digit6.setText(digits[5]);

    }

    @Override
    public void showAlertDialogMessage(String title, String message) {
        AlertDialog alertDialog = AlertDialog.newInstance();
        alertDialog.setAlertTitle(title);
        alertDialog.setAlertMessage(message);
        alertDialog.show(getFragmentManager());

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(NewMessageEvent event) {

        mPresenter.onSmsCodeReceived(event.message);
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
    public Context getContext() {
        return getActivity();
    }

}