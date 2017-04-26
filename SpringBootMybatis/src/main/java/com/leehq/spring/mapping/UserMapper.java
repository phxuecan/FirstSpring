package spring.mapping;

import com.leehq.spring.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by putao_lhq on 17-4-25.
 */
@Mapper
public interface UserMapper
{
    /*@Select("select * from user where name = #{name}")
    User selectByUserName(@Param("name") String name);
    @Insert("insert into user values (#{name}, #{email})")
    void addNewUser(@Param("name") String name, @Param("email") String email);
    @Select("select * from user")
    Iterable<User> findAll();*/

    User selectByUserName(@Param("name") String name);
    void addNewUser(@Param("name") String name, @Param("email") String email);
    List<User> findAll();
}
