package cn.cc.dawn.local.qrcode.util;

import cn.cc.dawn.utils.enums.CharsetsEnum;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.SneakyThrows;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class QRWrite {

    public static void main(String[] args) {

        String param = null;
        try {
            param = "https://www.baidu.com/s?wd=" + URLEncoder.encode( "傻逼", CharsetsEnum.UTF_8.charset.toString());

            //param = URLEncoder.encode( "傻逼", CharsetsEnum.UTF_8.charset.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int weight = 100;
        int height = 100;
        writeQRCode(param,weight,height);
    }

    private static String filePath = "E:\\filesystem\\test\\qrcode\\" + System.currentTimeMillis() + ".png";;

    /**
     * 生成二维码
     * @param params
     * @param widthHeight
     * @param height
     */
    @SneakyThrows
    public static void writeQRCode(String params, int widthHeight, int height){

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        /**
         * 返回的已经是图片了
         */
        BitMatrix bitMatrix = qrCodeWriter.encode(params, BarcodeFormat.QR_CODE,widthHeight,height);


        /**
         * 这里可以放一个位置，或者一个流
         */
        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix,"PNG",path);
        OutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix,"PNG",outputStream);
        System.out.println("完成");

    }

}
