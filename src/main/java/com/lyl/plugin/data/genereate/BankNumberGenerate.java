package com.lyl.plugin.data.genereate;

import org.apache.commons.lang3.StringUtils;

import java.util.Random;

public class BankNumberGenerate {
    private BankNumberGenerate() {
    }
    /**
     * 从不含校验位的银行卡卡号采用 Luhn 校验算法获得校验位
     * 该校验的过程：
     * 1、从卡号最后一位数字开始，逆向将奇数位(1、3、5等等)相加。
     * 2、从卡号最后一位数字开始，逆向将偶数位数字，先乘以2（如果乘积为两位数，则将其减去9），再求和。
     * 3、将奇数位总和加上偶数位总和，结果应该可以被10整除。
     */
    public static int getLuhnSum(char[] chs) {
        int luhnSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhnSum += k;
        }
        return luhnSum;
    }

    public String generate() {
        Random random = new Random();
        Integer prev = 622126 + random.nextInt(925 + 1 - 126);
        return generateByPrefix(prev);
    }

    /**
     * <pre>
     * 根据给定前六位生成卡号
     * </pre>
     */
    public static String generateByPrefix(Integer prefix) {
        Random random = new Random(System.currentTimeMillis());
        String bardNo = prefix
            + StringUtils.leftPad(random.nextInt(999999999) + "", 9, "0");

        char[] chs = bardNo.trim().toCharArray();
        int luhnSum = getLuhnSum(chs);
        char checkCode = luhnSum % 10 == 0 ? '0' : (char) (10 - luhnSum % 10 + '0');
        return bardNo + checkCode;
    }

    /**
     * 根据银行名称 及银行卡类型生成对应卡号
     *
     * @param bankName 银行名称
     * @param cardType 银行卡类型
     * @return 银行卡号
     */
    public static String bankNumberGenerate(BankNameEnum bankName, BankCardTypeEnum cardType) {
        Integer[] candidatePrefixes = null;
        if (cardType == null) {
            candidatePrefixes = bankName.getAllCardPrefixes();
        } else {
            switch (cardType) {
                case DEBIT:
                    candidatePrefixes = bankName.getDebitCardPrefixes();
                    break;
                case CREDIT:
                    candidatePrefixes = bankName.getCreditCardPrefixes();
                    break;
                default:
            }
        }

        if (candidatePrefixes == null || candidatePrefixes.length == 0) {
            throw new RuntimeException("没有该银行的相关卡号信息");
        }

        Integer prefix = candidatePrefixes[new Random().nextInt(candidatePrefixes.length)];
        return generateByPrefix(prefix);
    }

    /**
     * 获取银行行号
     */
    public static String bankCodeGenerate(BankNameEnum bankName) {
            String bankCode = bankName.getCode();
        return bankCode;
    }
}
