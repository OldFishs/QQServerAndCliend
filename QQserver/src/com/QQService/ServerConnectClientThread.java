package com.QQService;

//该类对应的一个对象与某个客户端保持连接

import com.QQcommon.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ServerConnectClientThread extends Thread {
    private Socket socket;
    private String userid;

    public ServerConnectClientThread(Socket socket, String userid) {
        this.socket = socket;
        this.userid = userid;
    }


    public void run() {

        while(true){
            System.out.println("服务和客户保持通讯，读取数据" + userid);

            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message o = (Message)ois.readObject();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
