package com.twu;

import java.util.Scanner;

public class ScannerDemo {
    static Scanner scan = new Scanner(System.in);
    static public String scanNextline(String promptStr) {
        //Scanner scan = new Scanner(System.in);
        // 从键盘接收数据
        String inStr = "";
        // nextLine方式接收字符串
        System.out.println(promptStr);
        // 判断是否还有输入
        if (scan.hasNextLine()) {
            inStr = scan.nextLine();
        }
        //scan.close();
        return inStr;
    }

    static public int scanNextInt(String promptStr) {
        //Scanner scan = new Scanner(System.in);
        // 从键盘接收数据
        int inInt = 0;
        // nextLine方式接收字符串
        System.out.println(promptStr);
        // 判断是否还有输入
        if (scan.hasNextInt()) {
            inInt = scan.nextInt();
            scan.nextLine();// 因为读完int后其后 \0 会被读行识别，因此，解决冲突，在读完int后，再转至nextline
        }
        //scan.close();
        return inInt;
    }
}
