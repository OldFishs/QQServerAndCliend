package com.bbb.qqclient.service;

import com.bbb.qqcommon.MessageType;
import com.bbb.qqcommon.Messsge;
import com.bbb.qqcommon.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

//用户登录验证和注册
public class UserClientService {

    private User u = new User();
    private Socket socket;
    //根据id与pw验证是否合法
    public boolean checkUser(String username, String pwd) {
        boolean b = false;
        u.setUserId(username);
        u.setPassword(pwd);

        //连接服务器端，发送数据
        try {
            socket = new Socket(InetAddress.getLocalHost(),9999);
            //fasong
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(u);

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Messsge ms = (Messsge) ois.readObject();


            if(ms.getMestype().equals(MessageType.MESSAGE_LOGIN_SUCCESS)){

                b = true;
                //创建线程保持通讯  新class
                ClientConnectServerThread ccst = new ClientConnectServerThread(socket);
                //启动
                ccst.start();
                //后面线程增多，放入集合关管理
                ManageClientConnectServerThread.addClientConnectServerThread(username,ccst);


            }else{
                socket.close();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return b;
    }

    //像服务器端请求在线用户列表
    public void onlineFriendList(){
        //发送message，类型4
        Messsge ms = new Messsge();
        ms.setMestype(MessageType.MESSAGE_GET_ONLINEFRIEND);
        ms.setSender(u.getUserId());

        //应得到socket对应ObjectoutputStream对象
        try {
            //集合中得到线程 线程中得到socket 再获取输出流
            ObjectOutputStream oos = new ObjectOutputStream
                    (ManageClientConnectServerThread.getClientConnectServerThread
                            (u.getUserId()).getSocket().getOutputStream());

            oos.writeObject(ms);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
