package com.netcracker.checkapp.server.model;


import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "userInfo")
public class UserInfo {

    private String login;
    private String pwd;
    private String role;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserInfo)) return false;

        UserInfo userInfo = (UserInfo) o;

        if (!login.equals(userInfo.login)) return false;
        if (!role.equals(userInfo.role)) return false;
        return pwd.equals(userInfo.pwd);

    }

    @Override
    public int hashCode() {
        int result = login.hashCode();
        result = 31 * result + pwd.hashCode();
        result = 31 * result + role.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "login='" + login + '\'' +
                ", pwd='" + pwd + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
