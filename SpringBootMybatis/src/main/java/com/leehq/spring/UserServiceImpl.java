package com.leehq.spring;

import com.leehq.spring.mapping.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by putao_lhq on 17-4-24.
 */
@Service
@Primary
public class UserServiceImpl implements IUserService
{
    private UserMapper userMapper;


    @Override
    public User selectByUserName(String name)
    {
        return userMapper.selectByUserName(name);
    }

    @Override
    public void addNewUser(String name, String email)
    {
        userMapper.addNewUser(name, email);
    }

    @Override
    public List<User> findAll()
    {
        return userMapper.findAll();
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper)
    {
        this.userMapper = userMapper;
    }
}
