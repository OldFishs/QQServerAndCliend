package com.bbb.qqserver.server;

import com.bbb.qqcommon.MessageType;
import com.bbb.qqcommon.Messsge;
import com.bbb.utils.Utility;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

//服务器推送消息
public class SendNewsToAllService implements Runnable{

    @Override
    public void run() {
        while(true){
            System.out.println("请输入服务器要推送的新闻消息[输入0退出]：");
            String news = Utility.readString(100);
            if(news.equals("0")){
                break;
            }
            Messsge ms = new Messsge();
            ms.setSender("服务器");
            ms.setContent(news);
            ms.setMestype(MessageType.MESSAGE_ALL_MES);
            ms.setSendtime(new Date().toString());
            System.out.println("服务器推送：" + news);

            //遍历所有通讯线程，得到socket
            HashMap<String,ServerConnectClientThread> hm = ManagelientThreads.getHm();
            Iterator<String> iterator = hm.keySet().iterator();
            while(iterator.hasNext()){
                String onlineuses = iterator.next();
                ServerConnectClientThread client = hm.get(onlineuses);
                Socket socket = client.getSocket();
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(ms);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }



    }
}
