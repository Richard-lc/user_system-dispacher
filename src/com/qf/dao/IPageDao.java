package com.qf.dao;

import com.qf.entity.User;

import java.util.List;

/**
 * @author Richard
 * 2020/12/29 16:23
 */
public interface IPageDao {

    List<User> getPageData(Integer index, Integer pageSize);

    Integer getTotalCount();
}
