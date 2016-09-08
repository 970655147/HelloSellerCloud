package com.hx.sellerCloud.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * FileName : SpringBootSimpleApp.java
 * Created by 970655147 on 2016/4/14/014 15:30.
 */
@SpringBootApplication
@ComponentScan(basePackages={"com.hx.sellerCloud.*" } )
public class SpringBootSimpleApp {

	// start server
    public static void main(String[] args) {
    	
        SpringApplication.run(SpringBootSimpleApp.class, args);
        
    }

}
