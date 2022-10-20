/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.CalSocial.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import com.CalSocial.R;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import io.reactivex.annotations.NonNull;


/**
 * Created by janisharali on 27/01/17.
 */

public final class ViewUtils {

    private ViewUtils() {
        // This utility class is not publicly instantiable
    }

    public static float pxToDp(float px) {
        float densityDpi = Resources.getSystem().getDisplayMetrics().densityDpi;
        return px / (densityDpi / 160f);
    }

    public static int dpToPx(float dp) {
        float density = Resources.getSystem().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

    public static void changeIconDrawableToGray(Context context, Drawable drawable) {
        if (drawable != null) {
            drawable.mutate();
            drawable.setColorFilter(ContextCompat
                    .getColor(context, R.color.darkGray), PorterDuff.Mode.SRC_ATOP);
        }
    }

    public static BitmapImageViewTarget getRoundedImageTarget(@NonNull final Context context, @NonNull final ImageView imageView,
                                                              final float radius) {


        return new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(final Bitmap resource) {

                int originalBitmapWidth = resource.getWidth();
                int originalBitmapHeight = resource.getHeight();
                int borderWidth = 150;
                int borderColor = context.getResources().getColor(R.color.white);
                if (borderWidth != -1 && borderColor != -1) {
                    Canvas canvas = new Canvas(resource);
                    canvas.drawBitmap(resource, 0, 0, null);

                    Paint borderPaint = new Paint();
                    borderPaint.setStyle(Paint.Style.STROKE);
                    borderPaint.setStrokeWidth(borderWidth);
                    borderPaint.setAntiAlias(true);
                    borderPaint.setColor(borderColor);

                    int roundedRectDelta = (borderWidth / 3);
                    RectF rectF = new RectF(0 + roundedRectDelta, 0 + roundedRectDelta, originalBitmapWidth - roundedRectDelta, originalBitmapHeight - roundedRectDelta);
                    canvas.drawRoundRect(rectF, radius, radius, borderPaint);
                }

                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCornerRadius(radius);

                imageView.setImageDrawable(circularBitmapDrawable);
            }
        };
    }
}
