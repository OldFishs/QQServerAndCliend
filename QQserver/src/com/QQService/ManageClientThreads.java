package com.QQService;

import java.net.Socket;
import java.util.HashMap;

//管理和客户端通讯的线程
public class ManageClientThreads {
    private static HashMap<String,ServerConnectClientThread> hm= new HashMap<>();

    public static void addclientThread(String s,ServerConnectClientThread c){
        hm.put(s,c);
    }

    //根据id返回scct线程
    public static ServerConnectClientThread getserclientThread(Socket s){
        return hm.get(s);

    }
}
