package com.qf.controller;

import com.qf.entity.Page;
import com.qf.entity.User;
import com.qf.service.IPageService;
import com.qf.service.IUserService;
import com.qf.service.impl.PageServiceImpl;
import com.qf.service.impl.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Richard
 * 2020/12/25 15:25
 */

@WebServlet("/UserServlet/*")
public class UserServlet extends DispatcherServlet {

    IUserService userService = new UserServiceImpl();
    IPageService pageService = new PageServiceImpl();

/*    public String getUserList(HttpServletRequest req) {
        List<User> userList = this.userService.getUserList();
        req.setAttribute("userList", userList);
        return "forward:showUserList.jsp";
    }*/

    public String getUserPage(Page page, HttpServletRequest request) {

        Page userPage = this.pageService.getPage(page.getPageNum(), page.getPageSize());
        request.setAttribute("userPage", userPage);
        return "forward:showUserList.jsp";
    }


    public String addUser(User user, HttpServletResponse response) {
        User userByUsername = this.userService.getUserByUsername(user.getUsername());

        if (userByUsername != null) {
            try {
                response.getWriter().write("用户已存在，请重新<a href='getUserPage'>添加</a>!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            int i = this.userService.addUser(user);
            return "redirect:UserServlet/getUserPage";
        }
        return null;
    }

    public String deleteUserById(Integer id, Integer pageNum) {
        int i = this.userService.deleteUserById(id);
        Page page = this.pageService.getPage(pageNum, 10);
        if (page.getData().size() == 0 || page.getData() == null) {
            pageNum -= 1;
        }
        return "redirect:UserServlet/getUserPage?pageNum=" + pageNum;
    }

    public String updateUser(User user) {
        int i = this.userService.updateUser(user);
        return "redirect:UserServlet/getUserPage";
    }

    public String getUserById(Integer id, HttpServletRequest req) {
        User user = this.userService.getUserById(id);
        req.setAttribute("user", user);
        return "forward:updateUser.jsp";
    }
}
