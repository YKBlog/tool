package image.util;

import java.io.OutputStream;
import java.util.Map;

import com.google.common.collect.Maps;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

/**
 * 
 * @Title: QRCodeUtil.java
 * @Description: <br>
 *               <br>��ά����ɹ���
 * @Created on 2016��3��12�� ����4:21:39
 * @author yangkai
 * @version 1.0
 */
public class QRCodeUtil {

    /**
     * ��ɶ�ά��ͼƬ��
     * @param content ��ά������
     * @param width ��
//     * @param height ��
     * @param out �����
     * @throws Exception
     */
    public static void writeImage(String content, int width, int height, OutputStream out) throws Exception {
        Map<EncodeHintType, String> hints = Maps.newHashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        MatrixToImageWriter.writeToStream(bitMatrix, "png", out);
    }
    
}