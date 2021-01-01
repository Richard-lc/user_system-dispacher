package com.qf.service;

import com.qf.entity.User;

import java.util.List;

/**
 * @author Richard
 * 2020/12/25 15:20
 */
public interface IUserService {

    int addUser(User user);

    int updateUser(User user);

    int deleteUserById(Integer id);

    List<User> getUserList();

    User getUserById(Integer id);

    User getUserByUsername(String username);
}
