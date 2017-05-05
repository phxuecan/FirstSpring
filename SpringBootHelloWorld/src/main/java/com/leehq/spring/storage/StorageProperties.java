package com.leehq.spring.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by putao_lhq on 17-5-5.
 */
@ConfigurationProperties("storage")
public class StorageProperties
{
    /**
     * 存储文件的目录
     */
    private String location = "upload-dir";

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }
}
