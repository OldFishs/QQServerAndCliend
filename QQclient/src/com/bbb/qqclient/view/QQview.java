package com.bbb.qqclient.view;

import com.bbb.qqclient.service.UserClientService;
import com.bbb.qqclient.utils.Utility;


//客户端菜单
public class QQview {

    private boolean loop = true;
    private String key = "";
    private UserClientService ucs = new UserClientService();
    //显示主菜单

    public static void main(String[] args){
        new QQview().mainMenu();
        System.out.println("服务器退出系统");
    }

    private void mainMenu(){

        while(loop){

            System.out.println("==========欢迎登录网络通讯系统=========");
            System.out.println("\t1 登录系统");
            System.out.println("\t0 退出系统");
            System.out.print("输入选择：");

            key = Utility.readString(1);

            switch(key){
                case "1":
                    System.out.print("请输入用户名：");
                    String useid = Utility.readString(50);
                    System.out.print("请输入密码：");
                    String password = Utility.readString(50);

                    //需要验证是否合法
                    if(ucs.checkUser(useid, password)){
                        System.out.println("=========欢迎" + useid +"登录=========");
                        //进入二级菜单
                        while(loop){
                            System.out.println("\n=========网络通讯二级菜单=========");
                            System.out.println("\t\t 1 显示在线用户列表");
                            System.out.println("\t\t 2 群发消息");
                            System.out.println("\t\t 3 私发消息");
                            System.out.println("\t\t 4 发送文件");
                            System.out.println("\t\t 0 退出系统");
                            System.out.print("请输入你的选择：");
                            key = Utility.readString(1);
                            switch(key){
                                case "1":
                                    ucs.onlineFriendList();
                                    break;
                                case "2":
                                    System.out.println("\t\t 2 群发消息");

                                    break;
                                case "3":
                                    System.out.println("\t\t 3 私发消息");

                                    break;
                                case "4":
                                    System.out.println("\t\t 4 发送文件");

                                    break;
                                case "0":
                                    loop = false;
                                    break;
                            }

                        }
                    }else{
                        System.out.println("=========登录失败========");
                    }
                    break;
                case "0":
                    loop = false;
                    break;
                default:
                    System.out.println("别乱按输入 1或者0");
            }
        }
    }

}
