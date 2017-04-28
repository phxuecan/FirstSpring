package cn.putao.open.goods.entity;


import java.io.Serializable;

public class GoodsInfo implements Serializable {

    private static final long serialVersionUID = 4258540724969079503L;
    
    private long pid;
    private String name;
    private boolean isCoverage;

    public long getPid()
    {
        return pid;
    }

    public void setPid(long pid)
    {
        this.pid = pid;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public boolean isCoverage()
    {
        return isCoverage;
    }

    public void setCoverage(boolean coverage)
    {
        isCoverage = coverage;
    }
}
