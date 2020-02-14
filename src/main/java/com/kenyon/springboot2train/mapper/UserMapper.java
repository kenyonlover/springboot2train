package com.kenyon.springboot2train.mapper;

import com.kenyon.springboot2train.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

//@Mapper
public interface UserMapper {
    @Select("select * from user")
    List<User> getAllUsers();

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "u"),
            @Result(property = "address", column = "a")
    })
    @Select("select name as u,address as a,id as id from user where id=#{id}")
    User getUserById(Long id);

    @Select("select * from user where name like concat('%',#{name},'%')")
    List<User> getUsersByName(String name);

    //@SelectKey 注解可以实现主键回填的功能，即当数据插入成功后，插入成功的数据 id 会赋值到 user 对象的id 属性上。
    @Insert({"insert into user(name,address) values(#{name},#{address})"})
    @SelectKey(statement = "select last_insert_id()", keyProperty = "id", before = false, resultType = Long.class, statementType = StatementType.PREPARED)
    Long addUser(User user);

    @Update("update user set name=#{name},address=#{address} where id=#{id}")
    Integer updateUserById(User user);

    @Delete("delete from user where id=#{id}")
    Integer deleteUserById(Integer id);
}