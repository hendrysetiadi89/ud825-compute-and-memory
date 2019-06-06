package com.tokopedia.myapplication;

import android.view.View;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * The ListenerCollector acts as runtime storage for all the view/listener relationships
 * among the MyCustomView instances.
 */
public class ListenerCollector {
    // A common case is to want to store all the listeners for a particular type of view
    // somewhere.  Harmless AND convenient.  Or... is it? o_0
    static private HashMap<View, MyCustomView.MyListener> sListeners = new HashMap<View, MyCustomView.MyListener>();

    public void setListener(View view, MyCustomView.MyListener listener) {
        sListeners.put(view, listener);
    }

    public static void clearListener(){
        sListeners.clear();
    }
}