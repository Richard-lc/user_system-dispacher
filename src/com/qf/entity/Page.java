package com.qf.entity;

import lombok.Data;

import java.util.List;

/**
 * @author Richard
 * 2020/12/29 16:19
 */

@Data
public class Page {

    //页码
    private Integer pageNum = 1;

    //每页显示的条数
    private Integer pageSize = 10;

    //总条数
    private Integer totalCount;

    //总页数
    private Integer totalPage;

    //每页的数据
    private List<User> data;
}
