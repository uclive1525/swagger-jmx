package com.lyl.plugin.data.genereate;

import java.util.UUID;

/**
 * @author niaoshuai
 */
public class UuidGenerator {

    /**
     * 随机生成UUID
     *
     * @param is32Bit
     * @return
     */
    public static String generateUuid(Boolean is32Bit){
        String uuid= UUID.randomUUID().toString();
        if(is32Bit){
            return uuid.replace("-","");
        }
        return  uuid;
    }

}
