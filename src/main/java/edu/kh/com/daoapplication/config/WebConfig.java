package edu.kh.com.daoapplication.config;


import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // 웹 관련 설정 Springboot 프로젝트가 컴퓨터에 직접적으로 접근 할 수 있도록 설정
public class WebConfig implements WebMvcConfigurer {

    @Value("${upload-img}")
    private String uploadImg; // C:/book-image-path 들어있는상태


    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // resource 경로에 저장된 파일을 직접적으로 접근
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:"+uploadImg + "/");
    }
}
