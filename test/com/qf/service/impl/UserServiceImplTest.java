package com.qf.service.impl;

import com.qf.dao.IUserDao;
import com.qf.dao.impl.UserDaoImpl;
import com.qf.entity.User;
import org.junit.Test;

import java.util.Date;

/**
 * @author Richard
 * 2020/12/25 17:37
 */
public class UserServiceImplTest {
    private User user = new User();
    IUserDao userDao = new UserDaoImpl();

    {
        user.setUsername("里两");
        user.setPassword("666");
        user.setSex(1);
        user.setAge(18);
        user.setEmail("qf@123.com");
        user.setBirthday(new Date());
    }

    @Test
    public void testAddUser(){
        int i = userDao.addUser(user);
        System.out.println(i);
    }

}
