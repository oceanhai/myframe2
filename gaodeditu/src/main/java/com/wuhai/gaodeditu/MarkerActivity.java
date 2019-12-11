package com.wuhai.gaodeditu;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.route.BusPath;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkPath;
import com.amap.api.services.route.WalkRouteResult;
import com.wuhai.gaodeditu.databinding.AcMarkerBinding;

import java.net.URISyntaxException;

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/3 11:45
 *
 * 描述：绘制点标记
 */
public class MarkerActivity extends AppCompatActivity {

    private static final String TAG = "MarkerActivity";

    private AcMarkerBinding binding;

    private MapView mMapView;//容器
    private AMap aMap;//控制类
    private GeocodeSearch geocoderSearch;//地理编码与逆地理编码类

    private String mAddr;//目标地址
    private LatLng mCurLocationLatLng;//当前位置坐标
    private LatLng mTargetLocationLatLng;//目标位置坐标

    private RouteSearch routeSearch;//路径规划搜索

    /**
     * @param context
     */
    public static void startActivity(Context context, String addr) {
        Intent intent = new Intent();
        intent.putExtra("addr", addr);
        intent.setClass(context, MarkerActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_marker);
        binding = DataBindingUtil.setContentView(this, R.layout.ac_marker);
        parseIntent();
        init(savedInstanceState);
        setListener();
    }

    private void parseIntent() {
        Intent intent = getIntent();
        if(intent != null){
            mAddr = intent.getStringExtra("addr");
        }
    }

    private void init(Bundle savedInstanceState) {

        binding.addr.setText(mAddr);

        mMapView = binding.map;
        mMapView.onCreate(savedInstanceState);// 此方法必须重写
        aMap = mMapView.getMap();

        //当前位置定位
        location();
        //根据目标地址返回 经纬度
        search(mAddr);
    }

