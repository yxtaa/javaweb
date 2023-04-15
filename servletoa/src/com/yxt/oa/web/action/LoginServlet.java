package com.yxt.oa.web.action;

import com.yxt.oa.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet({"/user/login"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        String userName = request.getParameter("userName");
        String passWord = request.getParameter("passWord");

        //初始化变量
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int count = 0;

        try {
            //获取数据库连接对象
            conn = DBUtil.getConnection();

            //预处理语句
            String sql = "select loginName,realName from t_user where loginName = ?" +
                    "and realName = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1,userName);
            pst.setString(2,passWord);

            //执行查询
            rs = pst.executeQuery();

            //处理结果集 如果结果集有数据
            if (rs.next()) {
                //获取session对象 不写false
                HttpSession session = request.getSession();
                //设置属性
                session.setAttribute("userName",userName);

                //发送重定向
                response.sendRedirect(request.getContextPath() + "/dept/list");
            }else {
                //登录失败
                response.sendRedirect(request.getContextPath() + "/error.html");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,pst,rs);
        }
    }
}
