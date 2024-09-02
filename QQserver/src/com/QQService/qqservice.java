package com.QQService;

import com.QQcommon.Message;
import com.QQcommon.User;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static com.QQcommon.messagetype.MESSAGE_LOGIN_FATAL;
import static com.QQcommon.messagetype.MESSAGE_LOGIN_SUCCESS;

//服务器监听9999端口
public class qqservice {
    private ServerSocket ss= null;

    public qqservice() throws Exception {
        System.out.println("服务器端在9999监听");
            ss=new ServerSocket(9999);

            while(true){
                Socket s = ss.accept();
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());

                //得到输出流
                ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());

                //读取客户端发送的User对象
                User o = (User)ois.readObject();

                //创建message对象，回复客户端
                Message m = new Message();
                //验证
                if(o.getUserid().equals("100") && o.getPassword().equals("123456 ")){
                    m.setTyep(MESSAGE_LOGIN_SUCCESS);
                    //发送
                    oos.writeObject(m);
                    //创建线程 持有socket对象
                    ServerConnectClientThread scct = new ServerConnectClientThread(s,o.getUserid());
                    scct.start();

                    //把线程放入集合中管理
                    ManageClientThreads.addclientThread(o.getUserid(),scct);

                }else{
                    System.out.println("用户 id=" + o.getUserid() + " pwd=" + o.getPassword() + " 验证失败");
                    m.setTyep(MESSAGE_LOGIN_FATAL);
                    oos.writeObject(m);
                    s.close();

                }
                ss.close();
            }
    }
}