    /**
     * 根据目标地址返回 经纬度
     */
    private void search(String addr) {
        geocoderSearch = new GeocodeSearch(this);
        geocoderSearch.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {
            @Override
            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
                //根据给定的经纬度和最大结果数返回逆地理编码的结果列表
            }

            @Override
            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
                //根据给定的地理名称和查询城市，返回地理编码的结果列表
                Log.e(TAG,"onGeocodeSearched code="+i);
                if (i==1000){
                    if (geocodeResult!=null && geocodeResult.getGeocodeAddressList()!=null &&
                            geocodeResult.getGeocodeAddressList().size()>0){
                        GeocodeAddress geocodeAddress = geocodeResult.getGeocodeAddressList().get(0);
                        double latitude = geocodeAddress.getLatLonPoint().getLatitude();//纬度
                        double longititude = geocodeAddress.getLatLonPoint().getLongitude();//经度
                        String adcode= geocodeAddress.getAdcode();//区域编码
                        Log.e(TAG,"地理编码:"+geocodeAddress.getAdcode());
                        Log.e(TAG,"纬度:"+latitude);
                        Log.e(TAG,"经度:"+longititude);
                        Log.e(TAG,"区域编码 adcode:"+adcode);

                        LatLng latLng = new LatLng(latitude,longititude);
                        mTargetLocationLatLng = latLng;
                        calculatedDistance(mCurLocationLatLng, mTargetLocationLatLng);
                        calculatedTime();
                        //目标地址marker
                        setMarker(latLng);
                        //目标地设为地图中心点，地图放大
                        setCore(latLng);

                    }else {
                        Toast.makeText(MarkerActivity.this,"地名出错",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // name表示地址，第二个参数表示查询城市，中文或者中文全拼，citycode、adcode
//        GeocodeQuery query = new GeocodeQuery(addr, "010");
        GeocodeQuery query = new GeocodeQuery(addr, "");

        geocoderSearch.getFromLocationNameAsyn(query);
    }

    /**
     * 地图中心点
     * Constants.ZHONGGUANCUN 北京中关村经纬度  定值
     * 也就是说，我要先得到所要到地点的 经纬度     求得值
     */
    private void setCore(LatLng latLng) {
        changeCamera(
                CameraUpdateFactory.newCameraPosition(new CameraPosition(
                        latLng, 13, 0, 0)));
//        aMap.clear();
//        aMap.addMarker(new MarkerOptions().position(Constants.ZHONGGUANCUN)
//                .icon(BitmapDescriptorFactory
//                        .defaultMarker(BitmapDescriptorFactory.HUE_RED)));
    }

    /**
     * 根据动画按钮状态，调用函数animateCamera或moveCamera来改变可视区域
     */
    private void changeCamera(CameraUpdate update) {

        aMap.moveCamera(update);

    }

    /**
     * 绘制点标记
     */
    private void setMarker(LatLng latLng) {

        Log.e(TAG,"最大缩放级别:"+aMap.getMaxZoomLevel()+
                ",最小缩放级别"+aMap.getMinZoomLevel()+
                ",当前缩放级别下，地图上1像素点对应的长度，单位米"+aMap.getScalePerPixel());

//        LatLng latLng = Constants.ZHONGGUANCUN;
        Marker marker = aMap.addMarker(
                new MarkerOptions()
                        .position(latLng)
//                        .title("中关村")
                        .snippet("DefaultMarker"));
    }

    private void setListener() {

    }

    /**
     * 定位蓝点
     */
    private void location() {
        MyLocationStyle myLocationStyle;
        //初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);
        //连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle = new MyLocationStyle();
        //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.interval(2000);
        //只定位一次。TODO 不然地图拖动，老是移动回定位的地方
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_SHOW);

        //设置定位蓝点的Style
        aMap.setMyLocationStyle(myLocationStyle);
        //aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。

        aMap.setOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                Log.e(TAG,"location:"+location.toString());
                mCurLocationLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                calculatedDistance(mCurLocationLatLng,mTargetLocationLatLng);
                calculatedTime();
            }
        });
    }

    /**
     * 计算距离
     */
    private void calculatedDistance(LatLng curLocationLatLng, LatLng targetLocationLatLng){
        if(curLocationLatLng == null || targetLocationLatLng == null){
            return;
        }
        float distance = AMapUtils.calculateLineDistance(curLocationLatLng, targetLocationLatLng);

        binding.distance.setText("距离 "+StringUtil.getDecimalFormat(null,distance/1000)+"km");
    }

    /**
     * 计算
     * 出租车
     * 公交
     * 步行
     * 三种方式的预计时间
     */
    private void calculatedTime(){

        if(mCurLocationLatLng == null || mTargetLocationLatLng == null){
            return;
        }

        routeSearch = new RouteSearch(this);
        routeSearch.setRouteSearchListener(new RouteSearch.OnRouteSearchListener() {
            @Override
            public void onBusRouteSearched(BusRouteResult busRouteResult, int errorCode) {
                if (errorCode == AMapException.CODE_AMAP_SUCCESS) {
                    if (busRouteResult != null && busRouteResult.getPaths() != null) {
                        if (busRouteResult.getPaths().size() > 0) {
                            final BusPath busPath = busRouteResult.getPaths()
                                    .get(0);
                            if(busPath == null) {
                                return;
                            }
                            int dis = (int) busPath.getDistance();//距离
                            int dur = (int) busPath.getDuration();//时间 单位秒
                            binding.busTime.setText((dur/60)+"分钟(公交)");
                        } else if (busRouteResult != null && busRouteResult.getPaths() == null) {
                            Log.e(TAG,"onWalkRouteSearched 对不起，没有搜索到相关数据！");
                        }
                    } else {
                        Log.e(TAG,"onWalkRouteSearched 对不起，没有搜索到相关数据！");
                    }
                } else {
                    Log.e(TAG,"onWalkRouteSearched errorCode="+errorCode);
                }
            }

            @Override
            public void onDriveRouteSearched(DriveRouteResult driveRouteResult, int errorCode) {
                if (errorCode == AMapException.CODE_AMAP_SUCCESS) {
                    if (driveRouteResult != null && driveRouteResult.getPaths() != null) {
                        if (driveRouteResult.getPaths().size() > 0) {
                            final DrivePath drivePath = driveRouteResult.getPaths()
                                    .get(0);
                            if(drivePath == null) {
                                return;
                            }
                            int dis = (int) drivePath.getDistance();//距离
                            int dur = (int) drivePath.getDuration();//时间 单位秒
                            binding.taxiTime.setText((dur/60)+"分钟(出租车)");
                        } else if (driveRouteResult != null && driveRouteResult.getPaths() == null) {
                            Log.e(TAG,"onWalkRouteSearched 对不起，没有搜索到相关数据！");
                        }
                    } else {
                        Log.e(TAG,"onWalkRouteSearched 对不起，没有搜索到相关数据！");
                    }
                } else {
                    Log.e(TAG,"onWalkRouteSearched errorCode="+errorCode);
                }
            }

            @Override
            public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int errorCode) {
                if (errorCode == AMapException.CODE_AMAP_SUCCESS) {
                    if (walkRouteResult != null && walkRouteResult.getPaths() != null) {
                        if (walkRouteResult.getPaths().size() > 0) {
                            final WalkPath walkPath = walkRouteResult.getPaths()
                                    .get(0);
                            if(walkPath == null) {
                                return;
                            }
                            int dis = (int) walkPath.getDistance();//距离
                            int dur = (int) walkPath.getDuration();//时间 单位秒
                            binding.walkTime.setText((dur/60)+"分钟(步行)");
                        } else if (walkRouteResult != null && walkRouteResult.getPaths() == null) {
                            Log.e(TAG,"onWalkRouteSearched 对不起，没有搜索到相关数据！");
                        }
                    } else {
                        Log.e(TAG,"onWalkRouteSearched 对不起，没有搜索到相关数据！");
                    }
                } else {
                    Log.e(TAG,"onWalkRouteSearched errorCode="+errorCode);
                }
            }

            @Override
            public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {

            }
        });

        LatLonPoint mStartPoint = new LatLonPoint(mCurLocationLatLng.latitude, mCurLocationLatLng.longitude);//起点
        LatLonPoint mEndPoint = new LatLonPoint(mTargetLocationLatLng.latitude, mTargetLocationLatLng.longitude);//终点

        RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(mStartPoint, mEndPoint);
        //初始化query对象，fromAndTo是包含起终点信息，walkMode是步行路径规划的模式
        RouteSearch.WalkRouteQuery query = new RouteSearch.WalkRouteQuery(fromAndTo, RouteSearch.WalkDefault);
        // 异步路径规划步行模式查询
        routeSearch.calculateWalkRouteAsyn(query);
        //fromAndTo - 路径的起终点;mode - 计算路径的模式;city - 城市名称/城市区号/电话区号;nightFlag - 是否计算夜班车，默认为不计算。
        RouteSearch.BusRouteQuery busRouteQuery = new RouteSearch.BusRouteQuery(fromAndTo, RouteSearch.BUS_DEFAULT,"010",0);
        routeSearch.calculateBusRouteAsyn(busRouteQuery);
        //fromAndTo - 路径的起点终点;mode - 计算路径的模式;passedByPoints - 途经点，可选;avoidpolygons - 避让区域，可选;avoidRoad - 避让道路名称
        RouteSearch.DriveRouteQuery driveRouteQuery =
                new RouteSearch.DriveRouteQuery(fromAndTo, RouteSearch.DrivingDefault,null,null,null);
        routeSearch.calculateDriveRouteAsyn(driveRouteQuery);
    }

    /**
     * btn点击事件  导航   就一个点击
     * @param view
     */
    public void onClick(View view){
//        Toast.makeText(this,"我被点击了",Toast.LENGTH_LONG).show();

        try {
            //传目的地坐标，直接搜索
            Intent intent = Intent.getIntent("androidamap://route?sourceApplication=softname&sname=我的位置&dlat=" +
                    mTargetLocationLatLng.latitude + "&dlon=" + mTargetLocationLatLng.longitude + "&dname=" + mAddr + "&dev=0&m=0&t=1");
            //不传目的地坐标，进高德还得让你选择目的地
//            Intent intent = Intent.getIntent("androidamap://route?sourceApplication=softname&sname=我的位置&dname=" +
//                    mAddr + "&dev=0&m=0&t=1");
            startActivity(intent);
            //启动调用
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }
}
