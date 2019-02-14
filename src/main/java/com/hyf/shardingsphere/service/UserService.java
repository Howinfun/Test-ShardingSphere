package com.hyf.shardingsphere.service;

import com.hyf.shardingsphere.common.ResponseJson;
import com.hyf.shardingsphere.entity.Login;

/**
 * @author howinfun
 * @version 1.0
 * @desc
 * @date 2019/2/13
 * @company XMJBQ
 */
public interface UserService {

    public ResponseJson insertUser(Login login);
}
