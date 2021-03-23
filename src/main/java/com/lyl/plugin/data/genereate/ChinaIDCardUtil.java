package com.lyl.plugin.data.genereate;


import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 身份证号码
 * 1、号码的结构
 * 公民身份号码是特征组合码，由十七位数字本体码和一位校验码组成。排列顺序从左至右依次为：六位数字地址码，
 * 八位数字出生日期码，三位数字顺序码和一位数字校验码。
 * 2、地址码(前六位数）
 * 表示编码对象常住户口所在县(市、旗、区)的行政区划代码，按GB/T2260的规定执行。
 * 3、出生日期码（第七位至十四位）
 * 表示编码对象出生的年、月、日，按GB/T7408的规定执行，年、月、日代码之间不用分隔符。
 * 4、顺序码（第十五位至十七位）
 * 表示在同一地址码所标识的区域范围内，对同年、同月、同日出生的人编定的顺序号，
 * 顺序码的奇数分配给男性，偶数分配给女性。
 * 5、校验码（第十八位数）
 * （1）十七位数字本体码加权求和公式 S = Sum(Ai * Wi), i = 0, ... , 16 ，先对前17位数字的权求和
 * Ai:表示第i位置上的身份证号码数字值 Wi:表示第i位置上的加权因子 Wi: 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4
 * 2 （2）计算模 Y = mod(S, 11) （3）通过模得到对应的校验码 Y: 0 1 2 3 4 5 6 7 8 9 10 校验码: 1 0
 * X 9 8 7 6 5 4 3 2
 */
public class ChinaIDCardUtil {
    private ChinaIDCardUtil() {
    }

    /**
     * 15位身份证号
     */
    private static final Integer FIFTEEN_ID_CARD=15;
    /**
     * 18位身份证号
     */
    private static final Integer EIGHTEEN_ID_CARD=18;
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 生成身份号码
     * @return
     */
    public static String generateChineseIDCard() {
        Map<String, String> areacodeMap = IDCardAreaCode.getAreaCode();
        String[] keys = areacodeMap.keySet().toArray(new String[0]);
        Random random = new Random();
        String areaCodeRandomKey = keys[random.nextInt(keys.length)];
        if (areaCodeRandomKey.length()<6) {
            return null;
        }
        String birthday = new SimpleDateFormat("yyyyMMdd").format(randomDate());
        String randomCode = String.valueOf(1000 + RandomUtils.nextInt(0, 999))
            .substring(1);
        String pre = areaCodeRandomKey + birthday + randomCode;
        String verifyCode = getVerifyCode(pre);
        String result = pre + verifyCode;
        return result;
    }

    /**
     * 生成有效的生日
     * @return
     */
    public static Date randomDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1970, 1, 1);
        long earlierDate = calendar.getTime().getTime();
        calendar.set(2020, 1, 1);
        long laterDate = calendar.getTime().getTime();
        long chosenDate = RandomUtils.nextLong(earlierDate, laterDate);
        return new Date(chosenDate);
    }

    /**
     * @param beginDate 开始时间范围 yyyy-MM-dd
     * @param endDate  结束时间范围 yyyy-MM-dd
     * @return
     */
    public static Date getRandomDate(String beginDate, String endDate ){
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date start = format.parse(beginDate);//构造开始日期
            Date end = format.parse(endDate);//构造结束日期
            //getTime()表示返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。
            if(start.getTime() >= end.getTime()){
                return null;
            }
            long date = random(start.getTime(),end.getTime());
            return new Date(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static long random(long begin,long end){
        long rtn = begin + (long)(Math.random() * (end - begin));
        //如果返回的是开始时间和结束时间，则递归调用本函数查找随机值
        if(rtn == begin || rtn == end){
            return random(begin,end);
        }
        return rtn;
    }

    /**
     * 获得随机的性别
     * @return
     */
    public static String getRandomSex(){
        Random random = new Random();
        String sex=random.nextBoolean()?"男":"女";
        return sex;
    }


    /**
     * 生成校验码
     * @param cardId
     * @return
     */
    public static String getVerifyCode(String cardId) {
        String[] ValCodeArr = { "1", "0", "X", "9", "8", "7", "6", "5", "4",
            "3", "2" };
        String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
            "9", "10", "5", "8", "4", "2" };
        int tmp = 0;
        for (int i = 0; i < Wi.length; i++) {
            tmp += Integer.parseInt(String.valueOf(cardId.charAt(i)))
                * Integer.parseInt(Wi[i]);
        }
        int modValue = tmp % 11;
        String strVerifyCode = ValCodeArr[modValue];
        return strVerifyCode;
    }

    /**
     * 生成有效期限
     * Valid Through
     */
    public static String generateValidPeriod() {
        DateTime beginDate =new DateTime(randomDate()) ;
        String formater = "yyyy.MM.dd";
        DateTime endDate = beginDate.withYear(beginDate.getYear() + 20);
        return beginDate.toString(formater) + "-" + endDate.toString(formater);
    }

    /**
     * 身份证验证
     * @param id 号码内容
     * @return 是否有效
     */
    public static boolean isValid(String id){
        Boolean validResult = true;
        //校验长度只能为15或18
        int len = id.length();
        if (len != FIFTEEN_ID_CARD && len != EIGHTEEN_ID_CARD){
            validResult = false;
        }
        //校验生日
        if (!validDate(id)){
            validResult = false;
        }
        return validResult;
    }

    /**
     * 根据身份证号获取性别
     * @param IDCard
     * @return
     */
    public static String getSex(String IDCard){
        String sex ="";
        if (StringUtils.isNotBlank(IDCard)){
            //15位身份证号
            if (IDCard.length() == FIFTEEN_ID_CARD){
                if (Integer.parseInt(IDCard.substring(14, 15)) % 2 == 0) {
                    sex = "女";
                } else {
                    sex = "男";
                }
                //18位身份证号
            }else if(IDCard.length() == EIGHTEEN_ID_CARD){
                // 判断性别
                if (Integer.parseInt(IDCard.substring(16).substring(0, 1)) % 2 == 0) {
                    sex = "女";
                } else {
                    sex = "男";
                }
            }
        }
        return sex;
    }

    /**
     * 根据身份证号获取年龄
     * @param IDCard
     * @return
     */
    public static Integer getPersonAgeFromIdCard(String IDCard){
        Integer age = 0;
        Date date = new Date();
        if (StringUtils.isNotBlank(IDCard)&& isValid(IDCard)){
            //15位身份证号
            if (IDCard.length() == FIFTEEN_ID_CARD){
                // 身份证上的年份(15位身份证为1980年前的)
                String uyear = "19" + IDCard.substring(6, 8);
                // 身份证上的月份
                String uyue = IDCard.substring(8, 10);
                // 当前年份
                String fyear = format.format(date).substring(0, 4);
                // 当前月份
                String fyue = format.format(date).substring(5, 7);
                if (Integer.parseInt(uyue) <= Integer.parseInt(fyue)) {
                    age = Integer.parseInt(fyear) - Integer.parseInt(uyear) + 1;
                    // 当前用户还没过生
                } else {
                    age = Integer.parseInt(fyear) - Integer.parseInt(uyear);
                }
                //18位身份证号
            }else if(IDCard.length() == EIGHTEEN_ID_CARD){
                // 身份证上的年份
                String year = IDCard.substring(6).substring(0, 4);
                // 身份证上的月份
                String yue = IDCard.substring(10).substring(0, 2);
                // 当前年份
                String fyear = format.format(date).substring(0, 4);
                // 当前月份
                String fyue = format.format(date).substring(5, 7);
                // 当前月份大于用户出身的月份表示已过生日
                if (Integer.parseInt(yue) <= Integer.parseInt(fyue)) {
                    age = Integer.parseInt(fyear) - Integer.parseInt(year) + 1;
                    // 当前用户还没过生日
                } else {
                    age = Integer.parseInt(fyear) - Integer.parseInt(year);
                }
            }
        }
        return age;
    }



    /**
     * 获取出生日期  yyyy年MM月dd日
     * @param IDCard
     * @return
     */
    public static String getBirthday(String IDCard){
        String birthday="";
        String year="";
        String month="";
        String day="";
        if (StringUtils.isNotBlank(IDCard)){
            //15位身份证号
            if (IDCard.length() == FIFTEEN_ID_CARD){
                // 身份证上的年份(15位身份证为1980年前的)
                year = "19" + IDCard.substring(6, 8);
                //身份证上的月份
                month = IDCard.substring(8, 10);
                //身份证上的日期
                day= IDCard.substring(10, 12);
                //18位身份证号
            }else if(IDCard.length() == EIGHTEEN_ID_CARD){
                // 身份证上的年份
                year = IDCard.substring(6).substring(0, 4);
                // 身份证上的月份
                month = IDCard.substring(10).substring(0, 2);
                //身份证上的日期
                day=IDCard.substring(12).substring(0,2);
            }
            birthday= year + "-" + month + "-" + day;
        }
        return birthday;
    }



    /**
     * 校验生日
     * @param id
     * @return
     */
    private static boolean validDate(String id)
    {
        try
        {
            String birth = id.length() == 15 ? "19" + id.substring(6, 12) : id.substring(6, 14);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Date birthDate = sdf.parse(birth);
            if (!birth.equals(sdf.format(birthDate))){
                return false;
            }
        }
        catch (ParseException e)
        {
            return false;
        }
        return true;
    }


    /**
     * 获取身份证号码的出生年月日
     * @param chineseIDCard
     * @return
     */
    public static String extractYearMonthDayOfIdCard(String chineseIDCard) {
        String year = null;
        String month = null;
        String day = null;
        //正则匹配身份证号是否是正确的，15位或者17位数字+数字/x/X
        if (chineseIDCard.matches("^\\d{15}|\\d{17}[\\dxX]$")) {
            year = chineseIDCard.substring(6, 10);
            month = chineseIDCard.substring(10,12);
            day = chineseIDCard.substring(12,14);
        }else {
            System.out.println("身份证号码不匹配！");
            return null;
        }
            return year + "-" + month + "-" + day;
    }

    /**
     * 获取身份证有效期
     * @param chineseIDCard
     * @return
     */
    public static String getValidPeriodDate(String chineseIDCard) {
        int year;
        String month = null;
        String day = null;
        //正则匹配身份证号是否是正确的，15位或者17位数字+数字/x/X
        if (chineseIDCard.matches("^\\d{15}|\\d{17}[\\dxX]$")) {
            year = Integer.parseInt(chineseIDCard.substring(6, 10))+16;
            month = chineseIDCard.substring(10,12);
            day = chineseIDCard.substring(12,14);
        }else {
            System.out.println("身份证号码不匹配！");
            return null;
        }
        String startDate= year + "-" + month + "-" + day;
        DateTime beginDate =new DateTime(startDate) ;
        String formater = "yyyy.MM.dd";
        DateTime endDate = beginDate.withYear(beginDate.getYear() + 20);
        return beginDate.toString(formater) + "-" + endDate.toString(formater);
    }

    /**
    * @Author zgq
    * @Description  大学毕业时间
    * @Date 2020/4/19 下午4:47
    * @Param [chineseIDCard, collageTime]
    * @return java.lang.String
    **/
    public static String getCollageEndDate(String chineseIDCard, int collageTime) {
        int year;
        String month = null;
        String day = null;
        //正则匹配身份证号是否是正确的，15位或者17位数字+数字/x/X
        if (chineseIDCard.matches("^\\d{15}|\\d{17}[\\dxX]$")) {
            year = Integer.parseInt(chineseIDCard.substring(6, 10))+12;
            month = chineseIDCard.substring(10,12);
            day = chineseIDCard.substring(12,14);
        }else {
            System.out.println("身份证号码不匹配！");
            return null;
        }
        String startDate= year + "-" + month + "-" + day;
        DateTime beginDate =new DateTime(startDate) ;
        String formater = "yyyy.MM.dd";
        DateTime endDate = beginDate.withYear(beginDate.getYear() + collageTime);
        return endDate.toString(formater);
    }

    /**
     * 解析地址
     * @author lin
     * @param address
     * @return
     */
    public static List<Map<String, String>> addressResolution(String address){
        String regex="(?<province>[^省]+自治区|.*?省|.*?行政区|.*?市)(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)(?<county>[^县]+县|.+区|.+市|.+旗|.+海域|.+岛)?(?<town>[^区]+区|.+镇)?(?<village>.*)";
        Matcher m= Pattern.compile(regex).matcher(address);
        String province=null,city=null,county=null,town=null,village=null;
        List<Map<String, String>> table=new ArrayList<Map<String, String>>();
        Map<String, String> row=null;
        while(m.find()){
            row=new LinkedHashMap<String, String>();
            province=m.group("province");
            row.put("province", province==null?"":province.trim());
            city=m.group("city");
            row.put("city", city==null?"":city.trim());
            county=m.group("county");
            row.put("county", county==null?"":county.trim());
            town=m.group("town");
            row.put("town", town==null?"":town.trim());
            village=m.group("village");
            row.put("village", village==null?"":village.trim());
            table.add(row);
        }
        return table;
    }

    /**
     * 获取身份住址(随机组合)
     */
    public static String getIDAddressByID(String ID) {
        String[] area = {"伊春区","带岭区","南岔区","金山屯区","西林区","美溪区","乌马河区","翠峦区","友好区","新青区","上甘岭区","五营区","红星区","汤旺河区","乌伊岭区","榆次区"};
        String[] road = {"爱国路","安边路","安波路","安德路","安汾路","安福路","安国路","安化路","安澜路","安龙路","安仁路","安顺路","安亭路","安图路","安业路","安义路","安远路","鞍山路","鞍山支路","澳门路","八一路","巴林路","白城路","白城南路","白渡路","白渡桥","白兰路","白水路","白玉路","百安路（方泰镇）","百官街","百花街","百色路","板泉路","半淞园路","包头路","包头南路","宝安公路","宝安路","宝昌路","宝联路","宝林路","宝祁路","宝山路","宝通路","宝杨路","宝源路","保德路","保定路","保屯路","保屯路","北艾路",};
        String[] home = {"金色家园","耀江花园","阳光翠竹苑","东新大厦","溢盈河畔别墅","真新六街坊","和亭佳苑","协通公寓","博泰新苑","菊园五街坊","住友嘉馨名园","复华城市花园","爱里舍花园"};
        Random random = new Random();
        int randomAreaNum = random.nextInt(area.length);
        int randomRoadNum = random.nextInt(road.length);
        int randomHomeNum = random.nextInt(home.length);
        int num = random.nextInt(200);
        return IDCardAreaCode.getCodeAreaAddr(ID,6)+area[randomAreaNum]+road[randomRoadNum]+num+"号"+home[randomHomeNum];
    }


    /**
     * 获取身份证对应的地市(随机组合)
     *
     */
    public static String getIDCardRandomCityArea() {
        String[] area = {"伊春区","带岭区","南岔区","金山屯区","西林区","美溪区","乌马河区","翠峦区","友好区","新青区","上甘岭区","五营区","红星区","汤旺河区","乌伊岭区","榆次区"};
        Random random = new Random();
        int randomAreaNum = random.nextInt(area.length);
        return ProvinceAndCity.getCity()+"公安局"+area[randomAreaNum]+"分局";
    }

    /**
     * 根据身份证号码籍贯获取住址
     * @param chineseIDCardIssueOrg
     * @return
     */
    public static String getIDAddressByIssueOrg(String chineseIDCardIssueOrg) {
        String[] road = {"爱国路","安边路","安波路","安德路","安汾路","安福路","安国路","安化路","安澜路","安龙路","安仁路","安顺路","安亭路","安图路","安业路","安义路","安远路","鞍山路","鞍山支路","澳门路","八一路","巴林路","白城路","白城南路","白渡路","白渡桥","白兰路","白水路","白玉路","百安路（方泰镇）","百官街","百花街","百色路","板泉路","半淞园路","包头路","包头南路","宝安公路","宝安路","宝昌路","宝联路","宝林路","宝祁路","宝山路","宝通路","宝杨路","宝源路","保德路","保定路","保屯路","保屯路","北艾路",};
        String[] home = {"金色家园","耀江花园","阳光翠竹苑","东新大厦","溢盈河畔别墅","真新六街坊","和亭佳苑","协通公寓","博泰新苑","菊园五街坊","住友嘉馨名园","复华城市花园","爱里舍花园"};
        Random random = new Random();
        int randomRoadNum = random.nextInt(road.length);
        int randomHomeNum = random.nextInt(home.length);
        int num = random.nextInt(200);
        return chineseIDCardIssueOrg+road[randomRoadNum]+num+"号"+home[randomHomeNum];
    }


}
