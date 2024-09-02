package com.bbb.qqserver.server;

import java.util.HashMap;
import java.util.Iterator;

//管理和客户端通讯的线程
public class ManagelientThreads {
    private static HashMap<String,ServerConnectClientThread> hm = new HashMap<>();

    //添加线程到集合
    public static void addClientThread(String s,ServerConnectClientThread c){
        hm.put(s,c);
    }

    //根据userid返回serverconnerctclientthread线程
    public static ServerConnectClientThread getServerConnectClientThread(String s,ServerConnectClientThread c){
        return hm.get(s);
    }

    //返回在线用户列表
    public static String getonlineuser(){
        //集合的遍历
        Iterator<String> iterator = hm.keySet().iterator();
        String s = "";
        while(iterator.hasNext()){
            s += iterator.next().toString() + " ";
        }
        return s;
    }

    //移除
    public static void removeClientThread(String s){
        hm.remove(s);
    }
}
