package com.qf.dao;

import com.qf.entity.User;

import java.util.List;

/**
 * @author Richard
 * 2020/12/25 15:08
 */
public interface IUserDao {

    int addUser(User user);

    int updateUser(User user);

    int deleteUserById(Integer id);

    List<User> getUserList();

    User getUserById(Integer id);

    User getUserByUsername(String username);
}
