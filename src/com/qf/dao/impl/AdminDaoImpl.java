package com.qf.dao.impl;

import com.qf.dao.IAdminDao;
import com.qf.entity.Admin;
import com.qf.utils.DaoUtils;

import java.util.List;

/**
 * @author Richard
 * 2020/12/25 16:43
 */
public class AdminDaoImpl implements IAdminDao {
    @Override
    public Admin getAdminByUsername(String username) {
        List<Admin> admins = DaoUtils.commonQuery("select * from t_admin where username = ?", Admin.class, username);
        if (admins.isEmpty()){
            return null;
        }
        return admins.get(0);
    }
}
