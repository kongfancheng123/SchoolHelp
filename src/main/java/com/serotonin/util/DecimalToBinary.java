package com.serotonin.util;

import java.math.BigInteger;

/**
 * Create by fchkong on 2018/11/15.
 */
public class DecimalToBinary {
    public static String decimalToBinary(Number decimalSource) {
        BigInteger bi = new BigInteger(String.valueOf(decimalSource));    //转换成BigInteger类型
        String result = bi.toString(2);
//        System.out.println(result.length());
        int i1 = 16 - result.length();
//        System.out.println(i1);
        if (bi.equals("0")) {
            result = "0000000000000000";
        } else {
            if (result.length() < 16) {
                for (int i = 0; i < i1; i++) {
                    result = "0" + result;
                }
            }
        }
        return result;    //参数2指定的是转化成X进制，默认10进制
    }
}
