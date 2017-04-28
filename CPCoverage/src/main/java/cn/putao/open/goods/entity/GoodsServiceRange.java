/**
 * 
 */
package cn.putao.open.goods.entity;

import java.util.Date;

/**
 * 商品服务范围实体类
 * 
 * @author FelixWu
 * @since 4.2.0, 2016-05-06
 */
public class GoodsServiceRange
{
    
    private Long id;
    
    /**
     * 商品id
     */
    private Long gid;
    
    /**
     * “中心点”所属的城市名称
     */
    private String city;
    
    /**
     * “中心点”所属的区县名称
     */
    private String area;
    
    /**
     * “中心点”名称
     */
    private String point;
    
    /**
     * “中心点”的经度
     */
    private double longitude;
    
    /**
     * “中心点”的纬度
     */
    private double latitude;
    
    /**
     * 半径长度,单位为：米
     */
    private Integer radius;
    
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 最后修改时间
     */
    private Date modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
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
    
    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Integer getRadius() {
        return radius;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
    
}
