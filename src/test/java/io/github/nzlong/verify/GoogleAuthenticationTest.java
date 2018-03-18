package io.github.nzlong.verify;

import io.github.nzlong.verify.kit.GoogleAuthentication;
import org.junit.Test;

/**
 * @author: nzlong
 * @description:
 * @date: Create in 2018 03 18 下午9:19
 */
public class GoogleAuthenticationTest {

    @Test
    public void t() {
        System.out.println(GoogleAuthentication.generateSecretKey());
    }

}
