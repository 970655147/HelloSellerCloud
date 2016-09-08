/**
 * file name : InterceptorAdapter.java
 * created at : 下午7:21:04 2016年9月7日
 * created by 970655147
 */

package com.hx.sellerCloud.conf;

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import com.hx.sellerCloud.interceptor.UserInterceptor;

@Configuration
public class InterceptorAdapter extends WebMvcAutoConfigurationAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserInterceptor()).addPathPatterns("/action/user/*");
        super.addInterceptors(registry);
    }
    
}
