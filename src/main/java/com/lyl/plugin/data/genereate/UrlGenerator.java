package com.lyl.plugin.data.genereate;


/**
 * @program: data-generate
 * @description: url
 * @author: shixing
 * @create: 2020-04-14 10:35
 **/
public class UrlGenerator {

    /**
     * 协议
     */
    public static String[] PROTOCOL_ARRY = {"http://","https://","ftp://","rtsp://","mms://"};


    /**
     *
     * @param protocol "http","https","ftp","rtsp","mms"
     * @param host www.baidu.com
     * @return
     */
    public static String getUrl(String protocol , String host){
        StringBuffer url = new StringBuffer();
        url.append(protocol).append("://")
                .append(host)
                .append("?")
                .append(CharNumberGenerator.generateLetterAndInt(9));
        return url.toString();
    }
    public static String getUrl(){
        StringBuffer url = new StringBuffer();
         url.append(PROTOCOL_ARRY[NumberGenerator.randomInt(PROTOCOL_ARRY.length-1)])
            .append("www.")
            .append(CharNumberGenerator.generateLetterAndInt(9))
            .append(".com")
            .append("?")
            .append(CharNumberGenerator.generateLetterAndInt(9));
       return url.toString();
    }

    /**
     *
     * @param host www.baidu.com
     * @return
     */
    public static String getUrl(String host){
        StringBuffer url = new StringBuffer();
        url.append(PROTOCOL_ARRY[NumberGenerator.randomInt(PROTOCOL_ARRY.length-1)])
                .append(host)
                .append("?")
                .append(CharNumberGenerator.generateLetterAndInt(9));
        return url.toString();
    }

}
