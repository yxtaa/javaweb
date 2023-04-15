
            package com.yxt.javaweb.listener;

            import jakarta.servlet.ServletRequestEvent;
            import jakarta.servlet.ServletRequestListener;
            import jakarta.servlet.annotation.WebListener;

            @WebListener
            public class MyServletRequestListener implements ServletRequestListener {
                @Override
                public void requestDestroyed(ServletRequestEvent sre) {
                    //request对象销毁时
                    System.out.println("request对象销毁了" + sre.getServletRequest());
                }

                @Override
                public void requestInitialized(ServletRequestEvent sre) {
                    //request对象被初始化时
                    //每次服务器有请求都会刷新
                    System.out.println("request对象创建了" + sre.getServletRequest());
                }
            }
