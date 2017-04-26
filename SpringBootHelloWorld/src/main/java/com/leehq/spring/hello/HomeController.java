package com.leehq.spring.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by putao_lhq on 17-4-26.
 */
@RestController
@RequestMapping(path = "/")
public class HomeController
{
    @GetMapping("/helloword")
    public @ResponseBody String hello()
    {
        return "您好,欢迎来到spring";
    }
}
