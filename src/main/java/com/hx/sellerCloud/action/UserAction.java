/**
 * file name : UserAction.java
 * created at : 下午5:00:31 2016年9月7日
 * created by 970655147
 */

package com.hx.sellerCloud.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hx.sellerCloud.bean.User;
import com.hx.sellerCloud.service.UserService;
import com.hx.sellerCloud.util.Constants;
import com.hx.sellerCloud.util.Tools;

import net.sf.json.JSONObject;

@RestController
@RequestMapping("/action/user")
public class UserAction {

    @Autowired
    private UserService userService;
	
	@RequestMapping("/findById")
	@ResponseBody
	public String findById(@RequestParam String id) {
		User user = userService.findById(id);
		if(user == null) {
			user = new User();
			user.setName("dummyName");
			user.set_desc("user not exists !");
		}
		
		return JSONObject.fromObject(user).toString();
	}
    
	@RequestMapping("/findAll")
	public ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("user/showUser");
        List<User> allUser = userService.findAll();
        
        mv.addObject("title", "ShowUser");
        mv.addObject("users", allUser);

        return mv;
	}
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public ModelAndView addUser(@RequestParam String name, @RequestParam String desc) {
		ModelAndView mv = new ModelAndView("result");
        JSONObject res = addUser0(name, desc);
        for(Object _key : res.names() ) {
        	String key = (String) _key;
        	mv.addObject(key, res.get(key) );
        }
        
        return mv;
	}
	
	@RequestMapping(value = "/addUserAsync", method = RequestMethod.POST)
	@ResponseBody
	public String addUserAsync(@RequestParam String name, @RequestParam String desc) {
		JSONObject res = addUser0(name, desc);
		return res.toString();
	}
	
	// 添加用户并返回结果信息
	private JSONObject addUser0(String name, String desc) {
		User user = new User();
		user.setId(null);
		user.setName(name);
		user.set_desc(desc);
		String now = Tools.now();
		user.setCreateDate(now);
		user.setLastLoginDate(null);
		user.setState(User.STAT_NORMAL);
		user.setType(User.TYPE_NORMAL);
		
		boolean success = true;
		try {
//			userService.insert(user);
			userService.saveOrUpdate(user);
		} catch (Exception e) {
			success = false;
			e.printStackTrace();
		}
		
		JSONObject res = new JSONObject();
        res.element(Constants.TITLE, "Result");
        res.element(Constants.OPERATION, "addUser");
        if(success) {
        	res.element(Constants.STATUS, "success");
        	res.element(Constants.CONTENT, "66666666666666666666666666");
        } else {
        	res.element(Constants.STATUS, "failed");
        	res.element(Constants.CONTENT, "server response error, please contact administrator !");
        }
        
        return res;
	}
	
	@RequestMapping(value = "/sealUser", method = RequestMethod.POST )
	public ModelAndView sealUser(@RequestParam String id) {
		return sealUnSealUser(id, true);
	}
	
	@RequestMapping(value = "/unsealUser", method = RequestMethod.POST )
	public ModelAndView unsealUser(@RequestParam String id) {
		return sealUnSealUser(id, false);
	}
	
	private ModelAndView sealUnSealUser(String id, boolean sealUser) {
		ModelAndView mv = new ModelAndView("result");
        
		boolean success = true;
        String errMsg = null;
        try {
        	User user = userService.findById(id);
        	if(user == null) {
        		success = false;
        		errMsg = "have no this user !";
        	} else {
	        	if(sealUser) {
	        		if(user.getState() == User.STAT_SEAL) {
	        			success = false;
	        			errMsg = "this user's state already be SEALED !";
	        		} else {
	        			userService.sealUser(id);
	        		}
	        	} else {
	        		if(user.getState() == User.STAT_NORMAL) {
	        			success = false;
	        			errMsg = "this user's state already be NORMAL !";
	        		} else {
	        			userService.unsealUser(id);
	        		}
	        	}
        	}
        } catch (Exception e) {
			success = false;
			errMsg = "server response error, please contact administrator !";
			e.printStackTrace();
		}
        
        mv.addObject(Constants.TITLE, "Result");
        if(sealUser) {
        	mv.addObject(Constants.OPERATION, "sealUser");
        } else {
        	mv.addObject(Constants.OPERATION, "unSealUser");
        }
        if(success) {
        	mv.addObject(Constants.STATUS, "success");
        	mv.addObject(Constants.CONTENT, "66666666666666666666666666");
        } else {
        	mv.addObject(Constants.STATUS, "failed");
        	mv.addObject(Constants.CONTENT, errMsg);
        }

        return mv;
	}
	
	@RequestMapping(value = "/removeUser", method = RequestMethod.POST )
	public ModelAndView removeUser(@RequestParam String id) {
		ModelAndView mv = new ModelAndView("result");
		
		boolean success = true;
		String errMsg = null;
		try {
			User user = userService.findById(id);
			if(user == null) {
				success = false;
				errMsg = "have no this user !";
			} else {
				userService.removeById(id);
			}
		} catch (Exception e) {
			success = false;
			errMsg = "server response error, please contact administrator !";
			e.printStackTrace();
		}
		
		mv.addObject(Constants.TITLE, "Result");
		mv.addObject(Constants.OPERATION, "removeUser");
		if(success) {
			mv.addObject(Constants.STATUS, "success");
			mv.addObject(Constants.CONTENT, "66666666666666666666666666");
		} else {
			mv.addObject(Constants.STATUS, "failed");
			mv.addObject(Constants.CONTENT, errMsg);
		}
		
		return mv;
	}
	
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST )
	public ModelAndView updateUser(@RequestParam String id, @RequestParam String name, @RequestParam String desc) {
		ModelAndView mv = new ModelAndView("result");
		
		boolean success = true;
		String errMsg = null;
		try {
			User user = userService.findById(id);
			if(user == null) {
				success = false;
				errMsg = "have no this user !";
			} else {
				user.setName(name);
				user.set_desc(desc);
//				userService.update(user);
				userService.saveOrUpdate(user);
			}
		} catch (Exception e) {
			success = false;
			errMsg = "server response error, please contact administrator !";
			e.printStackTrace();
		}
		
		mv.addObject(Constants.TITLE, "Result");
		mv.addObject(Constants.OPERATION, "updateUser");
		if(success) {
			mv.addObject(Constants.STATUS, "success");
			mv.addObject(Constants.CONTENT, "66666666666666666666666666");
		} else {
			mv.addObject(Constants.STATUS, "failed");
			mv.addObject(Constants.CONTENT, errMsg);
		}
		
		return mv;
	}
	
	@RequestMapping(value = "/queryUser", method = RequestMethod.POST)
	public ModelAndView queryUser(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("user/showUser");
        
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String start = request.getParameter("start");
        String end = request.getParameter("end");
        String type = request.getParameter("type");
        
        List<User> allUser = userService.findBy(id, name, start, end, type);
//        System.out.println(id + " - " + name + " - " + start + " - " + end + " - " + type);
        
        mv.addObject("title", "ShowUser");
        mv.addObject("users", allUser);

        return mv;
	}
	
}
