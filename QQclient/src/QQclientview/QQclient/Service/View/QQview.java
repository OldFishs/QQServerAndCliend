package QQclientview.QQclient.Service.View;


import QQclientview.QQclient.Service.Userclientservice;

import java.util.Scanner;

//客户端的菜单界面！！！！
public class QQview {

    private boolean a = true;
    private String key = "";
    private String name = "";
    private String password = "";
    //用于登录注册用户
    private Userclientservice user = new Userclientservice();



    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        new QQview().mainview();
    }



    //主菜单
    private void mainview() throws Exception {
        while(a){
            System.out.println("==========欢迎登录网络通讯系统=========");
            System.out.println("\t\t 1 登录系统");
            System.out.println("\t\t 0 系统退出");
            System.out.print("请输入：");

            key = sc.next();

            switch(key){
                case "1":
                    System.out.print("请输入用户名：");
                    name = sc.next();
                    char[] c = name.toCharArray();
                    if(c.length > 10){
                        System.out.println("用户名超过10位数，请重新输入。");
                        break;
                    }
                    System.out.print("请输入密码：");
                    password = sc.next();
                    char[] c2 = password.toCharArray();
                    if(c2.length > 15){
                        System.out.println("密码超过15位数，请重新输入。");
                        break;
                    }

                    //去服务器验证是否合法
                    //编写专门类验证用户登录是否合法

                    if(user.checkuser(name,password)){
                        //二级菜单
                        System.out.println("==========欢迎" + name + "登录=========");
                        while(a){
                            System.out.println("\n==========网络通讯系统二级菜单(id：" + name +")=========");
                            //1 显示在线用户列表2 群发消息3 私聊消息4 发送文件9 退出系统
                            System.out.println("\t\t1 显示在线用户列表");
                            System.out.println("\t\t2 群发消息");
                            System.out.println("\t\t3 私聊消息");
                            System.out.println("\t\t4 发送文件");
                            System.out.println("\t\t0 退出系统");

                            System.out.print("请输入：");
                            key = sc.next();
                            switch(key){
                                case "1":
                                    System.out.println("\t\t1 显示在线用户列表");

                                    break;
                                case "2":
                                    System.out.println("\t\t2 群发消息");

                                    break;
                                case "3":
                                    System.out.println("\t\t3 私聊消息");

                                    break;
                                case "4":
                                    System.out.println("\t\t4 发送文件");

                                    break;
                                case "0":
                                    a = false;
                                    break;
                                default:
                                    System.out.println("指令无效，按错啦傻逼");

                            }
                        }
                    }else{
                        System.out.println("==========登录失败=========");
                    }


                    break;
                case "0":
                    a = false;
                    break;
                default:
                    System.out.println("别几把乱按，1 or 0，明白吗？");
            }

        }

    }
}
