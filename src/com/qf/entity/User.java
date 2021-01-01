package com.qf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Richard
 * 2020/12/25 15:09
 */

/**
 * User类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer sex;
    private Integer age;
    private String email;
    private Date birthday;
}
