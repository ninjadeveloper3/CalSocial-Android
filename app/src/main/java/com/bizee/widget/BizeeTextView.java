package com.CalSocial.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.CalSocial.R;

public class CalSocialTextView extends LinearLayout {

    private TextView textView;
    private String text;
    private int textColor;
    private int textSize;
    private String fontFamily;

    public CalSocialTextView(Context context) {
        super(context);
        initializeViews(context, null);

    }

    public CalSocialTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initializeViews(context, attrs);


    }

    public CalSocialTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeViews(context, attrs);

    }

    public CalSocialTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initializeViews(context, attrs);

    }

    private void initializeViews(Context context, AttributeSet set) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.widget_CalSocial_text_view, this);

        if (set == null) {
            return;
        }

        TypedArray typedArray;
        typedArray = context
                .obtainStyledAttributes(set, R.styleable.CalSocialTextView);
        text = typedArray.getString(R.styleable.CalSocialTextView_text);
        textColor = typedArray.getColor(R.styleable.CalSocialTextView_text_color, Color.BLACK);
        textSize = typedArray.getInt(R.styleable.CalSocialTextView_text_size, 20);
        fontFamily = typedArray.getString(R.styleable.CalSocialTextView_font_family);
        typedArray.recycle();

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        textView = (TextView) this
                .findViewById(R.id.label);
        textView.setText(text);
        textView.setTextColor(textColor);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);

        if (fontFamily != null) {
            if (fontFamily.equalsIgnoreCase("NiveauGroteskRegular")) {
                textView.setTypeface(Typeface.createFromAsset(getContext().getAssets(),
                        "font/hk_grotesk_regular.ttf"));
            } else if (fontFamily.equalsIgnoreCase("AvenirBlack")) {


            } else if (fontFamily.equalsIgnoreCase("AvenirBook")) {


            }
        }
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTitle(String title) {
        textView.setText(title);
    }
}
