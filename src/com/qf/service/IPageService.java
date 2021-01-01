package com.qf.service;

import com.qf.entity.Page;

/**
 * @author Richard
 * 2020/12/29 16:32
 */
public interface IPageService {
    Page getPage(Integer pageNum, Integer pageSize);
}
