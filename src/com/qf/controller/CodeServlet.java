package com.qf.controller;

import cn.dsna.util.images.ValidateCode;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CodeServlet")
public class CodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ValidateCode validateCode = new ValidateCode(150, 30, 4, 10);

        String code = validateCode.getCode();

        request.getSession().setAttribute("code", code);

        ServletOutputStream outputStream = response.getOutputStream();

        validateCode.write(outputStream);

    }
}
