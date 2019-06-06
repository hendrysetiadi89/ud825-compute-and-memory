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
package com.tokopedia.myapplication

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.widget.Button

import java.util.Arrays
import java.util.Random


class MemoryChurnActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memory_churn)

        val theButtonThatDoesArrayStuff = findViewById<View>(R.id.memchurn_do_array_stuff) as Button
        theButtonThatDoesArrayStuff.text = "Do interesting things with Arrays."

        val theButtonThatDoesArrayStuff2 = findViewById<View>(R.id.memchurn_do_array_stuff2) as Button
        theButtonThatDoesArrayStuff2.text = "Do interesting things with Arrays (optimized)"

        theButtonThatDoesArrayStuff.setOnClickListener {
            // It's not.
            imPrettySureSortingIsFree()
        }

        theButtonThatDoesArrayStuff2.setOnClickListener { imPrettySureSortingIsFree2() }

        // Blah blah productivity YARR
        val webView = findViewById<View>(R.id.webview) as WebView
        webView.settings.useWideViewPort = true
        webView.settings.loadWithOverviewMode = true
        webView.loadUrl("file:///android_asset/shiver_me_timbers.gif")
    }

    /**
     * Sorts and prints every row of a 2D array, one element at a time.
     */
    fun imPrettySureSortingIsFree() {
        // Throw together a nice, big 2D array of random numbers.
        val dimension = 300
        val lotsOfInts = Array(dimension) { IntArray(dimension) }
        val randomGenerator = Random()
        for (i in lotsOfInts.indices) {
            for (j in 0 until lotsOfInts[i].size) {
                lotsOfInts[i][j] = randomGenerator.nextInt()
            }
        }

        // Now go through and dump the sorted version of each row to output!
        for (i in lotsOfInts.indices) {
            var rowAsStr = ""
            for (j in 0 until lotsOfInts[i].size) {
                // Clearly, the only reasonable way to construct a string is one character at a
                // time, with lots and lots of convenient concatenation.
                rowAsStr += getSorted(lotsOfInts[i])[j]
                if (j < lotsOfInts[i].size - 1) {
                    rowAsStr += ", "
                }
            }
            Log.i("CachingActivityExercise", "Row $i: $rowAsStr")
        }
    }

    /**
     * Helper method, returns the sorted copy of an array.
     * @param input the unsorted array
     * @return the sorted array
     */
    fun getSorted(input: IntArray): IntArray {
        val clone = input.clone()
        Arrays.sort(clone)
        return clone
    }

    fun imPrettySureSortingIsFree2() {
        // Throw together a nice, big 2D array of random numbers.
        val dimension = 300
        val lotsOfInts = Array(dimension) { IntArray(dimension) }
        val randomGenerator = Random()
        for (i in lotsOfInts.indices) {
            for (j in 0 until lotsOfInts[i].size) {
                lotsOfInts[i][j] = randomGenerator.nextInt()
            }
        }

        // Now go through and dump the sorted version of each row to output! This time, use a
        // StringBuilder object so that we can construct one String per row, instead of wasting
        // String objects with ridiculous concatenation that never ends.
        // You may notice that the dancing pirate still seems to have lost the beat here. You would
        // be correct. What else could you do now to like that pirate keep his groove? What have you
        // learned?
        val sb = StringBuilder()
        var rowAsStr = ""
        for (i in lotsOfInts.indices) {
            sb.delete(0, rowAsStr.length) // clear the previous row
            for (j in 0 until lotsOfInts[i].size) {
                sb.append(getSorted(lotsOfInts[i])[j])
                if (j < lotsOfInts[i].size - 1) {
                    sb.append(", ")
                }
            }
            rowAsStr = sb.toString()
            Log.i("CachingActivityExercise", "Row $i: $rowAsStr")
        }
    }
}