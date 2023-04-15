                package com.yxt.oa.web.action;

                import com.yxt.oa.bean.Dept;
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
                import java.util.ArrayList;
                import java.util.List;

                @WebServlet({"/dept/list","/dept/detail","/dept/delete","/dept/add","/dept/edit"})
                public class DeptServlet extends HttpServlet {

                    @Override
                    protected void service(HttpServletRequest request, HttpServletResponse response)
                            throws ServletException, IOException {
                        //获取session
                        HttpSession session = request.getSession(false);

                        //如果session不为空且userName不为空
                        if (session != null && session.getAttribute("userName") != null) {
                            //获取servlet根路径
                            String servletPath = request.getServletPath();
                            //路径判断
                            if ("/dept/list".equals(servletPath)) {
                                doList(request, response);
                            }else if ("/dept/detail".equals(servletPath)){
                                doDetail(request,response);
                            }else if ("/dept/delete".equals(servletPath)){
                                doDel(request,response);
                            }else if ("/dept/add".equals(servletPath)){
                                doSave(request,response);
                            }else if ("/dept/edit".equals(servletPath)){
                                doEdit(request,response);
                            }
                        } else {
                            //没有会话信息，发送重定向
                            response.sendRedirect(request.getContextPath());
                        }
                    }

                    private void doEdit(HttpServletRequest request, HttpServletResponse response)
                            throws ServletException, IOException {

                        //请求参数
                        String dno = request.getParameter("dno");
                        String loginName = request.getParameter("loginName");
                        String realName = request.getParameter("realName");

                        //初始化变量
                        Connection conn = null;
                        PreparedStatement pst = null;
                        ResultSet rs = null;
                        int upCount = 0;

                        try {
                            conn = DBUtil.getConnection();

                            String sql = "UPDATE t_user set loginName = ?,realName = ? where id = ?";
                            pst = conn.prepareStatement(sql);
                            pst.setString(1,loginName);
                            pst.setString(2,realName);
                            pst.setString(3,dno);

                            upCount = pst.executeUpdate();

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }finally {
                            DBUtil.close(conn,pst,rs);
                        }
                        if (upCount==1) {
                            //更新成功
                            //发送重定向 path:/dept/list
                           response.sendRedirect(request.getContextPath() + "/dept/list");
                        }else {
                            //更新失败
                            response.sendRedirect(request.getContextPath() + "/error.html");
                        }
                    }

                    private void doSave(HttpServletRequest request, HttpServletResponse response)
                            throws ServletException, IOException {
                        //乱码问题 10以上不用
                        request.setCharacterEncoding("UTF-8");

                        //获取请求参数
                        String dno = request.getParameter("dno");
                        String loginName = request.getParameter("loginName");
                        String realName = request.getParameter("realName");

                        //数据库
                        //初始化变量
                        Connection conn = null;
                        PreparedStatement pst = null;
                        ResultSet rs = null;
                        int count = 0;//更新条数

                        try {
                            conn = DBUtil.getConnection();
                            String sql = "insert into t_user(id,loginName,realName) values (?,?,?)";
                            pst = conn.prepareStatement(sql);
                            pst.setString(1,dno);
                            pst.setString(2,loginName);
                            pst.setString(3,realName);
                            count = pst.executeUpdate();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }finally {
                            DBUtil.close(conn,pst,rs);
                        }

                        //删除成功后，发送重定向
                        if (count == 1) {
                            response.sendRedirect(request.getContextPath() + "/dept/list");
                        }else {
                            response.sendRedirect(request.getContextPath() + "/error.html");
                        }
                    }

                    /**
                     * 根据部门编号，删除部门
                     * @param request
                     * @param response
                     * @throws ServletException
                     * @throws IOException
                     */
                    private void doDel(HttpServletRequest request, HttpServletResponse response)
                            throws ServletException, IOException {

                        //获取参数 --部门编号
                        String dno = request.getParameter("dno");

                        //初始化变量
                        Connection conn = null;
                        PreparedStatement pst = null;
                        ResultSet rs = null;
                        int count = 0;

                        //JDBC
                        try {
                            conn = DBUtil.getConnection();
                            String sql = "delete from t_user where id= ?";
                            pst = conn.prepareStatement(sql);
                            pst.setString(1,dno);
                            count = pst.executeUpdate();

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }finally {
                            DBUtil.close(conn,pst,rs);
                        }

                        //发送重定向
                        if (count == 1) {
                            response.sendRedirect(request.getContextPath() + "/dept/list");
                        }
                    }

                    private void doDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {

                        //response.setContentType("text/html");
                        //response.getWriter().print("/dept/detail");

                        Connection conn = null;
                        PreparedStatement pst = null;
                        ResultSet rs = null;
                        String dno = request.getParameter("dno");
                        //ArrayList<Dept> depts = new ArrayList<>();
                        //封装对象Dept
                        Dept dept = null;

                        try {
                            conn = DBUtil.getConnection();
                            String sql = "SELECT id,loginName,realName FROM t_user where id = ?";
                            pst = conn.prepareStatement(sql);
                            pst.setString(1,dno);
                            rs = pst.executeQuery();
                            while (rs.next()) {
                                int id = rs.getInt(1);
                                String loginName = rs.getString(2);
                                String realName = rs.getString(3);
                                //封装对象
                                dept = new Dept(id, loginName, realName);
                            }

                            //请求域绑定数据
                            request.setAttribute("dept",dept);

                            //获取转发器对象
                            request.getRequestDispatcher("/detail.jsp").forward(request,response);

                        } catch (SQLException | ServletException e) {
                            e.printStackTrace();
                        }finally {
                            DBUtil.close(conn,pst,rs);
                        }

                    }

                    /**
                     * 连接数据库，查询所有部门信息
                     *
                     * @param request
                     * @param response
                     * @throws ServletException
                     * @throws IOException
                     */
                    private void doList(HttpServletRequest request, HttpServletResponse response)
                            throws ServletException, IOException {
                        //初始化变量
                        Connection conn = null;
                        PreparedStatement pst = null;
                        ResultSet rs = null;
                        //创建ArrayList集合用来封装数据
                        List<Dept> depts = new ArrayList<>();
                        try {
                            conn = DBUtil.getConnection();
                            String sql = "SELECT id,loginName,realName FROM t_user";
                            pst = conn.prepareStatement(sql);
                            rs = pst.executeQuery();
                            while (rs.next()) {
                                //获取结果集
                                int id = rs.getInt(1);
                                String loginName = rs.getString(2);
                                String realName = rs.getString(3);
                                //创建dept对象
                                Dept dept = new Dept(id, loginName, realName);
                                //集合中添加元素
                                depts.add(dept);
                            }
                            //请求域绑定属性
                            request.setAttribute("deptlist", depts);
                            //获取请求转发器
                            request.getRequestDispatcher("/list.jsp").forward(request, response);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } finally {
                            DBUtil.close(conn, pst, rs);
                        }
                    }
                }
