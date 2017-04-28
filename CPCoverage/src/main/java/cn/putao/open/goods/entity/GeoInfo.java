/**
 * 
 */
package cn.putao.open.goods.entity;

/**
 * 用户地理信息
 * 
 * @author FelixWu
 * @since 4.4.0, 2016-07-04 
 */
public class GeoInfo
{

    /**
     * 经纬度定位点
     */
    private Point point;
    
    /**
     * 城市名称，如：深圳
     */
    private String city;
    
    /**
     * 区域，如：南山
     */
    private String area;
    
    public GeoInfo() {
    }
    
    public GeoInfo(Point point, String city, String area) {
        this.point = point;
        this.city = city;
        this.area = area;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
    
}
