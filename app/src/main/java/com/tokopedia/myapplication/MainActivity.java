/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tokopedia.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.tokopedia.myapplication.computeandmemory.*;
import com.tokopedia.myapplication.render1.ChatumLatinumActivity;
import com.tokopedia.myapplication.render2.DroidCardsActivity;

/*
 Learn reference:
 https://github.com/udacity/ud825-compute-and-memory/compare
 https://github.com/udacity/ud825-render/compare/
 */

/** Just a "Table of Contents" Activity to springboard you into the various exercises.  Seriously,
 * there is NOTHING interesting here.  Why are you still reading?  Why must you continue to hang
 * upon my every word?  WHY???
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewGroup rootView = findViewById(R.id.activity_main_container);

        addButton(ChatumLatinumActivity.class, "Render GPU overdraw (1)", rootView);
        addButton(com.tokopedia.myapplication.renderoptimized1.ChatumLatinumActivity.class, "Render GPU overdraw (1) (optimized)", rootView);

        addButton(DroidCardsActivity.class, "Render GPU overdraw (2)", rootView);
        addButton(com.tokopedia.myapplication.renderoptimized2.DroidCardsActivity.class, "Render GPU overdraw (2) (optimized)", rootView);

        addButton(CachingActivity.class, "Batching and caching", rootView);
        addButton(BusyUIThreadActivity.class, "Slow onClick handler", rootView);
        addButton(DataStructuresActivity.class, "Data structure selection", rootView);

        addButton(MemoryLeakActivity.class, "Memory leaks", rootView);
        addButton(MemoryLeakOptimizedActivity.class, "Memory leaks (optimized)", rootView);
        addButton(MemoryChurnActivity.class,"Memory churn", rootView);
    }

    public void addButton(final Class destination, String description, ViewGroup parent) {
        Button button = new Button(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent problemIntent = new Intent(MainActivity.this, destination);
                startActivity(problemIntent);
            }
        });

        button.setText(description);
        parent.addView(button);
    }
}
