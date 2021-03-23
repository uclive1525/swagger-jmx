package com.lyl.plugin.data.genereate;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;

/**
 * @ClassName ImageReorganization
 * @Description 合成资格证图片
 * @Author zgq
 * @Date 2020/4/19 下午2:29
 */
public class CertificateImageGenerator {
    /**
    * @Author zgq
    * @Description  样板模板图片＋证书头像合成
    * @Date 2020/4/19 下午2:48
    * @Param [backgroundPath, qrCodePath, message01, message02, outPutPath]
    * @return java.lang.String
    **/
    public static String getCertificateImage(String backgroundPath, String touxiangImagePath, Map userInfoMap, String outPutPath){
        //合成图片名称
        String outPutPathName= outPutPath+userInfoMap.get("chineseName").toString()+"资格证.jpg";
        try {
            //头像
            BufferedImage touxiangImage = getTouXiang(userInfoMap.get("sex").toString(),"zmtouxiang",touxiangImagePath);
            //背景图片
            BufferedImage background = resizeImage(510,360, ImageIO.read(new File(backgroundPath)));
            //在背景图片中添加入需要写入的信息，
            Graphics2D g = background.createGraphics();
            g.setColor(Color.BLACK);
            g.setFont(new Font("华文细黑", Font.BOLD,10));
            //绘图drawString位置的确定
            //姓名
            g.drawString(userInfoMap.get("chineseName").toString(),345 ,175);
            //证件号码
            g.drawString(userInfoMap.get("ChinaIDCardNumber").toString(),345 ,190);
            //性别
            g.drawString(userInfoMap.get("sex").toString(),345 ,210);
            //出生日期
            g.drawString(userInfoMap.get("birthday").toString(),345 ,230);
            //专业
            g.drawString(userInfoMap.get("professionalName").toString(),345 ,245);
            //批准日期
            g.drawString(userInfoMap.get("collageEndDate").toString(),345 ,265);
            //管理号
            g.drawString(userInfoMap.get("collageNo").toString(),345 ,285);
            //在背景图片上添加图片:设置图片存放位置　340, 50； x 离左边x的距离,Y 上边Y的距离
            g.drawImage(touxiangImage, 335, 40, touxiangImage.getWidth(), touxiangImage.getHeight(), null);
            g.dispose();
            ImageIO.write(background, "jpg", new File(outPutPathName));
        }catch (Exception e){
            e.printStackTrace();
        }
        return outPutPathName;
    }

    /**
    * @Author zgq
    * @Description  TODO
    * @Date 2020/4/19 下午5:15
    * @Param [sex, zmTouXiangName, touxiangPath]
    * @return java.awt.image.BufferedImage
    **/
    public static BufferedImage getTouXiang(String sex, String zmTouXiangName, String touxiangPath) throws Exception {
        String zmTouXiangPicName="";
        if ("男".equals(sex)) {
            zmTouXiangPicName=zmTouXiangName+"-boy-100-125.jpg";
        }else {
            zmTouXiangPicName=zmTouXiangName+"-girl-100-125.jpg";
        }
        //头像路径
        String xinPianTouXiangPicPath = new StringBuffer(System.getProperty("user.dir")).append("/").append(touxiangPath).append(zmTouXiangPicName).toString();
        File photoimage = new File(xinPianTouXiangPicPath);
        //设置头像的尺寸大小 80,90
        BufferedImage photoImage = resizeImage(80,90, ImageIO.read(photoimage));
        //头像透明化
        BufferedImage bufferedImage = ChinaIDCardPicGenerator.transferAlpha(photoImage);
        return bufferedImage;
    }

    /**
    * @Author zgq
    * @Description  TODO
    * @Date 2020/4/19 下午2:48
    * @Param [x, y, bfi]
    * @return java.awt.image.BufferedImage
    **/
    public static BufferedImage resizeImage(int x, int y, BufferedImage bfi){
        BufferedImage bufferedImage = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
        bufferedImage.getGraphics().drawImage(
            bfi.getScaledInstance(x, y, Image.SCALE_SMOOTH), 0, 0, null);
        return bufferedImage;
    }

}
