package com.bbb.qqserver.server;

import com.bbb.qqcommon.MessageType;
import com.bbb.qqcommon.Messsge;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

//该类对应对象与客户端保持通讯
public class ServerConnectClientThread extends Thread{
    private Socket socket;
    private String useid;

    public ServerConnectClientThread(Socket socket, String useid) {
        this.socket = socket;
        this.useid = useid;
    }

    @Override
    public void run(){
        //线程运行
        while(true){
            try {
                System.out.println("服务器端和通讯端保持连接" + useid);

                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                 Messsge ms = (Messsge) ois.readObject();

                //根据message类型做相应处理
                if(ms.getMestype().equals(MessageType.MESSAGE_GET_ONLINEFRIEND)){
                    //客户端要在线用户列表
                    System.out.println(ms.getSender() + "要在线用户列表");
                    String onlineuser = ManagelientThreads.getonlineuser();

                    //构建message对象
                    Messsge ms1 = new Messsge();
                    ms1.setMestype(MessageType.MESSAGE_RET_ONLINEFRIEND);
                    ms1.setContent(onlineuser);
                    ms1.setGetter(ms.getSender());

                    //返回客户端
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(ms1);


                }else{
                    System.out.println("其他");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
