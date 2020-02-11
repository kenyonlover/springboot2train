package com.kenyon.springboot2train.service;

import com.kenyon.springboot2train.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;

@Service
public class UserService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public int addUser(User user) {
        return jdbcTemplate.update("insert into user (id,name,address) values (?,?,?);", user.getId(), user.getName(), user.getAddress());
    }

    public int deleteUserById(Long id) {
        return jdbcTemplate.update("delete from user where id=?", id);
    }

    public int updateUserById(User user) {
        return jdbcTemplate.update("update user set name=?,address=? where id=?", user.getName(), user.getAddress(),user.getId());
    }

    public List<User> getAllUsers() {
        return jdbcTemplate.query("select * from user", new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                String username = resultSet.getString("name");
                String address = resultSet.getString("address");
                long id = resultSet.getLong("id");
                User user = new User();
                user.setAddress(address);
                user.setName(username);
                user.setId(id);
                return user;
            }
        });
    }

    public List<User> getAllUsers2() {
        return jdbcTemplate.query("select * from user", new BeanPropertyRowMapper<>(User.class));
    }

    public User getUserById(Long id){
        List<User> userList = jdbcTemplate.query("select * from user where id = "+id, new BeanPropertyRowMapper<>(User.class));
        User user = userList != null ? userList.get(0) : null;
        return user;
    }
}