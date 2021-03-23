package com.lyl.plugin.utils;

public class Random {
    public String randomdota (String type,String vname) {
        String randomdota = null;
        if (("integer").equals(type)) {
            randomdota = "${__Random(1,100,)}";
        }
        else if (vname.contains("Date")){
            randomdota="${__time(yyyy-MM-dd HH:mm:ss,)}";
        }
        else {
            randomdota="${__RandomString(10,abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789,randomString)}";
        }
        return randomdota;
    }
}
