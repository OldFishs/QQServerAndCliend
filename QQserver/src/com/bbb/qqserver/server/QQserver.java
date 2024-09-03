package com.bbb.qqserver.server;

import com.bbb.qqcommon.MessageType;
import com.bbb.qqcommon.Messsge;
import com.bbb.qqcommon.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

//服务器 监听 通讯 连接
public class QQserver {
    private ServerSocket ss;

    //创建集合存放用户
    //hashmap多线程情况下不安全
    private static ConcurrentHashMap<String, User> vaildusers = new ConcurrentHashMap<>();

    static{//在静态代码块初始化
        vaildusers.put("100",new User("100","123456"));
        vaildusers.put("200",new User("200","123456"));
        vaildusers.put("300",new User("300","123456"));

    }

    //验证用户是否有效的方法
    private boolean checkuser(String username,String password){
        User user = vaildusers.get(username);
        if(user == null){
            return false;
        }
        if(!user.getPassword().equals(password)){
            return false;
        }
        return true;

    }

    public QQserver() {
        try {
            System.out.println("在端口9999监听，，，");
            new Thread(new SendNewsToAllService()).start();
            ss = new ServerSocket(9999);

            while(true){
                Socket s = ss.accept();

                //得到socket关联的对象流
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                //得到socket关联的输出流
                ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());

                //读取User
                User u = (User) ois.readObject();
                Messsge msg = new Messsge();
                //登录成功
                if(checkuser(u.getUserId(),u.getPassword())){
                    msg.setMestype(MessageType.MESSAGE_LOGIN_SUCCESS);
                    //回复是否登录成功
                    oos.writeObject(msg);
                    //开始创建线程，保持通信 需要socket


                    //开始通讯
                    ServerConnectClientThread scct = new ServerConnectClientThread(s,u.getUserId());
                    //启动
                    scct.start();

                    //放入集合，方便管理
                    ManagelientThreads.addClientThread(u.getUserId(),scct);


                }else{//登录失败
                    System.out.println(u.getUserId() + " " + u.getPassword() + "登录失败，，");
                    msg.setMestype(MessageType.MESSAGE_LOGIN_FAIL);
                    oos.writeObject(msg);
                    s.close();
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            try {
                ss.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
