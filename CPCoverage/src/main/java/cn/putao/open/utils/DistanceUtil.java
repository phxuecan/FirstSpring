package cn.putao.open.utils;


public class DistanceUtil
{
    private static final double EARTH_RADIUS = 6378137;
    
    private static double rad(double d)
    {
        return d * Math.PI / 180.0;
    }
    
    /**
     * 根据两点间经纬度坐标，计算两点间距离，返回值单位为米
     * 
     * @param lng1
     * @param lat1
     * @param lng2
     * @param lat2
     * @return
     */
    public static Integer GetDistance(double lng1, double lat1, double lng2, double lat2)
    {
       double radLat1 = rad(lat1);
       double radLat2 = rad(lat2);
       double a = radLat1 - radLat2;
       double b = rad(lng1) - rad(lng2);
       double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) + 
       Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
       distance = distance * EARTH_RADIUS;
       distance = Math.round(distance * 10000) / 10000;
       Integer dis = (int)  Math.ceil(distance);
       return dis;
    }
}
