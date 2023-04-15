            package com.yxt.oa.utils;

            import java.sql.*;
            import java.util.ResourceBundle;

            public  class DBUtil {
                public static  ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("resources.jdbc");
                public static  String DRIVER = RESOURCE_BUNDLE.getString("driver");
                public static  String URL = RESOURCE_BUNDLE.getString("url");
                public static  String USER = RESOURCE_BUNDLE.getString("user");
                public static  String PASSWORD = RESOURCE_BUNDLE.getString("password");


                static {
                    try {
                        Class.forName(DRIVER);
                        //System.out.println("注册驱动");
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }

                /**
                 * 获取数据库连接对象
                 * @return 获取数据库连接对象
                 * @throws SQLException
                 */
                public static Connection getConnection() throws SQLException {
                    return DriverManager.getConnection(URL, USER, PASSWORD);
                }

                /**
                 * 释放资源
                 * @param connection
                 * @param preparedStatement
                 * @param resultSet
                 */
                public static void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet){
                    if (resultSet != null){
                        try {
                            resultSet.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    if (preparedStatement != null){
                        try {
                            preparedStatement.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null){
                        try {
                            connection.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
