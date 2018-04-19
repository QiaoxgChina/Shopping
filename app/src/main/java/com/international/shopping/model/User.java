package com.international.shopping.model;

import com.international.baselib.net.CustomResult;

public class User extends CustomResult {

    private String gender;
    private String nickname;
    private String id;
    private String name;
    private String phone;
    private String iconUrl;
    private String wyAccount;
    private String wyToken;

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWyAccount() {
        return wyAccount;
    }

    public void setWyAccount(String wyAccount) {
        this.wyAccount = wyAccount;
    }

    public String getWyToken() {
        return wyToken;
    }

    public void setWyToken(String wyToken) {
        this.wyToken = wyToken;
    }
}
