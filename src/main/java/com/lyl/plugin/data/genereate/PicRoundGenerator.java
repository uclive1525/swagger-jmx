package com.lyl.plugin.data.genereate;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @ClassName PicRoundUtils
 * @Description TODO
 * @Author zgq
 * @Date 2020/4/24 下午4:01
 */
public class PicRoundGenerator {
    /***
     * @param srcFilePath 源图片文件路径
     * @param circularImgSavePath 新生成的图片的保存路径，需要带有保存的文件名和后缀
     * @param targetSize 文件的边长，单位：像素，最终得到的是一张正方形的图，所以要求targetSize<=源文件的最小边长
     * @param cornerRadius 圆角半径，单位：像素 如果=targetSize那么得到的是圆形图
     * @return  文件的保存路径
     * @throws IOException
     */
    public static String getCircularImg(String srcFilePath, String circularImgSavePath, int targetSize, String type, int cornerRadius) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new File(srcFilePath));
        BufferedImage circularBufferImage = roundImage(bufferedImage,targetSize,cornerRadius);
        ImageIO.write(circularBufferImage, type, new File(circularImgSavePath));
        return circularImgSavePath;
    }

    /**
    * @Author zgq
    * @Description  生成圆角
    * @Date 2020/4/24 下午6:00
    * @Param [image, targetSize, cornerRadius]
    * @return java.awt.image.BufferedImage
    **/
    public static BufferedImage roundImage(BufferedImage image, int targetSize, int cornerRadius) {
        BufferedImage outputImage = new BufferedImage(targetSize, targetSize, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = outputImage.createGraphics();
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(0, 0, targetSize, targetSize, cornerRadius, cornerRadius));
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
        return outputImage;
    }
    /**
     * 生成圆角图标
     * @param image
     * @param cornerRadius 圆角半径
     * @return
     */
    public static BufferedImage makeRoundedCorner1(BufferedImage image, int cornerRadius) {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = output.createGraphics();
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
        return output;
    }

    /**
    * @Author zgq
    * @Description  TODO
    * @Date 2020/4/24 下午5:14
    * @Param [srcImageFilePath, cornerRadius, outPutPathName]
    * @return java.lang.String
    **/
    public static String getPicRoundedCorner(String srcImageFilePath, int cornerRadius, String type, String outPutPathName) throws IOException {
        BufferedImage image = ImageIO.read(new File(srcImageFilePath));
        BufferedImage rounded = makeRoundedCorner1(image, cornerRadius);
        ImageIO.write(rounded, type, new File(outPutPathName));
        return outPutPathName ;
    }

    /**
    * @Author zgq
    * @Description  图片圆角处理，背景透明化
    * @Date 2020/4/24 下午5:59
    * @Param [srcImageFilePath, cornerRadius]
    * @return java.awt.image.BufferedImage
    **/
    public static BufferedImage makeRoundedCorner(String srcImageFilePath, int cornerRadius) throws IOException {
            BufferedImage bi1 = ImageIO.read(new File(srcImageFilePath));
            // 根据需要是否使用 BufferedImage.TYPE_INT_ARGB
            BufferedImage image = new BufferedImage(bi1.getWidth(), bi1.getHeight(),
                BufferedImage.TYPE_INT_ARGB);
            Ellipse2D.Double shape = new Ellipse2D.Double(0, 0, bi1.getWidth(), bi1.getHeight());
            Graphics2D g2 = image.createGraphics();
            image = g2.getDeviceConfiguration().createCompatibleImage(bi1.getWidth(), bi1.getHeight(), Transparency.TRANSLUCENT);
            g2 = image.createGraphics();
            g2.setComposite(AlphaComposite.Clear);
            g2.fill(new Rectangle(image.getWidth(), image.getHeight()));
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC, 1.0f));
            g2.setClip(shape);
            // 使用 setRenderingHint 设置抗锯齿
            g2 = image.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.fillRoundRect(0, 0,bi1.getWidth(), bi1.getHeight(), cornerRadius, cornerRadius);
            g2.setComposite(AlphaComposite.SrcIn);
            g2.drawImage(bi1, 0, 0, bi1.getWidth(), bi1.getHeight(), null);
            g2.dispose();
//            ImageIO.write(image, type, result);
            return image;
    }
    /**
    * @Author zgq
    * @Description  TODO
    * @Date 2020/4/24 下午6:10
    * @Param [srcImageFilePath, cornerRadius, type, outPutPathName]
    * @return java.lang.String
    **/
    public static String getPicRoundedCorner2(String srcImageFilePath, int cornerRadius, String type, String outPutPathName) throws IOException {
        BufferedImage rounded = makeRoundedCorner(srcImageFilePath, cornerRadius);
        ImageIO.write(rounded, type, new File(outPutPathName));
        return outPutPathName ;
    }
}
