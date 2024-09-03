package com.bbb.qqclient.service;

import com.bbb.qqcommon.MessageType;
import com.bbb.qqcommon.Messsge;

import java.io.*;

//文件传输服务
public class FileClientService {

    public void sendfiletoone(String src,String dest,String sender,String getter){

        Messsge ms = new Messsge();
        ms.setMestype(MessageType.MESSAGE_FILE_MES);
        ms.setSender(sender);
        ms.setDest(dest);
        ms.setSrc(src);
        ms.setGetter(getter);

        //读取文件
        FileInputStream fis = null;
        //src读入字节数组
        byte[] filebytes = new byte[(int) new File(src).length()];
        try {
            //???????
            fis = new FileInputStream(src);
            fis.read(filebytes);
            //字节数组设置message
            ms.setFilebyte(filebytes);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("\n" + sender + "给" + getter + "发送文件：" + src + "到对方电脑：" + dest);

        //发送
        try {
            ObjectOutputStream oos = new ObjectOutputStream
                    (ManageClientConnectServerThread.getClientConnectServerThread
                            (sender).getSocket().getOutputStream());
            oos.writeObject(ms);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
