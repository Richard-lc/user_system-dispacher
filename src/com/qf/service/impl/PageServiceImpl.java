package com.qf.service.impl;

import com.qf.dao.IPageDao;
import com.qf.dao.impl.PageDaoImpl;
import com.qf.entity.Page;
import com.qf.entity.User;
import com.qf.service.IPageService;

import java.util.List;

/**
 * @author Richard
 * 2020/12/29 16:34
 */
public class PageServiceImpl implements IPageService {

    IPageDao pageDao = new PageDaoImpl();

    @Override
    public Page getPage(Integer pageNum, Integer pageSize) {

        Page page = new Page();

        Integer index = (pageNum - 1) * pageSize;

        List<User> pageData = this.pageDao.getPageData(index, pageSize);
        Integer totalCount = this.pageDao.getTotalCount();

        page.setData(pageData);
        page.setTotalCount(totalCount);
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);

        if (totalCount % pageSize == 0) {
            page.setTotalPage(totalCount / pageSize);
        } else {
            page.setTotalPage(totalCount / pageSize + 1);
        }

        return page;
    }
}
