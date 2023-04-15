package com.yxt.javaweb.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class MyServletContextListener implements
        ServletContextListener,
        ServletRequestListener,
        HttpSessionListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //服务器启动事件（ServletContext对象被创建）
        System.out.println("ServletContext对象被创建");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //服务器关闭事件（ServletContext对象被销毁）
        System.out.println("ServletContext对象被销毁");
    }
}
