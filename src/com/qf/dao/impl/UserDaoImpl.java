package com.qf.dao.impl;

import com.qf.dao.IUserDao;
import com.qf.entity.User;
import com.qf.utils.DaoUtils;

import java.util.List;

/**
 * @author Richard
 * 2020/12/25 15:13
 */
public class UserDaoImpl implements IUserDao {
    @Override
    public int addUser(User user) {
        String sql = "insert into t_user(username,password,sex,age,email,birthday) values(?,?,?,?,?,?)";
        return DaoUtils.commonUpdate(sql, user.getUsername(),user.getPassword(),user.getSex(),user.getAge(),user.getEmail(),user.getBirthday());
    }

    @Override
    public int updateUser(User user) {
        String sql = "update t_user set username = ?,password = ?,sex = ?,age = ?,email = ?,birthday = ? where id = ?";
        return DaoUtils.commonUpdate(sql, user.getUsername(), user.getPassword(), user.getSex(), user.getAge(), user.getEmail(), user.getBirthday(),user.getId());
    }

    @Override
    public int deleteUserById(Integer id) {
        return DaoUtils.commonUpdate("delete from t_user where id = ?",id);
    }

    @Override
    public List<User> getUserList() {
        return DaoUtils.commonQuery("select * from t_user", User.class);
    }

    @Override
    public User getUserById(Integer id) {
        List<User> users = DaoUtils.commonQuery("select * from t_user where id = ?", User.class, id);
        if (users.isEmpty()){
            return null;
        }
        return users.get(0);
    }

    @Override
    public User getUserByUsername(String username) {
        List<User> users = DaoUtils.commonQuery("select * from t_user where username = ?", User.class, username);
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }
}
