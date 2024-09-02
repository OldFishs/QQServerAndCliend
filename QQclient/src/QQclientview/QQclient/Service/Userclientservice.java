package QQclientview.QQclient.Service;

import QQclientview.QQcommon.Message;
import QQclientview.QQcommon.User;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import static QQclientview.QQcommon.messagetype.MESSAGE_LOGIN_SUCCESS;


//完成登录验证和注册的功能
public class Userclientservice {
    boolean b = false;

    //其他地方可能用到
    private User user = new User();
    private Socket socket;

    //验证是否合法
    public boolean checkuser(String username, String password) throws Exception{
        user.setUserid(username);
        user.setPassword(password);

        //链接到服务端，发送u
        socket = new Socket(InetAddress.getLocalHost(), 9999);
        //得到objectoutputstream对象
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(user);

        //读取从服务端返回对象
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        Message ms = (Message) in.readObject();

        //登录成功
        if(ms.getMessage().equals(MESSAGE_LOGIN_SUCCESS)) {
            //创建一个和服务器端保持通讯的线程
            ClientConnectServerThread ccst = new ClientConnectServerThread(socket);
            ccst.start();
            //后续拓展，将线程放入集合
            ManageClientConnectServerThread.addthread(username,ccst);
            b = true;
            //登录失败
        }else{
            //关闭socket
            socket.close();

        }
        return b;
    }
}
