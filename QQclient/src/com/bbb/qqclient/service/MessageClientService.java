package com.bbb.qqclient.service;

import com.bbb.qqcommon.MessageType;
import com.bbb.qqcommon.Messsge;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

//提供与消息相关的方法
public class MessageClientService {

    public void sendMessage(String msg, String senderid, String getterid){
        Messsge ms = new Messsge();
        ms.setMestype(MessageType.MESSAGE_COMM_MES);
        ms.setSender(senderid);
        ms.setGetter(getterid);
        ms.setContent(msg);
        ms.setSendtime(new Date().toString());
        System.out.println("我对" + getterid + "说" + msg);

        //发送给服务端
        try {
            ObjectOutputStream oos = new ObjectOutputStream
                    (ManageClientConnectServerThread.getClientConnectServerThread
                            (senderid).getSocket().getOutputStream());
            oos.writeObject(ms);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //群聊
    public void allsendMessage(String msg, String senderid){
        Messsge ms = new Messsge();
        ms.setMestype(MessageType.MESSAGE_ALL_MES);
        ms.setSender(senderid);
        ms.setContent(msg);
        ms.setSendtime(new Date().toString());
        System.out.println("我对大家说" + msg);

        //发送给服务端
        try {
            ObjectOutputStream oos = new ObjectOutputStream
                    (ManageClientConnectServerThread.getClientConnectServerThread
                            (senderid).getSocket().getOutputStream());
            oos.writeObject(ms);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
