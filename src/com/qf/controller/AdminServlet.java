package com.qf.controller;

import com.qf.entity.Admin;
import com.qf.service.IAdminService;
import com.qf.service.impl.AdminServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AdminServlet/*")
public class AdminServlet extends DispatcherServlet {

    IAdminService adminService = new AdminServiceImpl();

    public String login(String username, String password, String code, HttpServletRequest request, HttpServletResponse response) {
        String sessionCode = (String) request.getSession().getAttribute("code");

        if (sessionCode.equalsIgnoreCase(code)) {
//            if (true){
            Admin admin = this.adminService.login(username, password);
            if (admin != null) {
                request.getSession().setAttribute("admin", admin);
                return "redirect:UserServlet/getUserPage";
            } else {
                try {
                    response.getWriter().write("用户名或密码错误，请重新<a href='/login.jsp'>登录</a>！");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                response.getWriter().write("验证码错误，请重新<a href='/login.jsp'>登录</a>！");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:login.jsp";
    }

}
