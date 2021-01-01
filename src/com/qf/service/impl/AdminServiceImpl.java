package com.qf.service.impl;

import com.qf.dao.IAdminDao;
import com.qf.dao.impl.AdminDaoImpl;
import com.qf.entity.Admin;
import com.qf.entity.User;
import com.qf.service.IAdminService;
import sun.awt.geom.AreaOp;

/**
 * @author Richard
 * 2020/12/25 16:47
 */
public class AdminServiceImpl implements IAdminService {

    IAdminDao adminDao = new AdminDaoImpl();

    @Override
    public Admin login(String username, String password) {
        Admin admin = adminDao.getAdminByUsername(username);
        if (admin==null){
            return null;
        }else if (admin.getPassword().equals(password)){
            return admin;
        }
        return null;
    }
}
