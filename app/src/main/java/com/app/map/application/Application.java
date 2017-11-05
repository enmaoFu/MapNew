package com.app.map.application;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by mac on 17/11/4.
 * @author enmaoFu
 */

public class Application extends android.app.Application{

    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(getApplicationContext());
    }
}
