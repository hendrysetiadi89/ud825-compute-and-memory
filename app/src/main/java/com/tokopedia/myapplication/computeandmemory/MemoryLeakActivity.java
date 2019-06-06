package com.tokopedia.myapplication.computeandmemory;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.tokopedia.myapplication.R;

/**
 * The most completely unremarkable Activity code you'll probably ever see.  How could this hide
 * a memory leak?  HOW?
 */
public class MemoryLeakActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_leak);

        View customView = findViewById(R.id.customView);
    }

}