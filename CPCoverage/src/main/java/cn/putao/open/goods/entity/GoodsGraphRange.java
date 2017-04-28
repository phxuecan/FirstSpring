/**
 * 
 */
package cn.putao.open.goods.entity;

import java.util.Date;

/**
 * 商品服务范围（不规则的图形）实体类
 * 
 * @author FelixWu
 * @since 4.3.1, 2016-06-29
 */
public class GoodsGraphRange
{
    
    private Long id;
    
    /**
     * 商品id
     */
    private Long gid;
    
    /**
     * 该图形主要属于的城市
     */
    private String city;
    
    /**
     * “中心点”经纬度集合，JSON格式
     */
    private String ranges;
        
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

    public String getRanges() {
        return ranges;
    }

    public void setRanges(String ranges) {
        this.ranges = ranges;
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
