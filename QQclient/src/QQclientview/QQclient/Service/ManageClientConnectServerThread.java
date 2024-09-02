package QQclientview.QQclient.Service;

import java.util.HashMap;

public class ManageClientConnectServerThread {
    //把多线程放入一个hashmap key是用户 value是线程
    private static HashMap<String,ClientConnectServerThread> hm = new HashMap<>();

    //将某个线程加入集合中
    public static void addthread(String name,ClientConnectServerThread clientConnectServerThread){
        hm.put(name,clientConnectServerThread);
    }

    //通过name得到对应线程
    public static ClientConnectServerThread getthread(String name){
        return hm.get(name);
    }
}
