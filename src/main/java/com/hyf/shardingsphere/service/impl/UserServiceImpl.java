package com.hyf.shardingsphere.service.impl;

import com.hyf.shardingsphere.common.ResponseJson;
import com.hyf.shardingsphere.entity.Login;
import com.hyf.shardingsphere.entity.XmjbqUser;
import com.hyf.shardingsphere.mapper.XmjbqUserMapper;
import com.hyf.shardingsphere.service.UserService;
import com.hyf.shardingsphere.utils.SnowFlakeUtil;
import io.shardingsphere.core.constant.transaction.TransactionType;
import io.shardingsphere.core.keygen.DefaultKeyGenerator;
import io.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author howinfun
 * @version 1.0
 * @desc
 * @date 2019/2/13
 * @company XMJBQ
 */
@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private XmjbqUserMapper userMapper;

    @Override
    @ShardingTransactionType(TransactionType.XA)
    @Transactional
    public ResponseJson insertUser(Login login) {
        ResponseJson responseJson = new ResponseJson();
        try{
            String phone = login.getPhone();
            XmjbqUser user = new XmjbqUser();
            SnowFlakeUtil.initWorkerIdByIPKeyGenerator();
            // Sharding-JDBC采用snowflake算法作为默认的分布式分布式自增主键策略，用于保证分布式的情况下可以无中心化的生成不重复的自增序列。
            //因此自增主键可以保证递增，但无法保证连续。而snowflake算法的最后4位是在同一毫秒内的访问递增值。
            // 因此，如果毫秒内并发度不高，最后4位为零的几率则很大。
            // 因此并发度不高的应用生成偶数主键的几率会更高。
            DefaultKeyGenerator keyGenerator = new DefaultKeyGenerator();
            user.setId(keyGenerator.generateKey().toString());
            user.setPhone(phone);
            user.setIdentify("");
            user.setRealName("");
            user.setUserName("");
            userMapper.insert(user);
        }catch (Exception e){
            logger.error("添加新用户报错：",e);
            responseJson.setSuccess(false);
            responseJson.setCode(500);
            responseJson.setMsg("服务器报错");
            return responseJson;
        }
        return responseJson;
    }
}
