package cn.cust.user.remote;

import cn.cust.netty.annotation.Remote;
import cn.cust.netty.medium.ResponseUtil;
import cn.cust.user.model.User;
import cn.cust.user.service.UserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhangbing on 2018/4/24.
 */
@Remote
public class UserRemoteImpl implements UserRemote {

    @Resource
    private UserService userService;

    @Override
    public Object saveUser(User user) {
        userService.save(user);
        return ResponseUtil.createFailResult("1000","success");
    }

    @Override
    public Object saveUsers(List<User> users) {
        return null;
    }
}
