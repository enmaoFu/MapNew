package com.app.map.util;

import android.content.Context;
import android.util.Log;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

/**
 * 定位工具类
 * @date 2017/11/04
 * @author enmaoFu
 */
public class LocationUtil extends BDAbstractLocationListener{

    /**
     * 定位成功失败的接口
     */
    private LocationListener mLocationListener;
    /**
     * 定位的客户端
     */
    private LocationClient mLocationClient;
    /**
     * 定位SDK各配置参数
     */
    private LocationClientOption mLocationClientOption;

    /**
     * 定位成功状态码
     */
    private static final int LOCATION_CODE = 161;

    /**
     * 初始化配置定位各项参数
     * @param context
     * @param mLocationListener
     */
    public void initLocationParameter(Context context, LocationListener mLocationListener){
        //声明LocationClient类
        mLocationClient = new LocationClient(context);
        //注册监听函数
        mLocationClient.registerLocationListener(this);
        //初始化定位参数
        mLocationClientOption = new LocationClientOption();
        //设置是否需要地址信息
        mLocationClientOption.setIsNeedAddress(true);
        //高精度模式
        mLocationClientOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //返回坐标类型
        mLocationClientOption.setCoorType("bd09ll");
        //启用GPS
        mLocationClientOption.setOpenGps(true);
        //设置是否在stop的时候杀死这个进程，为了APP的流畅性以及生态建议杀死，此次为false，true为不杀死
        mLocationClientOption.setIgnoreKillProcess(false);
        //设置定位参数
        mLocationClient.setLocOption(mLocationClientOption);
        this.mLocationListener = mLocationListener;
    }

    /**
     * 此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
     * @param bdLocation
     */
    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        if(bdLocation != null){
            Log.v("print","工具类定位Code==" + bdLocation.getLocType() + "lat==" + bdLocation.getLatitude() + "lon==" + bdLocation.getLongitude());
            if(bdLocation.getLocType() == LOCATION_CODE){
                if(mLocationListener != null){
                    mLocationListener.onLocationSuccess(bdLocation.getLatitude(),bdLocation.getLongitude(),bdLocation);
                }
            }else{
                mLocationListener.onLocationFailure();
            }
        }else{
            mLocationListener.onLocationFailure();
        }

    }

    /**
     * 启动定位
     */
    public void startLocation(){
        mLocationClient.start();
    }

    /**
     * 停止定位
     */
    public void stopLocation(){
        mLocationClient.stop();
    }

}
