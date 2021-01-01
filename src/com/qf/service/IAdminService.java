package com.qf.service;

import com.qf.entity.Admin;
import com.qf.entity.User;

/**
 * @author Richard
 * 2020/12/25 16:46
 */
public interface IAdminService {
    Admin login(String username, String password);
}
