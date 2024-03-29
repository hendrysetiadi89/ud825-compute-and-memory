package com.tokopedia.myapplication.render2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import com.tokopedia.myapplication.R;
import com.tokopedia.myapplication.render1.Droid;

/**
 * Activity to show the custom view, DroidCardsView. Used to demonstrate how clipping a view's
 * drawing area is an effective way of reducing overdraws.
 */
public class DroidCardsActivity extends AppCompatActivity {
    public static final String TAG = "droid-cards-activity";

    // For this sample, we simply hard code the size of the droid image. A real app might prefer
    // to dynamically calculate this value based on the activity's dimensions.
    protected static final float DROID_IMAGE_WIDTH = 420f;

    // The distance between the left edges of two adjacent cards. The cards overlap horizontally.
    protected static final float CARD_SPACING = DROID_IMAGE_WIDTH / 3;

    /**
     * The container for the custom view.
     */
    private RelativeLayout mdroidCardsContainer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_droid_cards);

        // Find the container.
        mdroidCardsContainer = (RelativeLayout) findViewById(R.id.activity_droid_cards_container);

        Droid[] droids = {
                new Droid("Joanna", R.color.joanna_color, R.drawable.joanna),
                new Droid("Shailen", R.color.shailen_color, R.drawable.shailen),
                new Droid("Chris", R.color.chris_color, R.drawable.chris)
        };

        // Create the DroidCardsView object.
        final DroidCardsView droidCardView = new DroidCardsView(
                this,
                droids,
                DROID_IMAGE_WIDTH,
                CARD_SPACING
        );

        droidCardView.setLayoutParams(new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT));

        // Add the view to the container.
        mdroidCardsContainer.addView(droidCardView);
    }
}