package cn.cust.netty.user.controller;

import cn.cust.netty.annotation.Action;
import cn.cust.netty.user.model.User;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
	
	
	@Action("saveUser")
	public Object save(User user){
		System.out.println(user.getName());
		return user.getName();
	}

}
