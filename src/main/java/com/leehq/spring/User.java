package com.leehq.spring;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by putao_lhq on 17-4-24.
 */
public class User
{
    /*@Id
    @GeneratedValue(strategy = GenerationType.AUTO)*/
    private Integer id;
    private String name;
    private String email;

    public Integer getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}
