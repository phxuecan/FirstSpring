package cn.putao.open.utils;


import cn.putao.open.goods.entity.Point;

import java.util.List;

public class GeoUtil
{

    private static GeoUtil geoUtil = new GeoUtil();

    private GeoUtil() {
    }

    public static GeoUtil getInstance() {
        return geoUtil;
    }

    /**
     * 判断点是否在多边形内
     * 
     * @param point
     *            目标点
     * @param polygon
     *            多边形顶点集合
     * @return true-在多边形内（包括边框上） false-在多边形外
     */
    public boolean isPointInPolygon(Point point, List<Point> polygons) {

        int N = polygons.size();

        boolean boundOrVertex = true; // 如果点位于多边形的顶点或边上，也算做点在多边形内，直接返回true
        int intersectCount = 0;// cross points count of x
        double precision = 2e-10; // 浮点类型计算时候与0比较时候的容差
        Point p1, p2;// neighbour bound vertices
        Point p = point; // 测试点

        p1 = polygons.get(0);// left vertex
        for (int i = 1; i <= N; ++i) {// check all rays
            if (p.equals(p1)) {
                return boundOrVertex;// p is an vertex
            }

            p2 = polygons.get(i % N);// right vertex
            if (p.getLat() < Math.min(p1.getLat(), p2.getLat()) || p.getLat() > Math.max(p1.getLat(), p2.getLat())) {// ray
                                                                                                                     // is
                                                                                                                     // outside
                                                                                                                     // of
                                                                                                                     // our
                                                                                                                     // interests
                p1 = p2;
                continue;// next ray left point
            }

            if (p.getLat() > Math.min(p1.getLat(), p2.getLat()) && p.getLat() < Math.max(p1.getLat(), p2.getLat())) {// ray
                                                                                                                     // is
                                                                                                                     // crossing
                                                                                                                     // over
                                                                                                                     // by
                                                                                                                     // the
                                                                                                                     // algorithm
                                                                                                                     // (common
                                                                                                                     // part
                                                                                                                     // of)
                if (p.getLng() <= Math.max(p1.getLng(), p2.getLng())) {// x is
                                                                       // before
                                                                       // of ray
                    if (p1.getLat() == p2.getLat() && p.getLng() >= Math.min(p1.getLng(), p2.getLng())) {// overlies
                                                                                                         // on
                                                                                                         // a
                                                                                                         // horizontal
                                                                                                         // ray
                        return boundOrVertex;
                    }

                    if (p1.getLng() == p2.getLng()) {// ray is vertical
                        if (p1.getLng() == p.getLng()) {// overlies on a
                                                        // vertical ray
                            return boundOrVertex;
                        } else {// before ray
                            ++intersectCount;
                        }
                    } else {// cross point on the left side
                        double xinters = (p.getLat() - p1.getLat()) * (p2.getLng() - p1.getLng())
                                / (p2.getLat() - p1.getLat()) + p1.getLng();// cross
                                                                            // point
                                                                            // of
                                                                            // lng
                        if (Math.abs(p.getLng() - xinters) < precision) {// overlies
                                                                         // on a
                                                                         // ray
                            return boundOrVertex;
                        }

                        if (p.getLng() < xinters) {// before ray
                            ++intersectCount;
                        }
                    }
                }
            } else {// special case when ray is crossing through the vertex
                if (p.getLat() == p2.getLat() && p.getLng() <= p2.getLng()) {// p
                                                                             // crossing
                                                                             // over
                                                                             // p2
                    Point p3 = polygons.get((i + 1) % N); // next vertex
                    if (p.getLat() >= Math.min(p1.getLat(), p3.getLat())
                            && p.getLat() <= Math.max(p1.getLat(), p3.getLat())) {// p.lat
                                                                                  // lies
                                                                                  // between
                                                                                  // p1.lat
                                                                                  // &
                                                                                  // p3.lat
                        ++intersectCount;
                    } else {
                        intersectCount += 2;
                    }
                }
            }
            p1 = p2;// next ray left point
        }

        if (intersectCount % 2 == 0) {// 偶数在多边形外
            return false;
        } else { // 奇数在多边形内
            return true;
        }
    }

}
