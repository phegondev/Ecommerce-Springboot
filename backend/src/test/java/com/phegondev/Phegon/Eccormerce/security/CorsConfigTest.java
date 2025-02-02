package com.phegondev.Phegon.Eccormerce.security;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.junit.jupiter.api.Assertions.*;

class CorsConfigTest {

    @Test
    void testCorsConfig() {
        ApplicationContext context = new AnnotationConfigApplicationContext(CorsConfig.class);
        WebMvcConfigurer webMvcConfigurer = context.getBean(WebMvcConfigurer.class);

        assertNotNull(webMvcConfigurer);
    }
}
