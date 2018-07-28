package io.github.nzlong.verify.kit.imageCode;

import java.awt.*;
import java.util.Random;

/**
 *
 * 随机生产6位验证码 数字保存在session中,图片返回给前段展示
 *
 * @author: nzlong
 * @description:
 * @date: Create in 2018 03 18 下午9:03
 */
public class ValidateCode {

    /**
     * 放到session中的key
     */
    public static final String RANDOMCODEKEY = "RANDOMVALIDATECODEKEY";

    private static Random random = new Random();

    /**
     * 随机产生的字符串去掉0和O
     */
    private static String randString = "123456789ABCDEFGHIJKLMNPQRSTUVWXYZ";

    public static int width = 85;// 图片宽
    public static int height = 35;// 图片高
    public static int lineSize = 40;// 干扰线数量
    public static int stringNum = 4;// 随机产生字符数量

    /**
     * 获得字体
     */
    private static Font getFont() {
        return new Font("Fixedsys", Font.CENTER_BASELINE, 18);
    }

    /**
     * 获得颜色
     */
    public static Color getRandColor(int fc, int bc) {
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc - 16);
        int g = fc + random.nextInt(bc - fc - 14);
        int b = fc + random.nextInt(bc - fc - 18);
        return new Color(r, g, b);
    }

    /**
     * 绘制字符串
     */
    public static String drowString(Graphics g, String randomString, int i) {
        g.setFont(getFont());
        g.setColor(new Color(random.nextInt(101), random.nextInt(111), random
                .nextInt(121)));
        String rand = String.valueOf(getRandomString(random.nextInt(randString.length())));
        randomString += rand;
        g.translate(random.nextInt(3), random.nextInt(3));
        g.drawString(rand, 13 * i, 16);
        return randomString;
    }

    /**
     * 绘制干扰线
     */
    public static void drowLine(Graphics g) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.drawLine(x, y, x + xl, y + yl);
    }

    /**
     * 获取随机的字符
     */
    public static String getRandomString(int num) {
        return String.valueOf(randString.charAt(num));
    }

}
