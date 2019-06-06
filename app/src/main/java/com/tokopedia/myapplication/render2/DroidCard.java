package com.tokopedia.myapplication.render2;

import android.graphics.Bitmap;
import com.tokopedia.myapplication.render1.Droid;

/**
 * Class for creating droid cards, which feature a droid's name and image.
 **/
public class DroidCard {

    protected static final float SPACE_AROUND_IMAGE = 20f;

    protected static final float BITMAP_HEIGHT_HEADER_HEIGHT_RATIO = .25f;

    private Droid mDroid;
    private Bitmap mBitmap;

    private float mHeaderHeight;
    private float mBodyHeight;
    private float mTitleSize;

    public DroidCard(Droid droid, Bitmap bitmap) {
        mDroid = droid;
        mBitmap = bitmap;

        mBodyHeight = mBitmap.getHeight() + SPACE_AROUND_IMAGE;
        mHeaderHeight = mBitmap.getHeight() * BITMAP_HEIGHT_HEADER_HEIGHT_RATIO;
        mTitleSize = mHeaderHeight / 2;
    }

    private String logDimensions() {
        return "mBodyHeight = " + mBodyHeight +
                ", mHeaderHeight = " + mHeaderHeight +
                ", mTitleSize = " + mTitleSize +
                ", getWidth() = " + String.valueOf(getWidth());
    }

    public float getWidth() {
        return mBitmap.getWidth() + (2 * SPACE_AROUND_IMAGE);
    }

    public float getBodyHeight() {
        return mBodyHeight;
    }

    public float getHeaderHeight() {
        return mHeaderHeight;
    }

    public float getHeight() {
        return getBodyHeight() + getHeaderHeight();
    }

    public float getTitleSize() {
        return mTitleSize;
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public Droid getDroid() {
        return mDroid;
    }

    public float getTitleXOffset() {
        return SPACE_AROUND_IMAGE;
    }

    public float getTitleYOffset() {
        return SPACE_AROUND_IMAGE;
    }
}
