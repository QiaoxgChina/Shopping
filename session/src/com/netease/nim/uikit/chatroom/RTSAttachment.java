package com.netease.nim.uikit.chatroom;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by huangjun on 2015/7/28.
 */
public class RTSAttachment extends CustomAttachment {

    private byte flag;

    public RTSAttachment() {
        super(CustomAttachmentType.RTS);
    }

    public RTSAttachment(byte flag) {
        this();
        this.flag = flag;
    }

    @Override
    protected JSONObject packData() {
        JSONObject data = new JSONObject();
        data.put("flag", flag);
        return data;
    }

    @Override
    protected void parseData(JSONObject data) {
        flag = data.getByte("flag");
    }

    public byte getFlag() {
        return flag;
    }

    public String getContent() {
        return "录音";
    }
}
