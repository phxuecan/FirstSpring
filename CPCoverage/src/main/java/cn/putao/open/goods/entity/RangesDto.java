package cn.putao.open.goods.entity;

import java.util.List;

public class RangesDto
{

    private List<Point> points;
    
    private String name;

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
