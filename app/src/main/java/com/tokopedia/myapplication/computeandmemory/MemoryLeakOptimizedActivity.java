package com.tokopedia.myapplication.computeandmemory;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.tokopedia.myapplication.R;

public class MemoryLeakOptimizedActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_leak);

        View customView = findViewById(R.id.customView);
    }

    @Override
    protected void onStop() {
        super.onStop();
        ListenerCollector.clearListener();
    }
}