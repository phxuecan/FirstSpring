package com.leehq.spring;

import java.util.List;

/**
 * Created by putao_lhq on 17-4-24.
 */
public interface IUserService
{
    User selectByUserName(String name);
    void addNewUser(String name, String email);
    List<User> findAll();
}
