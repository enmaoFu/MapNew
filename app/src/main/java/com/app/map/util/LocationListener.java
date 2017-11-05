package com.app.map.util;

import com.baidu.location.BDLocation;

/**
 * 定位状态接口
 * @date 2017/11/04
 * @author enmaoFu
 */
public interface LocationListener {

    /**
     * 定位成功
     * @param lat
     * @param lon
     * @param bdLocation
     */
    void onLocationSuccess(double lat, double lon, BDLocation bdLocation);

    /**
     * 定位失败
     */
    void onLocationFailure();

}
