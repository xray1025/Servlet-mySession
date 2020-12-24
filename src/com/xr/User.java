package com.xr;

public class User {

    private String name;
    private String password;
    private String goods;
    private String jsessionId;
    public User(String name, String password, String goods) {
        this.name = name;
        this.password = password;
        this.goods = goods;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public String getJsessionId() {
        return jsessionId;
    }

    public void setJsessionId(String jsessionId) {
        this.jsessionId = jsessionId;
    }
}
