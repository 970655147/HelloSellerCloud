/**
 * file name : UserInterceptor.java
 * created at : 下午7:22:20 2016年9月7日
 * created by 970655147
 */

package com.hx.sellerCloud.interceptor;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hx.sellerCloud.util.Tools;

// /action/user/*
public class UserInterceptor implements HandlerInterceptor {

	// 需要校验id的"接口"
	List<String> chkIds = Arrays.asList("findById", "sealUser", "unsealUser", "removeUser", "updateUser" );
	List<String> chkNameAndDesc = Arrays.asList("addUser", "updateUser" );
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println(request.getRequestURI() + " -- 参数校验, 预处理 等等逻辑");
        HandlerMethod method = (HandlerMethod) handler;
        String methodName = method.getMethod().getName();
        
        // 这里只做简单的校验
        boolean valid = true;
        if(chkIds.contains(methodName) ) {
        	try {
        		Integer.valueOf(request.getParameter("id") );
        		// NPE | NFE
        	} catch (Exception e) {
        		valid = false;
        	}
        }
        if(chkNameAndDesc.contains(methodName) ) {
        	if(Tools.isEmpty(request.getParameter("name")) ) {
        		valid = false;
        	}
        }
        if("queryUser".equals(methodName) ) {
        	String id = request.getParameter("id");
        	String start = request.getParameter("start"), end = request.getParameter("end");
        	try {
        		if(! Tools.isEmpty(start) ) {
        			Tools.parse(start);
        		}
        		if(! Tools.isEmpty(end) ) {
        			Tools.parse(end);
        		}
        		
        		if(! Tools.isEmpty(id) ) {
        			Integer.parseInt(id);
        		}
        		// must with type in normal case
        		int type = Integer.parseInt(request.getParameter("type") );
        		if((type != -1) && (type != 0) && (type != 1) ) {
        			valid = false;
        		}
        	} catch (Exception e) {
        		valid = false;
        	}
        }
        
        if(! valid) {
        	response.sendError(404);
        }
        return valid;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
//    	System.out.println("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    	System.out.println(request.getRequestURI() + " -- 结果处理 等等逻辑");
    }
	
}
