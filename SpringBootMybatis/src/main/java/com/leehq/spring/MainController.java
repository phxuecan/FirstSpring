package com.leehq.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by putao_lhq on 17-4-24.
 */
@Controller
@RequestMapping(path = "/demo")
public class MainController
{
    //自动注入mybatis UserMapping
    @Autowired
    private IUserService userService;

    /*@Autowired
    private UserMapper userMapper;*/
    //自动注入
    /*@Autowired
    private UserRepository userRepository;*/

    //@ResponseBody 注解表明,该请求返回的就是response,而不是view的名称
    @GetMapping(path = "/add")
    public @ResponseBody
    String addNewUser(@RequestParam("name") String name, @RequestParam("email") String email)
    {
        /*User user = new User();
        user.setName(name);
        user.setEmail(email);
        userRepository.save(user);*/
        userService.addNewUser(name, email);
        //userMapper.addNewUser(name, email);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<User> findAll()
    {
        return userService.findAll();//userMapper.findAll(); //userRepository.findAll();
    }

    @GetMapping(path = "/query")
    public @ResponseBody Object query(@RequestParam("name") String name)
    {
        if (StringUtils.isEmpty(name))
        {
            return "name 参数不能为空";
        }
        return userService.selectByUserName(name);
    }
}
