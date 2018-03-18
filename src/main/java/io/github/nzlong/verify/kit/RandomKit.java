package io.github.nzlong.verify.kit;

import java.util.Random;

/**
 * @author: nzlong
 * @description:
 * @date: Create in 2018 03 18 下午7:59
 */
public class RandomKit {

    public static String getGoogleAuthenticationSeed(int length) {
        String[] str = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
                        "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
                        "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        Random random = new Random();
        String seed = "";
        for (int i = 0; i < length; i++) {
            seed += str[random.nextInt(str.length)];
        }
        return seed;
    }

    public static void main(String[] args) {
        System.out.println(getGoogleAuthenticationSeed(90));
    }

}
