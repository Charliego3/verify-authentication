package io.github.nzlong.verify.controller;

import com.blade.mvc.annotation.Path;
import com.blade.mvc.annotation.Route;
import com.blade.mvc.http.HttpResponse;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import io.github.nzlong.verify.kit.google.GoogleAuthentication;
import io.github.nzlong.verify.kit.MatrixToImageWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;

/**
 * @author: nzlong
 * @description:
 * @date: Create in 2018 03 18 下午7:51
 */
@Path
public class GoogleAuthenticationController {

    private final int width = 300;
    private final int height = 300;

    @Route
    public void getGoogleAuthenticationQr(HttpResponse response) {
        String secret = GoogleAuthentication.generateSecretKey();
        String url = "otpauth://totp/%s?secret=%s";
        String text = String.format(url, "nzlong", secret);
        System.out.println(text);
        Hashtable<EncodeHintType, String> hints = new Hashtable<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        try {
            response.header("Content-Type", "image/jpg");
            BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", os);
            byte[] bytes = os.toByteArray();
            System.out.println(bytes);
            response.outputStream().write(bytes);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }

}
