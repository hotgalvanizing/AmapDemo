package com.mx.amapdemo;

import android.app.Application;
import android.content.Context;

public class App extends Application {

    private static Context mContext;//上下文


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getmContext() {
        return mContext;
    }

    public static void setContext(Context mContext) {
        App.mContext = mContext;
    }
}
