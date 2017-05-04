package com.leehq.spring.bean;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by putao_lhq on 17-5-4.
 */
public class RoluUser implements GrantedAuthority
{

    @Override
    public String getAuthority()
    {
        return "USER";
    }
}
