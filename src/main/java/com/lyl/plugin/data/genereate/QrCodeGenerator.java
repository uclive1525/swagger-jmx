package com.lyl.plugin.data.genereate;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Hashtable;

/**
 * @ClassName QrCodeGenerator
 * @Description 创建二维码工具类
 * @Author zgq
 * @Date 2020/4/19 下午5:43
 */
public class QrCodeGenerator {
    /**
     * 创建二维码图片
     *
     * @param content    二维码携带信息
     * @param qrCodeSize 二维码图片大小
     * @param filePathName   生成的二维码图片的保存的路径
     */
    public static String createQrCodeImage(String content, int qrCodeSize, String filePathName) {
        try {
            BufferedImage bi = createQrCode(content, qrCodeSize);
            File imgFile = new File(filePathName);
            ImageIO.write(bi, "JPEG", imgFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePathName;
    }

    /**
     * 生成包含字符串信息的二维码图片
     *
     * @param content    二维码携带信息
     * @param qrCodeSize 二维码图片大小
     */
    private static BufferedImage createQrCode(String content, int qrCodeSize) {
        try {
            // 设置二维码纠错级别Map
            Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
            // 矫错级别
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            // 创建比特矩阵(位矩阵)的QR码编码的字符串
            BitMatrix byteMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, qrCodeSize, qrCodeSize, hintMap);
            // 使BufferedImage勾画QRCode  (matrixWidth 是行二维码像素点)
            int matrixWidth = byteMatrix.getWidth();
            int matrixHeight = byteMatrix.getWidth();
            BufferedImage image = new BufferedImage(matrixWidth - 65, matrixWidth - 65, BufferedImage.TYPE_INT_RGB);
            image.createGraphics();
            Graphics2D graphics = (Graphics2D) image.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, matrixWidth, matrixHeight);
            // 使用比特矩阵画并保存图像
            graphics.setColor(Color.BLACK);
            for (int i = 0; i < matrixWidth; i++) {
                for (int j = 0; j < matrixWidth; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i - 33, j - 33, 2, 2);
                    }
                }
            }
            return image;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

