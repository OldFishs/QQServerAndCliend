package com.bbb.qqclient.service;

import com.bbb.qqcommon.MessageType;
import com.bbb.qqcommon.Messsge;

import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientConnectServerThread extends Thread {
    //需要socket
    private Socket socket ;

    //构造器接收socket对象
    public ClientConnectServerThread(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public void run() {
        //保持通讯，while
        while(true){
            System.out.println("客户端等待服务器端发送消息") ;
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                //没法消息线程一致在这
                Messsge ms = (Messsge) ois.readObject();

                //不断运行，接收服务器端发回的消息
                //判断message类型，相应处理
                if(ms.getMestype().equals(MessageType.MESSAGE_RET_ONLINEFRIEND)){//显示列表

                    //取出在线列表 显示
                    String[] onlineFriend = ms.getContent().split(" ");
                    System.out.println("\n========当前在线用户列表如下=======");
                    for(int i = 0 ; i < onlineFriend.length ; i++){
                        System.out.println("用户:" + onlineFriend[i]);
                    }
                }else if(ms.getMestype().equals(MessageType.MESSAGE_COMM_MES)){//普通聊天
                    System.out.println("\n\t" + ms.getSender() + "对我说:" + ms.getContent());

                }


                else{
                    System.out.println("其他");
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
