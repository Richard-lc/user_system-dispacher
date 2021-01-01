package com.qf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Richard
 * 2020/12/25 16:42
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

    private Integer id;
    private String username;
    private String password;
}
