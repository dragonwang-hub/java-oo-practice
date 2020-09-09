package com.twu;

import java.util.*;
import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {
        // scanner 关闭后再打开会报错，此处目前处理方式是将scan.close()在程序结束前关闭
        StringBuilder welcomeStr = new StringBuilder();
        welcomeStr.append("欢迎来到热搜排行榜，您是？\n");
        welcomeStr.append("1.管理员\n2.普通用户\n3.退出");
        while (true) {
            System.out.println("=================用户选择===================");
            int selectRole = ScannerDemo.scanNextInt(welcomeStr.toString());
            if (selectRole == 3) {
                break;
            }
            String roleName = ScannerDemo.scanNextline("请输入您的昵称:");
            System.out.println("Welcome~! >_< " + roleName);

            if (selectRole == 1) {
                Admins newAdmin = new Admins(roleName);
                adminInterface(newAdmin);
            } else if (selectRole == 2) {
                Customs newCustm = new Customs(roleName);
                customInterface(newCustm);
            }
        }
        ScannerDemo.scan.close();
        System.out.println("=================程序结束===================");
    }

    static public void adminInterface(Admins admin) {
        StringBuilder selectOper = new StringBuilder();
        selectOper.append("1.查看热搜排行榜\n" + "2.添加热搜\n" + "3.添加超级热搜\n" + "4.退出");
        while (true) {
            System.out.println("=================管理员界面===================");
            int selectRole = ScannerDemo.scanNextInt(selectOper.toString());
            if (selectRole == 4) {
                break;
            }
            // 使用映射结构存放key值和对应的方法
            Map<Integer, Consumer<Admins>> admnMethodMap = new HashMap<>();
            // method reference 方法引用：Class：：instanceMethod
            admnMethodMap.put(1, Roles::viewTrendingTopic);
            admnMethodMap.put(2, Roles::addTrendingTopic);
            admnMethodMap.put(3, Admins::addSuperTrenTopic);
            // 使用optional判断方法存在与否，并map映射执行了对应方法，若不存在，使用orElseGet打印一句话再返回值至optional容器
            Optional.ofNullable(admnMethodMap.get(selectRole)).map(method -> {
                method.accept(admin);
                return true;
            }).orElseGet(() -> {
                System.out.println("输入错误，请重新选择！");
                return false;
            });
        }
    }


    static public void customInterface(Customs custm) {
        StringBuilder selectOper = new StringBuilder();
        selectOper.append("1.查看热搜排行榜\n" + "2.给热搜事件投票\n" + "3.购买热搜\n" + "4.添加热搜\n" + "5.退出");
        while (true) {
            System.out.println("=================普通用户界面===================");
            int selectRole = ScannerDemo.scanNextInt(selectOper.toString());
            if (selectRole == 5) {
                break;
            }
            Map<Integer, Consumer<Customs>> cusmMethodMap = new HashMap<>();
            cusmMethodMap.put(1, Roles::viewTrendingTopic);
            cusmMethodMap.put(2, Customs::voteTrendingTopic);
            cusmMethodMap.put(3, Customs::buyTrendingTopic);
            cusmMethodMap.put(4, Roles::addTrendingTopic);
            Optional.ofNullable(cusmMethodMap.get(selectRole)).map(method -> {
                method.accept(custm);
                return true;
            }).orElseGet(() -> {
                System.out.println("输入错误，请重新选择！");
                return false;
            });
        }
    }


}
