package cn.putao.open;

import cn.putao.open.goods.entity.*;
import cn.putao.open.goods.repo.GoodsInfoMapper;
import cn.putao.open.utils.DistanceUtil;
import cn.putao.open.utils.GeoUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by putao_lhq on 17-4-28.
 */
@RestController
@RequestMapping("/")
public class HomeController
{
    /**
     * 服务范围为全国的标识
     */
    private final static String CITY_STAR = "*";
    /**
     * 服务范围为全国的标识
     */
    public final static String CITY_ALL = "全国";

    /**
     * 服务范围为全市的标识
     */
    private final static String ALL_AREA_MARKER = "全市";

    @Autowired
    private GoodsInfoMapper goodsInfoMapper;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    List<GoodsInfo> home()
    {
        List<GoodsInfo> goodsInfos = goodsInfoMapper.queryAllGoods();
        GeoInfo geoInfo = getGeoInfoByAddress("横岗街道");
        for (int i = 0; i < goodsInfos.size(); i++)
        {
            GoodsInfo info = goodsInfos.get(i);
            info.setCoverage(isInServiceRange(info.getPid(), geoInfo));
        }
        return goodsInfos;
    }

    @RequestMapping(value = "/geo", method = RequestMethod.GET)
    public @ResponseBody
    String geo()
    {
        String url = buidUrl("金银园");
        return sendGet(url);
    }

    @RequestMapping(value = "/coverage", method = RequestMethod.GET)
    public @ResponseBody String coverage(@RequestParam("gid") long gid, @RequestParam("address") String address)
    {
        GeoInfo geoInfo = getGeoInfoByAddress(address);
        boolean inSer = isInServiceRange(gid, geoInfo);
        String in = " 不在 ";
        if (inSer)
        {
            in = " 在 ";
        }
        return "商品: " + gid + in + address + " 服务范围";
    }

    private GeoInfo getGeoInfoByAddress(String address)
    {
        if (address == null || address.equals(""))
        {
            return null;
        }
        String url = buidUrl("金银园");
        String result = sendGet(url);
        JSONObject json = JSON.parseObject(result);
        JSONArray jsonArray = json.getJSONArray("geocodes");
        JSONObject geo = jsonArray.getJSONObject(0);
        GeoInfo info = new GeoInfo();
        info.setArea(geo.getString("district"));
        info.setCity(geo.getString("city"));
        String location = geo.getString("location");
        String[] loc = location.split(",");
        Point point = new Point(Double.parseDouble(loc[0]), Double.parseDouble(loc[1]));
        info.setPoint(point);
        return info;
    }

