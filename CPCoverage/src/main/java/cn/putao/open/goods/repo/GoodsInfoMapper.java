package cn.putao.open.goods.repo;


import cn.putao.open.goods.entity.GoodsCity;
import cn.putao.open.goods.entity.GoodsGraphRange;
import cn.putao.open.goods.entity.GoodsInfo;
import cn.putao.open.goods.entity.GoodsServiceRange;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsInfoMapper
{
    List<GoodsInfo> queryAllGoods();

    /**
     * 根据商品id获取服务城市以及区域
     * @param gid
     * @param isNeedFilterRetainCity 是否需要过滤保留区域
     * @return
     */
    List<GoodsCity> selectCityAndDistByGid(@Param("gid") long gid, boolean isNeedFilterRetainCity);

    List<GoodsServiceRange> selectByGid(@Param("gid")long gid);

    List<GoodsGraphRange> selectByGidForGraphRange(@Param("gid")long gid);
}