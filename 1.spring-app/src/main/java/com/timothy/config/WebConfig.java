package com.timothy.config;


import com.timothy.utils.Mappings;
import com.timothy.utils.ViewNames;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController(Mappings.WELCOME).setViewName(ViewNames.WELCOME_VIEW);
        registry.addViewController(Mappings.REDIRECT).setViewName(ViewNames.REDIRECT_VIEW);
        registry.addViewController(Mappings.VIEW_DATA).setViewName(ViewNames.VIEW_DATA_VIEW);
    }
}