package com.hyf.shardingsphere.controller;

import com.hyf.shardingsphere.common.ResponseJson;
import com.hyf.shardingsphere.entity.Login;
import com.hyf.shardingsphere.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author howinfun
 * @version 1.0
 * @desc 用户Controller
 * @date 2018/12/10
 * @company XMJBQ
 */
@RestController
@RequestMapping("user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("insertUser")
    public ResponseJson insertUser(@RequestBody Login login){
        ResponseJson responseJson = new ResponseJson();
        try {
            responseJson = userService.insertUser(login);
        }catch (Exception e){
            logger.error("添加新用户：",e);
            responseJson.setSuccess(false);
            responseJson.setCode(500);
            responseJson.setMsg("服务器出错");
            return responseJson;
        }
        return responseJson;
    }

    public static void main(String[] args) {
        int sum = 0*0;
    }
}
