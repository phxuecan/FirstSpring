package com.leehq.spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by putao_lhq on 17-5-4.
 */
@RestController
public class HomeController
{
    @GetMapping("/ldap")
    public String index()
    {
        return "welcome to the home page";
    }
}
