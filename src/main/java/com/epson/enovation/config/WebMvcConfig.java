package com.epson.enovation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void  addResourceHandlers(ResourceHandlerRegistry registry) {
    	String rootPath = System.getProperty("user.dir");
    	rootPath= rootPath.replace("D:","");
    	rootPath= rootPath.replace("\\","/");
    	System.out.println(rootPath);
    	
        registry.addResourceHandler("/attach/images/**") // --1
                .addResourceLocations("file://"+rootPath+"/"); //--2
    }

}