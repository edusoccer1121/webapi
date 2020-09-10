package com.core.webapi.security;


import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

public class BCryptPasswordEncoderTest {
    private final Logger logger = LoggerFactory.getLogger(BCryptPasswordEncoder.class);

    @Test
    public void encryptTest(){
        String clearText = "abcd@123!";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        String encodedString = encoder.encode(clearText);
        logger.info(encodedString);
    }
    @Test
    public void decryptTest() {
        String encodedString = "$2a$12$/iw6LZvxrc0TsFZfp.cYXu0AXXrQSBQ5crmbN25.s3SDpBYe1Lwci";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

        boolean isMatch = encoder.matches("#5pR1nG8007$04u7h2@cL13n7!", encodedString);
        logger.info(String.valueOf(isMatch));
        assertThat(isMatch).isTrue();
    }
}
