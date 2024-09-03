package com.bbb.qqserver.server;

import com.bbb.qqcommon.MessageType;
import com.bbb.qqcommon.Messsge;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;



//该类对应对象与客户端保持通讯
public class ServerConnectClientThread extends Thread{
    private Socket socket;
    private String useid;

    public ServerConnectClientThread(Socket socket, String useid) {
        this.socket = socket;
        this.useid = useid;
    }

    public Socket getSocket() {
        return socket;
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

                }else if(ms.getMestype().equals(MessageType.MESSAGE_COMM_MES)){

                    //获取getterid，得到对应线程
                    ServerConnectClientThread scct = ManagelientThreads.getServerConnectClientThread(ms.getGetter());
                    //得到对应输出流， 输出给客户端
                    ObjectOutputStream oos = new ObjectOutputStream(scct.getSocket().getOutputStream());
                    oos.writeObject(ms);
                    //如果不在线 保存到数据库

                }else if(ms.getMestype().equals(MessageType.MESSAGE_ALL_MES)){

                    //遍历线程集合，拿到所有socket
                    HashMap<String, ServerConnectClientThread> hm = ManagelientThreads.getHm();
                    Iterator<String> iterator = hm.keySet().iterator();
                    while(iterator.hasNext()){
                        //排除群发的用户
                        //如果除掉key会发送少用户
                        String key = iterator.next();
                        if(!key.equals(ms.getSender())){
                            ObjectOutputStream oos = new ObjectOutputStream(hm.get(key).getSocket().getOutputStream());
                            oos.writeObject(ms);

                        }
                    }
                }else if(ms.getMestype().equals(MessageType.MESSAGE_FILE_MES)){
                    ServerConnectClientThread scct = ManagelientThreads.getServerConnectClientThread(ms.getGetter());
                    ObjectOutputStream oos = new ObjectOutputStream(scct.getSocket().getOutputStream());
                    //转发
                    oos.writeObject(ms);

                } else if(ms.getMestype().equals(MessageType.MESSAGE_CLIENT_EXIT)){
                    System.out.println(ms.getSender() + "退出");
                    ManagelientThreads.removeClientThread(ms.getSender());
                    socket.close();
                    break;

                }else{
                    System.out.println("其他");
                }
            } catch (Exception e) {
                e.printStackTrace();


            }
        }

    }
}
