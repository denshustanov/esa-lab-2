package com.example.esalab2.entity.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.xslt.XsltViewResolver;

@Configuration
public class ViewConfiguration{

    @Bean
    public XsltViewResolver xsltViewResolver(){
        XsltViewResolver resolver = new XsltViewResolver();
        resolver.setPrefix("classpath:/xslt/");
        resolver.setSuffix(".xslt");
        return resolver;
    }

}
