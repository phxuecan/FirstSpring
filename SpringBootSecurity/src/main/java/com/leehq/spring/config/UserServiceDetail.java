package com.leehq.spring.config;

import com.leehq.spring.bean.User;
import com.leehq.spring.bean.UserDetailAdapter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.sql.DataSource;

/**
 * Created by putao_lhq on 17-5-4.
 */
public class UserServiceDetail implements UserDetailsService
{

    private DataSource dataSource;

    public UserServiceDetail(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException
    {
        User user = findUserByUserName(s);

        return new UserDetailAdapter(user);
    }

    private User findUserByUserName(String username)
    {
        String sql = "select * from users where username = ?";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.queryForObject(sql, (resultSet, i) ->
        {
            User user = new User();
            user.setUsername(resultSet.getString(0));
            user.setPassword(resultSet.getString(1));
            return user;
        }, username);
    }
}
