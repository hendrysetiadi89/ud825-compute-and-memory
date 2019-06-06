package com.tokopedia.myapplication.computeandmemory;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import com.tokopedia.myapplication.R;

import java.lang.ref.WeakReference;

public class BusyUIThreadActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busy_uithread);

        Button theButtonThatLoadsAnImage = (Button) findViewById(R.id.busyui_button_load);
        theButtonThatLoadsAnImage.setText("Display an image.");

        Button theButtonThatLoadsAnImage2 = (Button) findViewById(R.id.busyui_button_load2);
        theButtonThatLoadsAnImage2.setText("Display an image (optimized).");

        theButtonThatLoadsAnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sepiaAndDisplayImage();
            }
        });
        theButtonThatLoadsAnImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sepiaAndDisplayImage2();
            }
        });

        WebView webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);

        webView.loadUrl("file:///android_asset/shiver_me_timbers.gif");
    }

    /**
     * Loads a hardcoded (Don't judge me) bitmap into memory, applies
     * a sepia filter, and displays it.
     */
    public void sepiaAndDisplayImage() {
        // The following blobs of code *gleefully* pilfered from the Android Training class,
        // appropriately titled "Loading Large Bitmaps Efficiently".  Efficiently, eh?
        // Challenge accepted!
        Bitmap loadedBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.punk_droid);

        int width = loadedBitmap.getWidth();
        int height = loadedBitmap.getHeight();
        Bitmap sepiaBitmap = Bitmap.createBitmap(width, height, loadedBitmap.getConfig());

        // Go through every single pixel in the bitmap, apply a sepia filter to it, and apply it
        // to the new "output" bitmap.
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                // get pixel color
                int pixel = loadedBitmap.getPixel(x, y);
                int alpha = Color.alpha(pixel);

                // These are the starting values for this pixel.
                int inputRed = Color.red(pixel);
                int inputGreen = Color.green(pixel);
                int inputBlue = Color.blue(pixel);

                // These are the sepia-fied values for each pixel.
                // Note that if the resulting value is over 255, Math.Min brings it back down.
                // So effectively, min establishes a max value!  Isn't that weird?
                int outRed = (int) Math.min(
                        (inputRed * .393) + (inputGreen * .769) + (inputBlue * .189), 255);

                int outGreen = (int) Math.min(
                        (inputRed * .349) + (inputGreen * .686) + (inputBlue * .168), 255);

                int outBlue = (int) Math.min(
                        (inputRed * .272) + (inputGreen * .534) + (inputBlue * .131), 255);
                // apply new pixel color to output bitmap
                sepiaBitmap.setPixel(x, y, Color.argb(alpha, outRed, outGreen, outBlue));
            }
        }
        ImageView imageView = (ImageView) findViewById(R.id.busyui_imageview);
        imageView.setImageBitmap(sepiaBitmap);
    }

    public void sepiaAndDisplayImage2() {
        // The following blobs of code *gleefully* pilfered from the Android Training class,
        // appropriately titled "Loading Large Bitmaps Efficiently".  Efficiently, eh?
        // Challenge accepted!
        Bitmap loadedBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.punk_droid);

        // By performing the sepia filter manipulations in an AsyncTask, our UI can continue while
        // the CPU is processing the filter. Notice how the dancing pirate never misses a beat now?
        new SepiaFilterTask(this).execute(loadedBitmap);

    }

    static class SepiaFilterTask extends AsyncTask<Bitmap, Void, Bitmap> {
        WeakReference<Activity> activityWeakReference;

        public SepiaFilterTask(Activity activity) {
            activityWeakReference = new WeakReference<>(activity);
        }

        protected Bitmap doInBackground(Bitmap... bitmaps) {
            Bitmap loadedBitmap = bitmaps[0];

            int width = loadedBitmap.getWidth();
            int height = loadedBitmap.getHeight();
            Bitmap sepiaBitmap = Bitmap.createBitmap(width, height, loadedBitmap.getConfig());

            // Go through every single pixel in the bitmap, apply a sepia filter to it, and apply it
            // to the new "output" bitmap.
            for (int x = 0; x < width; ++x) {
                for (int y = 0; y < height; ++y) {
                    // get pixel color
                    int pixel = loadedBitmap.getPixel(x, y);
                    int alpha = Color.alpha(pixel);

                    // These are the starting values for this pixel.
                    int inputRed = Color.red(pixel);
                    int inputGreen = Color.green(pixel);
                    int inputBlue = Color.blue(pixel);

                    // These are the sepia-fied values for each pixel.
                    // Note that if the resulting value is over 255, Math.Min brings it back down.
                    // So effectively, min establishes a max value!  Isn't that weird?
                    int outRed = (int) Math.min(
                            (inputRed * .393) + (inputGreen * .769) + (inputBlue * .189), 255);

                    int outGreen = (int) Math.min(
                            (inputRed * .349) + (inputGreen * .686) + (inputBlue * .168), 255);

                    int outBlue = (int) Math.min(
                            (inputRed * .272) + (inputGreen * .534) + (inputBlue * .131), 255);
                    // apply new pixel color to output bitmap
                    sepiaBitmap.setPixel(x, y, Color.argb(alpha, outRed, outGreen, outBlue));
                }
            }
            return sepiaBitmap;
        }

        protected void onPostExecute(Bitmap sepiaBitmap) {
            if (activityWeakReference != null && activityWeakReference.get() != null) {
                Activity activity = activityWeakReference.get();
                if (!activity.isFinishing()) {
                    ImageView imageView = activity.findViewById(R.id.busyui_imageview);
                    imageView.setImageBitmap(sepiaBitmap);
                }
            }
        }
    }
}