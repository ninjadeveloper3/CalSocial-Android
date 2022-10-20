package com.CalSocial.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.CalSocial.R;

public class CalSocialEditText extends LinearLayout {

    private EditText editText;
    private TextView textView;
    private Integer type;

    public CalSocialEditText(Context context) {
        super(context);
        initializeViews(context, null);

    }

    public CalSocialEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initializeViews(context, attrs);

    }

    public CalSocialEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeViews(context, attrs);

    }

    public CalSocialEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initializeViews(context, attrs);

    }

    private void initializeViews(Context context, AttributeSet set) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.widget_CalSocial_edittext, this);

        if (set == null) {
            return;
        }

        TypedArray typedArray;
        typedArray = context
                .obtainStyledAttributes(set, R.styleable.CalSocialEditText);
        type = typedArray.getInteger(R.styleable.CalSocialEditText_type_label, 0);

        typedArray.recycle();

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        // Sets the images for the previous and next buttons. Uses
        // built-in images so you don't need to add images, but in
        // a real application your images should be in the
        // application package so they are always available.
        editText = (EditText) this
                .findViewById(R.id.editText);

        textView = (TextView) this
                .findViewById(R.id.label);

   /*     textView.setTypeface(Typeface.createFromAsset(getContext().getAssets(),
                "font/hk_grotesk_regular.ttf"));*/

        if (type == 0) {
            textView.setText(getResources().getString(R.string.first_name));
            editText.setHint(R.string.first_name);
        } else if (type == 1) {
            textView.setText(getResources().getString(R.string.last_name));
            editText.setHint(R.string.last_name);
        } else if (type == 2) {
            textView.setText(getResources().getString(R.string.phone));
            editText.setHint(R.string.phone);
            editText.setInputType(InputType.TYPE_CLASS_PHONE);
        } else if (type == 3) {
            textView.setText(getResources().getString(R.string.location));
            editText.setHint(R.string.location_hint);
        } else if (type == 4) {
            textView.setText(getResources().getString(R.string.bio));
            editText.setHint(R.string.bio_hint);
            final LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 360); // Width , height
            int margin = (int) getResources().getDimension(R.dimen.dp_10);
            int padding = (int) getResources().getDimension(R.dimen.dp_20);
            params.setMargins(margin, margin, margin, margin);
            editText.setLayoutParams(params);
            editText.setGravity(Gravity.TOP);
            editText.setPadding(padding, padding, 0, 0);
        } else if (type == 6) {
            textView.setText(getResources().getString(R.string.name));
            editText.setHint(R.string.name_hint);
        } else if (type == 7) {
            textView.setText(getResources().getString(R.string.bucket_one));
            editText.setHint(R.string.bucket_one_hint);
        } else if (type == 8) {
            textView.setText(getResources().getString(R.string.bucket_two));
            editText.setHint(R.string.bucket_two_hint);
        } else if (type == 9) {
            textView.setText(getResources().getString(R.string.bucket_three));
            editText.setHint(R.string.bucket_three_hint);
        } else if (type == 10) {
            textView.setText(getResources().getString(R.string.bucket_four));
            editText.setHint(R.string.bucket_four_hint);
        } else if (type == 11) {
            textView.setText(getResources().getString(R.string.bucket_five));
            editText.setHint(R.string.bucket_five_hint);
        } else if (type == 12) {
            textView.setText(getResources().getString(R.string.swarm));
            editText.setHint(R.string.swarm_hint);
        } else if (type == 13) {
            textView.setText(getResources().getString(R.string.about_us));
            editText.setHint(R.string.about_us_hint);
        } else if (type == 14) {
            textView.setVisibility(GONE);
            editText.setHint(R.string.message_hint);
        } else if (type == 15) {
            textView.setText(R.string.event_title);
            editText.setHint(R.string.event_title_hint);
        } else if (type == 16) {
            textView.setText(R.string.date);
            editText.setHint(R.string.date_hint);
            editText.setBackground(getResources().getDrawable(R.drawable.edittext_rounded_corner_border_date));
        } else if (type == 17) {
            textView.setText(R.string.location_where);
            editText.setHint(R.string.location_where_hint);
        } else if (type == 18) {
            textView.setText(R.string.notes);
            editText.setHint(R.string.notes_hint);
            final LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 360); // Width , height
            int margin = (int) getResources().getDimension(R.dimen.dp_10);
            int padding = (int) getResources().getDimension(R.dimen.dp_20);
            params.setMargins(margin, margin, margin, margin);
            editText.setLayoutParams(params);
            editText.setGravity(Gravity.TOP);
            editText.setPadding(padding, padding, 0, 0);
        } else if (type == 19) {
            textView.setText(R.string.swarm);
            editText.setHint(R.string.swarm_create_title_hint);

        } else if (type == 20) {
            textView.setText(R.string.about_us);
            editText.setHint(R.string.about_us_swarm_create_hint);
            final LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 360); // Width , height
            int margin = (int) getResources().getDimension(R.dimen.dp_10);
            int padding = (int) getResources().getDimension(R.dimen.dp_20);
            params.setMargins(margin, margin, margin, margin);
            editText.setLayoutParams(params);
            editText.setGravity(Gravity.TOP);
            editText.setPadding(padding, padding, 0, 0);
        } else {
            textView.setVisibility(GONE);
            editText.setHint("");
        }

    }

    public EditText getEditText() {
        return editText;
    }

    public TextView getTextView() {
        return textView;
    }

}
