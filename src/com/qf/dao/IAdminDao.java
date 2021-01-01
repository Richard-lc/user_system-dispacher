package com.qf.dao;

import com.qf.entity.Admin;

/**
 * @author Richard
 * 2020/12/25 16:41
 */
public interface IAdminDao {

    Admin getAdminByUsername(String username);
}
