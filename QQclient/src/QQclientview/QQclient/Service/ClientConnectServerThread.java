package QQclientview.QQclient.Service;

import QQclientview.QQcommon.Message;

import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientConnectServerThread extends Thread {
    private Socket socket;

    //构造器接收socket对象
    public ClientConnectServerThread(Socket socket) {
        this.socket = socket;
    }

    //因为thread在后台和服务器通讯 因此我们while循环
    public void run() {
        while(true){
            System.out.println("客户端等待读取服务器端发送的消息，，");
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                //如果服务器没发送message对象，会阻塞在这
                Message message = (Message) ois.readObject();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    public Socket getSocket() {
        return socket;
    }
}
