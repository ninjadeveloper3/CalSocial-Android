package com.CalSocial.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.CalSocial.R;

public class CalSocialButton extends LinearLayout {

    private Button button;
    private Integer type;
    private String text;
    private int textColor;
    private Integer buttonType;
    private Integer buttonTextSize;

    public CalSocialButton(Context context) {
        super(context);

        initializeViews(context, null);

    }

    public CalSocialButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initializeViews(context, attrs);

    }

    public CalSocialButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeViews(context, attrs);

    }

    public CalSocialButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initializeViews(context, attrs);

    }

    private void initializeViews(Context context, AttributeSet set) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.widget_CalSocial_button, this);

        if (set == null) {

            return;
        }

        TypedArray typedArray;
        typedArray = context
                .obtainStyledAttributes(set, R.styleable.CalSocialButton);
        //type = typedArray.getInteger(R.styleable.CalSocialButton_button_text, 0);
        text = typedArray.getString(R.styleable.CalSocialButton_button_text);
        buttonType = typedArray.getInt(R.styleable.CalSocialButton_button_type, 0);
        buttonTextSize = typedArray.getInt(R.styleable.CalSocialButton_button_text_size, 16);

        typedArray.recycle();

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        // Sets the images for the previous and next buttons. Uses
        // built-in images so you don't need to add images, but in
        // a real application your images should be in the
        // application package so they are always available.

        button = (Button) this
                .findViewById(R.id.CalSocialButton);
        button.setText(text);
        button.setTextSize(TypedValue.COMPLEX_UNIT_DIP, buttonTextSize);
        button.setAllCaps(false);
        button.setTypeface(Typeface.createFromAsset(getContext().getAssets(),
                "font/avenir_black.ttf"), Typeface.BOLD);

        if (buttonType == 0) {
            button.setBackground(getResources().getDrawable(R.drawable.button_CalSocial_yellow_selector, null));

        } else if (buttonType == 1) {
            button.setBackground(getResources().getDrawable(R.drawable.button_dark_green_selector, null));

        } else if (buttonType == 2) {
            button.setBackground(getResources().getDrawable(R.drawable.button_light_green_selector, null));

        } else if (buttonType == 3) {
            button.setBackground(getResources().getDrawable(R.drawable.button_lighter_green_selector, null));

        } else if (buttonType == 4) {
            button.setBackground(getResources().getDrawable(R.drawable.button_CalSocial_red_selector, null));

        } else if (buttonType == 5) {
            button.setBackground(getResources().getDrawable(R.drawable.button_dark_brown_selector, null));

        } else {
            button.setBackground(getResources().getDrawable(R.drawable.button_light_green_selector, null));
        }

    }

    public TextView getButton() {
        return button;
    }

}