    private String buidUrl(String area)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("http://restapi.amap.com/v3/geocode/geo?city=深圳&");
        sb.append("address=");
        sb.append(area);
        sb.append("&key=126198a148535ffc1544652f7a631252");
        return sb.toString();
    }

    /**
     * 判断地址是否在商品提供的服务范围内
     *
     * @param gid
     * @param geoInfo
     * @return
     */
    public boolean isInServiceRange(long gid, GeoInfo geoInfo) {
        if (null == geoInfo) {
            return false;
        }
        try {
            Point point = geoInfo.getPoint();
            String city = geoInfo.getCity();
            String area = geoInfo.getArea();
            if (isInCityAndArea(gid, city, area) || isInServiceRadius(gid, point) ||
                    isInServiceGraphRange(gid, point)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("e: " + e);
        }
        return false;
    }

    /**
     * 判断给定的“城市区域”是否和商品所属的“城市区域”匹配
     *
     * @param gid
     * @param cityName
     * @param area
     * @return
     */
    private boolean isInCityAndArea(long gid, String cityName, String area) {
        if (gid < 0) {
            gid = 0 - gid;
        }
        // add by FelixWu 去掉城市名称中，结尾为市的
        if (!isBlank(cityName) && cityName.endsWith("市")) {
            cityName = cityName.substring(0, cityName.length() - 1);
        }
        List<GoodsCity> cityList = goodsInfoMapper.selectCityAndDistByGid(gid,true);
        // 考虑服务范围是全国的商品
        if (cityList != null && cityList.size() > 0) {
            if (cityList.size() == 1) {
                String allCountryIdentification = cityList.get(0).getCity();
                if (CITY_STAR.equals(allCountryIdentification) || CITY_ALL.equals(allCountryIdentification)) {
                    return true;
                }
            }
            for (GoodsCity goodsCity : cityList) {
                if (cityName.equals(goodsCity.getCity())) {
                    String dbArea = goodsCity.getDistricts();
                    if (!isBlank(dbArea) || isBlank(area)) {
                        return true;
                    } else if (ALL_AREA_MARKER.equals(dbArea)) {
                        return true;
                    } else {
                        return dbArea.contains(area);
                    }
                }
            }
        }
        return false;
    }

    /**
     * 判断定位点是否在商品的服务半径内
     *
     * @param gid
     * @param point
     * @return
     */
    private boolean isInServiceRadius(long gid, Point point) {
        List<GoodsServiceRangeDto> rangeDtoList = entities2Dtos(goodsInfoMapper.selectByGid(gid));
        if (rangeDtoList == null) {
            return false;
        }
        for (GoodsServiceRangeDto rangeDto : rangeDtoList) {
            int distance = DistanceUtil.GetDistance(point.getLng(), point.getLat(), rangeDto.getLongitude(),
                    rangeDto.getLatitude());
            // 在半径范围内，则可以下单
            if (distance <= rangeDto.getRadius()) {
                return true;
            }
        }
        return false;
    }

    private static List<GoodsServiceRangeDto> entities2Dtos(List<GoodsServiceRange> entities) {
        if (null == entities || entities.isEmpty()) {
            return null;
        }
        List<GoodsServiceRangeDto> dtos = new ArrayList<GoodsServiceRangeDto>();
        for (GoodsServiceRange entity : entities) {
            GoodsServiceRangeDto dto = entity2Dto(entity);
            dtos.add(dto);
        }
        return dtos;
    }

    private static GoodsServiceRangeDto entity2Dto(GoodsServiceRange entity) {
        GoodsServiceRangeDto dto = new GoodsServiceRangeDto();
        dto.setId(entity.getId());
        dto.setCity(entity.getCity());
        dto.setArea(entity.getArea());
        dto.setPoint(entity.getPoint());
        dto.setLongitude(entity.getLongitude());
        dto.setLatitude(entity.getLatitude());
        dto.setRadius(entity.getRadius());
        return dto;
    }

    /**
     * 判断定位点是否在商品规划的图形范围内
     *
     * @param gid
     * @param point
     * @return
     */
    private boolean isInServiceGraphRange(long gid, Point point) {

        List<GoodsGraphRange> goodsGraphRanges = goodsInfoMapper.selectByGidForGraphRange(gid);
        if (goodsGraphRanges.isEmpty()) {
            return false;
        }
        for (GoodsGraphRange goodsGraphRange : goodsGraphRanges) {
            List<RangesDto> rangeDtos = getPoints(goodsGraphRange);
            for (RangesDto rangesDto : rangeDtos) {
                if (GeoUtil.getInstance().isPointInPolygon(point, rangesDto.getPoints()))
                {
                    return true;
                }
            }

        }
        return false;
    }

    private List<RangesDto> getPoints(GoodsGraphRange goodsGraphRange) {
        if (isBlank(goodsGraphRange.getRanges())) {
            return null;
        }
        JSONObject jsonObject = JSON.parseObject(goodsGraphRange.getRanges());
        List<RangesDto> dtos = new ArrayList<RangesDto>();
        JSONArray rangeArray = jsonObject.getJSONArray("service_area");
        for (int i = 0; i < rangeArray.size(); i++) {
            RangesDto rangesDto = new RangesDto();
            List<Point> list = new ArrayList<Point>();
            JSONObject rangeObj = rangeArray.getJSONObject(i);
            if (null == rangeObj) {
                continue;
            }
            String name = rangeObj.getString("name");
            rangesDto.setName(name);
            JSONArray pointsArray = rangeObj.getJSONArray("points");
            if (null == pointsArray) {
                continue;
            }
            for (int j = 0; j < pointsArray.size(); j++) {
                JSONObject pointsObj = pointsArray.getJSONObject(j);
                double lng = pointsObj.getDouble("lng");
                double lat = pointsObj.getDouble("lat");
                Point dto = new Point(lng, lat);
                list.add(dto);
            }
            rangesDto.setPoints(list);
            dtos.add(rangesDto);
        }
        return dtos;
    }

    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if(cs != null && (strLen = cs.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if(!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public static String sendGet(String url) {
        String result = "";
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
}
