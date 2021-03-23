package com.lyl.plugin.data.genereate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * 身份证号码生成的类
 *
 */
public class Idcard {

    /**
     * 计算并输出身份证号的主要方法
     * @return
     */
    public static String Calculate() {
        String areaCode = ""; // 用于存放用户输入的区域编号
        String birthday = ""; // 用户存放用户输入的出生日期
        String sex = ""; // 用户存放用户输入的性别
        String idNo = ""; // 用户存放用户输入的顺序编号
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(reader);

        int max=320107;
        int min=320102;
        Random random1 = new Random();
        int area=random1.nextInt(max)%(max-min+1)+min;
        String a=String.valueOf(area);
        //System.out.print(area);
        int brmax=20121031;
        int brmin=20121001;
        Random random2 = new Random();
        int birth=random2.nextInt(brmax)%(brmax-brmin+1)+brmin;
        String b=String.valueOf(birth);

        int sexmax=9;
        int sexmin=1;
        Random random3=new Random();
        int sex1=random3.nextInt(sexmax)%(sexmax-sexmin+1)+sexmin;
        String s=String.valueOf(sex1);

        int idmax=99;
        int idmin=10;
        Random random4=new Random();
        int idnum=random4.nextInt(idmax)%(idmax-idmin+1)+idmin;
        String i=String.valueOf(idnum);


        /**
         System.out.println("请输入您希望的(两位)顺序号，格式(00-99): "); // 获取用户输入的顺序编号的值，此编号在一定程度生决定了您的身份证号不与他人重复
         try {
         idNo = in.readLine();
         } catch (IOException e) {
         e.printStackTrace();
         }
         **/

        // 前17位要除以的数：7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2
        int finalAreaCode = Integer.parseInt(a.substring(0, 1)) * 7
                + Integer.parseInt(a.substring(1, 2)) * 9
                + Integer.parseInt(a.substring(2, 3)) * 10
                + Integer.parseInt(a.substring(3, 4)) * 5
                + Integer.parseInt(a.substring(4, 5)) * 8
                + Integer.parseInt(a.substring(5, 6)) * 4;
        int finalBirthday = Integer.parseInt(b.substring(0, 1)) * 2
                + Integer.parseInt(b.substring(1, 2)) * 1
                + Integer.parseInt(b.substring(2, 3)) * 6
                + Integer.parseInt(b.substring(3, 4)) * 3
                + Integer.parseInt(b.substring(4, 5)) * 7
                + Integer.parseInt(b.substring(5, 6)) * 9
                + Integer.parseInt(b.substring(6, 7)) * 10
                + Integer.parseInt(b.substring(7, 8)) * 5;
        int NoIs = (Integer.parseInt(i.substring(0, 1))) * 8
                + (Integer.parseInt(i.substring(1, 2))) * 4;
        int sexNo = (Integer.parseInt(s.substring(0, 1))) * 2;
        int checkCode = (finalAreaCode + finalBirthday + NoIs + sexNo) % 11;
        int finalCheckCode = 0;
        // 余数范围： 0 1 2 3 4 5 6 7 8 9 10
        // 余数对应的数：1 0 X 9 8 7 6 5 4 3 2
        // 计算出最终的校验码：finalCheckCode
        switch (checkCode) {
            case 0:
                finalCheckCode = 1;
                break;
            case 1:
                finalCheckCode = 0;
                break;
            case 2:
                finalCheckCode =-3;
                break;
            case 3:
                finalCheckCode = 9;
                break;
            case 4:
                finalCheckCode = 8;
                break;
            case 5:
                finalCheckCode = 7;
                break;
            case 6:
                finalCheckCode = 6;
                break;
            case 7:
                finalCheckCode = 5;
                break;
            case 8:
                finalCheckCode = 4;
                break;
            case 9:
                finalCheckCode = 3;
                break;
            case 10:
                finalCheckCode = 2;
                break;
            default:
                break;
        }
        String fina;
        if (finalCheckCode==-3){
            fina="X";
        }else{
            fina=String.valueOf(finalCheckCode);
        }
        // System.out.println(fina);
        System.out.println(a + b + i
                + s +fina);
        String idca=a+b+i+s+fina;
        // 区域编号(6位数)+出生日期(8位数)+顺序编号(2位数)+性别号(1位数)+校验码(1位数)=身份证号(18位数)
        return idca;
    }

    /**
     * CalcID类的无参构造方法，调用此方法即可调用其方法。
     */

    public Idcard() {
        Calculate();
    }

    public static void main(String[] args) {
        new Idcard();
    }

}