package com.hx.sellerCloud.action;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hx.sellerCloud.util.Constants;

import net.sf.json.JSONObject;

/**
 * FileName : Test01HelloWorld.java
 * Created by 970655147 on 2016/4/14/014 15:32.
 */
@RestController
@RequestMapping("/test01")
public class Test01HelloWorld {

    @RequestMapping
    public String hello() {
        return "Hello Spring-Boot";
    }

    @RequestMapping("/info")
    public String getInfo(@RequestParam String name) {
        return new JSONObject().element("name", name ).toString();
    }

    @RequestMapping(value = "/ls")
    public String ls() {
        return new JSONObject().element("name", "hx").toString();
    }
    
    @RequestMapping(value = {"/","/index"})
    public ModelAndView index(Map<String, Object> model){
        ModelAndView mv = new ModelAndView("HelloFreemarker");
        mv.addObject(Constants.TITLE, "Spring MVC And Freemarker");
        mv.addObject(Constants.CONTENT, " Hello world ， test my first spring mvc ! ");
        return mv;
    }

}
