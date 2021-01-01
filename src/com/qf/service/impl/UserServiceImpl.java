package com.qf.service.impl;

import com.qf.dao.IUserDao;
import com.qf.dao.impl.UserDaoImpl;
import com.qf.entity.User;
import com.qf.service.IUserService;

import java.util.List;

/**
 * @author Richard
 * 2020/12/25 15:22
 */
public class UserServiceImpl implements IUserService {

    IUserDao userDao = new UserDaoImpl();

    @Override
    public int addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public int deleteUserById(Integer id) {
        return userDao.deleteUserById(id);
    }

    @Override
    public List<User> getUserList() {
        return userDao.getUserList();
    }

    @Override
    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

}
