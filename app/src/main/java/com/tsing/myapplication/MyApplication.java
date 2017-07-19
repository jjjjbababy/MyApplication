package com.tsing.myapplication;

import android.app.Application;

import com.lzy.okgo.OkGo;

/**
 * Created by ${Tsing} on 2017/7/19.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        OkGo.init(this);
        OkGo.getInstance().setReadTimeOut(OkGo.DEFAULT_MILLISECONDS).debug("OkGo");
    }
}
