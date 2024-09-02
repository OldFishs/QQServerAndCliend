package com.bbb.qqclient.service;

import java.util.HashMap;

//管理    客户端练到服务器端的类
public class ManageClientConnectServerThread {
    //多线程放入hashmap  key=name value为线程
    private static HashMap<String,ClientConnectServerThread> hm = new HashMap<>();

    //添加
    public static void addClientConnectServerThread(String s ,ClientConnectServerThread c) {
        hm.put(s, c);

    }

    //通过name获取
    public static ClientConnectServerThread getClientConnectServerThread(String s) {
        return hm.get(s);
    }

}
