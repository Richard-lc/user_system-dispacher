package com.qf.dao.impl;

import com.qf.dao.IPageDao;
import com.qf.entity.User;
import com.qf.utils.DaoUtils;

import java.util.List;

/**
 * @author Richard
 * 2020/12/29 16:24
 */
public class PageDaoImpl implements IPageDao {

    @Override
    public List<User> getPageData(Integer index, Integer pageSize) {

        String sql = "select * from t_user limit ?,?";
        return DaoUtils.commonQuery(sql, User.class, index, pageSize);
    }

    @Override
    public Integer getTotalCount() {

        String sql = "select Count(1) from t_user";
        return DaoUtils.commonGetCount(sql);
    }
}
