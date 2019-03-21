package com.mx.amapdemo;

import android.app.Application;
import android.content.Context;

import com.mx.amapdemo.model.location.LocationManager;
import com.mx.amapdemo.utils.AppResourcesUtils;

public class App extends Application {

    private static Context mContext;//上下文


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        AppResourcesUtils.init(mContext);
        LocationManager.get().init(mContext);
    }

    public static Context getmContext() {
        return mContext;
    }

    public static void setContext(Context mContext) {
        App.mContext = mContext;
    }
}
