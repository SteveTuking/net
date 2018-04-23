package cn.cust.user.controller;

import cn.cust.netty.annotation.Action;
import cn.cust.netty.medium.ResponseUtil;
import cn.cust.netty.param.Response;
import cn.cust.user.model.User;
import cn.cust.user.service.UserService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class UserController {

	@Resource
	private UserService userService;

	public Response saveUser(User user){
		userService.save(user);
		return ResponseUtil.createSuccessResult(user);
	}

	public Response saveUsers(List<User> users){
		userService.saveList(users);
		return ResponseUtil.createSuccessResult(users);
	}

}
