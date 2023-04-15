
package com.yxt.oa.bean;

import java.util.Objects;

public class Dept {
    private int id;
    private String loginName;
    private String realName;

    public Dept() {
    }

    public Dept(int id, String loginName, String realName) {
        this.id = id;
        this.loginName = loginName;
        this.realName = realName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "id=" + id +
                ", loginName='" + loginName + '\'' +
                ", realName='" + realName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dept dept = (Dept) o;
        return id == dept.id && Objects.equals(loginName, dept.loginName) && Objects.equals(realName, dept.realName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, loginName, realName);
    }
}
