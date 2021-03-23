package com.lyl.plugin.data.genereate;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserInfoGenerate {

    /**
     * 生成csv文件
    * @Author zgq
    * @Description  TODO
    * @Date 2020/4/20 下午5:32
    * @Param [rowNumS]
    * @return java.util.List
    **/
    public static List getDataLists(int rowNumS) {
        List<List> dataLists = new ArrayList<List>();
        for(int i= 0; i<rowNumS; i++){
            List dataList = getBaseInfoList();
            dataLists.add(dataList);
        }
        return dataLists;
    }

    /**
    * @Author zgq
    * @Description  TODO
    * @Date 2020/4/20 下午5:55
    * @Param [rowNumS]
    * @return java.util.List
    **/
    public static List getDataListsByMap(int rowNumS) {
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        for(int i= 0; i<rowNumS; i++){
            Map dataMap = getBaseInfo();
            dataList.add(dataMap);
        }
        return dataList;
    }

    /**
     * map对象转换成list
     * @param infoMap
     * @return
     */
    public static List getBaseInfoList(Map infoMap) {
        List<String> infoList= new ArrayList(infoMap.values());
        return infoList;
    }

    /**
        * @Author zgq
        * @Description  TODO
        * @Date 2020/4/20 下午5:51
        * @Param []
        * @return java.util.List
        **/
    public static List getBaseInfoList() {
        List<String> infoList = new ArrayList<>();
        //生成身份证号码
        String ChinaIDCardNumber = ChinaIDCardUtil.generateChineseIDCard();
        //姓名
        String chineseName = UserNameGenerator.getRandomNameByChinese(3);
        //根据身份证号获取性别
        String sex = ChinaIDCardUtil.getSex(ChinaIDCardNumber);
        String nation = "汉";
        //年龄
        Integer age = ChinaIDCardUtil.getPersonAgeFromIdCard(ChinaIDCardNumber);
        //根据身份证获取生日
        String birthday = ChinaIDCardUtil.getBirthday(ChinaIDCardNumber) ;
        String[] birthdaySubStr = birthday.split("-");
        //身份证有效期
        String chineseIDCardValidPeriod = ChinaIDCardUtil.getValidPeriodDate(ChinaIDCardNumber);
        //身份证籍贯
        String IDCardAreaCodeAddr= IDCardAreaCode.generateIssueOrg(ChinaIDCardNumber,6);
        //身份证地址
        String homeAddress = ChinaIDCardUtil.getIDAddressByID(ChinaIDCardNumber);
        //身份证正面图片
        String IDCardPicZhengPath= ChinaIDCardPicGenerator.getZhengPicByBase64(chineseName,sex,nation,birthdaySubStr[0],birthdaySubStr[1],birthdaySubStr[2],homeAddress,ChinaIDCardNumber);
        //身份证反面图片
        String IDCardPicFanPath = ChinaIDCardPicGenerator.getIDCardPicByFan(IDCardAreaCodeAddr,chineseIDCardValidPeriod,chineseName);
        //专业
        String professionalName = UserNameGenerator.getCollageProfessional();
        //毕业时间
        String collageEndDate = ChinaIDCardUtil.getCollageEndDate(ChinaIDCardNumber,3);
        //毕业证编号
        int  collageNo = NumberGenerator.randomInt(100000000,999999999);
        //账号信息
        String tel = PhoneGenerator.getTel();
        String Password = NumberGenerator.generatePassword(8);
        String email = EmailGenerator.getEmail(6,9);
        //银行卡号
        String bankNumber = BankNumberGenerate.bankNumberGenerate(BankNameEnum.ICBC, BankCardTypeEnum.DEBIT);
        String bankCode = BankNumberGenerate.bankCodeGenerate(BankNameEnum.SHB);
        //描述
        String descChinese = CharNumberGenerator.generateInRadomLengthJianHan(2,20);

        infoList.add(chineseName);
        infoList.add(ChinaIDCardNumber);
        infoList.add(sex);
        infoList.add(nation);
        infoList.add(String.valueOf(age) );
        infoList.add(birthday);
        infoList.add(chineseIDCardValidPeriod);
        infoList.add(IDCardPicZhengPath);
        infoList.add(IDCardPicFanPath);
        infoList.add(professionalName);

        infoList.add(tel);
        infoList.add(email);
        infoList.add(bankNumber);
        infoList.add(bankCode);
        infoList.add(descChinese);

        return infoList;
    }

    /**
     * 用户基本信息数据封装
     * @return
     */
    public static Map getBaseInfo() {
        Map<String, Object> infoMap = new HashMap<>();
        //生成身份证号码
        String ChinaIDCardNumber = ChinaIDCardUtil.generateChineseIDCard();
        //姓名
        String chineseName = UserNameGenerator.getRandomNameByChinese(3);
        infoMap.put("chineseName",chineseName);
        //根据身份证号获取性别
        String sex = ChinaIDCardUtil.getSex(ChinaIDCardNumber);
        infoMap.put("sex",sex );
        String nation = "汉";
        infoMap.put("nation",nation );
        //年龄
        Integer age = ChinaIDCardUtil.getPersonAgeFromIdCard(ChinaIDCardNumber);
        infoMap.put("age",age );
        //根据身份证获取生日
        String birthday = ChinaIDCardUtil.getBirthday(ChinaIDCardNumber); ;
//        String birthday = ChinaIDCardUtil.extractYearMonthDayOfIdCard(ChinaIDCardNumber) ;
        String[] birthdaySubStr = birthday.split("-");
        infoMap.put("birthday",birthday);
        //身份证号码
        infoMap.put("ChinaIDCardNumber", ChinaIDCardNumber);
        //身份证有效期
        String chineseIDCardValidPeriod = ChinaIDCardUtil.getValidPeriodDate(ChinaIDCardNumber);
        infoMap.put("chineseIDCardValidPeriod",chineseIDCardValidPeriod);
        //身份证籍贯
        String IDCardAreaCodeAddr= IDCardAreaCode.generateIssueOrg(ChinaIDCardNumber,6);
        infoMap.put("IDCardAreaCodeAddr",IDCardAreaCodeAddr);
        //身份证地址
        String homeAddress = ChinaIDCardUtil.getIDAddressByID(ChinaIDCardNumber);
        infoMap.put("homeAddress",homeAddress);
        //身份证正面图片
        String IDCardPicZhengPath= ChinaIDCardPicGenerator.getZhengPicByBase64(chineseName,sex,nation,birthdaySubStr[0],birthdaySubStr[1],birthdaySubStr[2],homeAddress,ChinaIDCardNumber);
        infoMap.put("IDCardPicZheng", IDCardPicZhengPath);
        //身份证反面图片
        String IDCardPicFanPath = ChinaIDCardPicGenerator.getIDCardPicByFan(IDCardAreaCodeAddr,chineseIDCardValidPeriod,chineseName);
        infoMap.put("IDCardPicFanPath", IDCardPicFanPath);
        //账号信息
        infoMap.put("professionalName", UserNameGenerator.getCollageProfessional());
        //毕业时间
        infoMap.put("collageEndDate", ChinaIDCardUtil.getCollageEndDate(ChinaIDCardNumber,3));
        //毕业证编号
        infoMap.put("collageNo", NumberGenerator.randomInt(100000000,999999999));
        infoMap.put("tel", PhoneGenerator.getTel());
        infoMap.put("Password", NumberGenerator.generatePassword(8));
        infoMap.put("email", EmailGenerator.getEmail(6,9));
        //银行卡号
        infoMap.put("bankNumber", BankNumberGenerate.bankNumberGenerate(BankNameEnum.ICBC, BankCardTypeEnum.DEBIT));
        infoMap.put("bankCode", BankNumberGenerate.bankCodeGenerate(BankNameEnum.SHB));
        infoMap.put("descChinese", CharNumberGenerator.generateInRadomLengthJianHan(2,20));

        return infoMap;
    }

}
