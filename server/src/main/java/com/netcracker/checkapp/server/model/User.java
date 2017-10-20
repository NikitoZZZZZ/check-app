package com.netcracker.checkapp.server.model;

public class User {

    // login format : +X(XXX)XXXXXXX
    private String login;
    // password format : any (taking from client side)
    private String pwd;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (!login.equals(user.login)) return false;
        return pwd.equals(user.pwd);

    }

    @Override
    public int hashCode() {
        int result = login.hashCode();
        result = 31 * result + pwd.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
