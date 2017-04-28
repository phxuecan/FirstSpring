package cn.putao.open.goods.entity;

import java.io.Serializable;

public class GoodsCity implements Serializable {

    private static final long serialVersionUID = -906977792240321551L;

    // 主键
    private long id;

    // 关联 p_goods_info.pid
    private long gid;

    // 投放城市
    private String city;
    //区域,以,隔开组成字符串
    private String districts;
    
    /**
     * 城市备注
     */
    private String citynote;

    public String getDistricts() {
		return districts;
	}

	public void setDistricts(String districts) {
		this.districts = districts;
	}

	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGid() {
        return gid;
    }

    public void setGid(long gid) {
        this.gid = gid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCitynote() {
        return citynote;
    }

    public void setCitynote(String citynote) {
        this.citynote = citynote;
    }

}
