package com.tokopedia.myapplication.computeandmemory

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.widget.Button
import com.tokopedia.myapplication.R

import java.util.Arrays
import java.util.HashMap
import java.util.Random


class DataStructuresActivity : Activity() {

    object SampleData {
        var coolestRandomNumbers = arrayOfNulls<Int>(3000)
        var coolestRandomNumbers2 = HashMap<Int, Int>()

        init {
            val randomGenerator = Random()
            for (i in 0..2999) {
                coolestRandomNumbers[i] = randomGenerator.nextInt()
                coolestRandomNumbers2[randomGenerator.nextInt()] = i
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_structures)

        val dumpButton = findViewById<Button>(R.id.ds_button_dostuff)
        dumpButton.text = "Dump popular numbers"

        val dumpButton2 = findViewById<Button>(R.id.ds_button_dostuff2)
        dumpButton2.text = "Dump popular numbers (optimized)"

        dumpButton.setOnClickListener { dumpPopularRandomNumbersByRank() }
        dumpButton2.setOnClickListener { dumpPopularRandomNumbersByRank2() }

        // It's much easier to see how your decisions affect framerate when there's something
        // changing on screen.  For entirely serious, educational purposes, a dancing pirate
        // will be included with this exercise.
        val webView = findViewById<WebView>(R.id.webview)
        webView.settings.useWideViewPort = true
        webView.settings.loadWithOverviewMode = true
        webView.loadUrl("file:///android_asset/shiver_me_timbers.gif")

    }

    /**
     * Using the pre-formed array of random numbers ordered by popularity, prints out an ordered
     * list of the random number + rank in the form "(RandomNumber): #(Rank)".
     */
    fun dumpPopularRandomNumbersByRank() {
        // First we need a sorted list of the numbers to iterate through.
        val sortedNumbers = SampleData.coolestRandomNumbers.clone()
        Arrays.sort(sortedNumbers)

        // Great!  Now because we have no rank lookup in the population-sorted array,
        // take the random number in sorted order, and find its index in the array
        // that's sorted by popularity.  The index is the rank, so report that.  Easy and efficient!
        // Except that it's... you know... It's not.
        for (i in sortedNumbers.indices) {
            val currentNumber = sortedNumbers[i]
            for (j in SampleData.coolestRandomNumbers.indices) {
                if (currentNumber!!.compareTo(SampleData.coolestRandomNumbers[j]!!) == 0) {
                    Log.i("Popularity Dump", "$currentNumber: #$j")
                }
            }
        }
    }

    fun dumpPopularRandomNumbersByRank2() {
        // Make a copy so that we don't accidentally shatter our data structure.
        val rankedNumbers = HashMap<Int, Int>()
        rankedNumbers.putAll(SampleData.coolestRandomNumbers2)
        // Then, we need a sorted version of the numbers to iterate through.
        var sortedNumbers = arrayOf<Int>()
        sortedNumbers = rankedNumbers.keys.toTypedArray()
        Arrays.sort(sortedNumbers)

        var number: Int?
        for (i in sortedNumbers.indices) {
            number = sortedNumbers[i]
            Log.i("Popularity Dump", number.toString() + ": #" + rankedNumbers[number])
        }
    }
}