package com.xjr.miniapi;

import com.xjr.miniapi.controller.MiniInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    // 创建虚拟目录将资源以url形式映射

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //"/**"表示会处理所有url路径的资源分配
        //放置到resources目录和haitao_dev目录下
        registry.addResourceHandler("/**")
                //Swagger静态资源的路径
                .addResourceLocations("classpath:/META-INF/resources/")
                //windows目录下虚拟目录
                .addResourceLocations("file:D:/haitao_dev/");
//        .addResourceLocations("file:/haitao_dev/"); //linux
    }

    @Bean
    public MiniInterceptor miniInterceptor() {
        return new MiniInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

//        registry.addInterceptor(miniInterceptor()).addPathPatterns("/user/**")
//                .addPathPatterns("/video/uploadVideo", "/video/uploadCover", "/video/userLike", "/video/userUnLike", "/video/saveComment")
//                .addPathPatterns("/bgm/**")
//                .excludePathPatterns("/user/queryPublisher");

        super.addInterceptors(registry);
    }
}
