package com.example.third.bean;

/**
 * Created by Administrator on 2018/4/16.
 */

public class OnLoginResult {
    private String uid;
    private String name;
    private String gender;
    private String iconurl;
    private String msg;
    private LoginResultStatus status;

    public LoginResultStatus getStatus() {
        return status;
    }

    public void setStatus(LoginResultStatus status) {
        this.status = status;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIconurl() {
        return iconurl;
    }

    public void setIconurl(String iconurl) {
        this.iconurl = iconurl;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private enum LoginResultStatus {
        OK,
        FAIL,
        CANCEL
    }
}
