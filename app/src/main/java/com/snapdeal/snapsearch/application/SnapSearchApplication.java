package com.snapdeal.snapsearch.application;

import android.app.Application;

import com.squareup.otto.Bus;

/**
 * Created by adarshpandey on 11/22/14.
 */
public class SnapSearchApplication extends Application {
    private static SnapSearchApplication application;

    private Bus bus = new Bus();

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static Bus getEventBus() {
        return application.bus;
    }

}
