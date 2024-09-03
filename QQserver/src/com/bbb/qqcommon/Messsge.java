package com.bbb.qqcommon;

import java.io.Serializable;

public class Messsge implements Serializable {
    private static final long serialVersionUID = 1L;
    private String sender;
    private String getter;
    private String content;
    private String sendtime;
    private String mestype;
    //拓展
    private byte[] filebyte;//文件数组
    private int filelen = 0;
    private String dest;//传输位置
    private String src;//起始位置

    public byte[] getFilebyte() {
        return filebyte;
    }

    public void setFilebyte(byte[] filebyte) {
        this.filebyte = filebyte;
    }

    public int getFilelen() {
        return filelen;
    }

    public void setFilelen(int filelen) {
        this.filelen = filelen;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getGetter() {
        return getter;
    }

    public void setGetter(String getter) {
        this.getter = getter;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendtime() {
        return sendtime;
    }

    public void setSendtime(String sendtime) {
        this.sendtime = sendtime;
    }

    public String getMestype() {
        return mestype;
    }

    public void setMestype(String mestype) {
        this.mestype = mestype;
    }
}
