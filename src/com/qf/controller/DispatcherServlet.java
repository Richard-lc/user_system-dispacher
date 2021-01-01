package com.qf.controller;

import com.qf.utils.Constant;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author Richard
 * 2020/12/28 10:27
 */

public class DispatcherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取请求URI
        String requestURI = req.getRequestURI();

        //获取请求action
        String actionName = requestURI.substring(requestURI.lastIndexOf("/") + 1);

        //根据action获取需要调用的方法
        Method method = this.getMethodByActionName(actionName);

        if (method != null) {

            //获取需要调用的方法的参数
            Object[] args = this.getParameterArgs(method, req, resp);

            if (args != null || args.length == 0) {
                try {
                    //调用方法，得到返回值
                    String invoke = (String) method.invoke(this, args);

                    this.invokeProcess(invoke, req, resp);

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void invokeProcess(String invoke, HttpServletRequest req, HttpServletResponse resp) {
        if (invoke != null && !"".equals(invoke)) {
            String[] split = invoke.split(":");
            String type = split[0];
            String page = split[1];
            //转发
            if (Constant.FORWARD.equals(type)) {
                try {
                    req.getRequestDispatcher("/" + page).forward(req, resp);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //重定向
            } else if (Constant.REDIRECT.equals(type)) {
                try {
                    resp.sendRedirect(req.getContextPath() + "/" + page);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private Object[] getParameterArgs(Method method, HttpServletRequest req, HttpServletResponse resp) {

        Parameter[] parameters = method.getParameters();
        Object[] args = new Object[parameters.length];
        Object value;

        //注册类型转换器
        this.typeRegister();

        for (int i = 0; i < parameters.length; i++) {
            if (Constant.HTTP_SERVLET_REQUEST.equals(parameters[i].getType().getSimpleName())) {
                value = req;
            } else if (Constant.HTTP_SERVLET_RESPONSE.equals(parameters[i].getType().getSimpleName())) {
                value = resp;
            } else {
                value = this.parameterType(req.getParameter(parameters[i].getName()), parameters[i].getType());
                if (value == null || "".equals(value)) {
                    try {
                        Object instance = parameters[i].getType().newInstance();
                        Map<String, String[]> parameterMap = req.getParameterMap();
                        BeanUtils.populate(instance, parameterMap);
                        value = instance;
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
            args[i] = value;
        }
        return args;
    }

    private void typeRegister() {
        ConvertUtils.register(new Converter() {
            @Override
            public Object convert(Class cls, Object o) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    return simpleDateFormat.parse(o.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }, Date.class);
    }

    private Object parameterType(String parameter, Class<?> type) {

        if (Constant.INTEGER.equals(type.getSimpleName()) && parameter != null) {
            return Integer.parseInt(parameter);
        } else if (Constant.DOUBLE.equals(type.getSimpleName()) && parameter != null) {
            return Double.parseDouble(parameter);
        }
        return parameter;
    }

    private Method getMethodByActionName(String actionName) {
        Method[] methods = this.getClass().getMethods();
        for (int i = 0; i < methods.length; i++) {
            if (methods[i].getName().equals(actionName)) {
                return methods[i];
            }
        }
        return null;
    }
}
