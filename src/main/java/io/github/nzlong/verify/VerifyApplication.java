package io.github.nzlong.verify;

import com.blade.Blade;

/**
 * @author: nzlong
 * @description:
 * @date: Create in 2018 03 18 下午7:50
 */
public class VerifyApplication {

    public static void main(String[] args) {
        Blade.me()
             .start(VerifyApplication.class, args);
    }

}
