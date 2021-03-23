package com.lyl.plugin.data.genereate;

import nl.flotsam.xeger.Xeger;

/**
 * 根据微服务网关接口参数校验格式 生成对应的测试数据
 *
 * @Author: pengyangyang
 * @Date: 2020/04/13 17:29
 */
public class AccordingToRegexpGenerator {

    /**
     * IDS
     *
     * @return
     */
    public static String ids() {
        String regex = "[0-9a-zA-Z_,-]+";
        Xeger generator = new Xeger(regex);
        return generator.generate();
    }

    /**
     * ID
     *
     * @return
     */
    public static String id() {
        String regex = "[0-9a-zA-Z_-]+";
        Xeger generator = new Xeger(regex);
        return generator.generate();
    }

    /**
     * 长名字(2~50)
     *
     * @return
     */
    public static String nameLong() {
        String regex = "[\u4E00-\u9FA5]{2,50}";
        Xeger generator = new Xeger(regex);
        return generator.generate();

    }

    /**
     * 名字(2~8)
     *
     * @return
     */
    public static String name() {
        String regex = "[\u4E00-\u9FA5]{2,8}";
        Xeger generator = new Xeger(regex);
        return generator.generate();

    }

    /**
     * 数字
     *
     * @return
     */
    public static String num() {
        String regex = "-?[0-9]+(.[0-9]+)?";
        Xeger generator = new Xeger(regex);
        return generator.generate();

    }

    /**
     * 账号(1~18位)
     *
     * @return
     */
    public static String loginName() {
        String regex = "[a-zA-Z0-9$_]{1,18}";
        Xeger generator = new Xeger(regex);
        return generator.generate();

    }

    /**
     * 密码(6~16位)
     *
     * @return
     */
    public static String passWord() {
        String regex = "[\\!-\\~]{6,16}";
        Xeger generator = new Xeger(regex);
        return generator.generate();

    }

    /**
     * 手机号1开头
     *
     * @return
     */
    public static String phone() {
        String regex = "1([0-9]){10}";
        Xeger generator = new Xeger(regex);
        return generator.generate();

    }

    /**
     * 邮件
     *
     * @return
     */
    public static String email() {
        String regex = "\\w+[@][a-zA-Z0-9_]+(\\.[a-zA-Z0-9_]+)+";
        Xeger generator = new Xeger(regex);
        return generator.generate();

    }

    /**
     * 验证码
     *
     * @return
     */
    public static String verifyCode() {
        String regex = "([0-9a-zA-Z]){4,6}";
        Xeger generator = new Xeger(regex);
        return generator.generate();

    }

    /**
     * URL
     *
     * @return
     */
    public static String url() {
        String regex = "(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        Xeger generator = new Xeger(regex);
        return generator.generate();

    }



    /**
     * 中文
     *
     * @return
     */
    public static String chinese() {
        String regex = "[\u4E00-\u9FA5]+";
        Xeger generator = new Xeger(regex);
        return generator.generate();

    }

    /**
     * 地址
     *
     * @return
     */
    public static String address() {
        String regex = "[\u4E00-\u9FA5|\\(\\)\\s\\-|0-9|a-zA-Z]+";
        Xeger generator = new Xeger(regex);
        return generator.generate();

    }

    /**
     * CARNO
     *
     * @return
     */
    public static String carNo() {
        String regex = "[\u4E00-\u9FA5A-Z]{1}[A-Z]{1}[A-Z0-9]{5}";
        Xeger generator = new Xeger(regex);
        return generator.generate();

    }

    /**
     * 签名
     *
     * @return
     */
    public static String signature() {
        String regex = "[0-9a-zA-Z]+";
        Xeger generator = new Xeger(regex);
        return generator.generate();

    }

    /**
     * 银行行号
     *
     * @return
     */
    public static String bankNo() {
        String regex = "[0-9a-zA-Z]+";
        Xeger generator = new Xeger(regex);
        return generator.generate();

    }

    /**
     * 银行卡号
     *
     * @return
     */
    public static String bankCardNo() {
        String regex = "[0-9]{16,19}";
        Xeger generator = new Xeger(regex);
        return generator.generate();

    }

}
