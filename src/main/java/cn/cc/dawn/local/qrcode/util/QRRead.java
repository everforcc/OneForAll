package cn.cc.dawn.local.qrcode.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class QRRead {

    private static String filePath = "E:\\filesystem\\test\\qrcode\\";
    private static String fileName = "1652339795714.png";
    public static void main(String[] args) {
        readQRCode(filePath + fileName);
    }

    @SneakyThrows
    public static void readQRCode(String path){

        BufferedImage bufferedImage = ImageIO.read(new File(path));
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);

        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(source));
        Map<DecodeHintType,String> map = new HashMap<>();
        map.put(DecodeHintType.CHARACTER_SET,"utf-8");

        Result result = new MultiFormatReader().decode(binaryBitmap,map);
        System.out.println("解析结果: " + result.toString());
        System.out.println("二维码格式类型: " + result.getBarcodeFormat());
        System.out.println("二维码文本内容 : " + result.getText());
        System.out.println("二维码文本内容 : " + URLDecoder.decode(result.getText(),"UTF-8"));

    }

}
