/**
 * 
 */
package cn.putao.open.goods.entity;

/**
 * @author FelixWu
 * @since 4.4.0, 2016-07-02
 */
public class Point
{
    
    /**
     * 经度
     */
    private double lng;
    
    /**
     * 纬度
     */
    private double lat;
    
    public Point() {
    }
    
    public Point(double lng, double lat) {
        this.lng = lng;
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

}
