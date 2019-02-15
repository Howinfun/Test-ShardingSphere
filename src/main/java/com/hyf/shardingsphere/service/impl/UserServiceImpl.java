package com.hyf.shardingsphere.service.impl;

import com.hyf.shardingsphere.common.ResponseJson;
import com.hyf.shardingsphere.entity.Login;
import com.hyf.shardingsphere.entity.XmjbqUser;
import com.hyf.shardingsphere.mapper.XmjbqUserMapper;
import com.hyf.shardingsphere.service.UserService;
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
    // 本地事务
    //@ShardingTransactionType(TransactionType.LOCAL)
    // 两阶段事务（支持夸库事务）
    @ShardingTransactionType(TransactionType.XA)
    @Transactional
    public ResponseJson insertUser(Login login) {
        ResponseJson responseJson = new ResponseJson();
        try{
            String phone = login.getPhone();
            XmjbqUser user = new XmjbqUser();
            //SnowFlakeUtil.initWorkerIdByIPKeyGenerator();
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

    @Override
    @ShardingTransactionType(TransactionType.XA)
    @Transactional
    public ResponseJson updateUserUserNameByPhone(Login login) {
        ResponseJson responseJson = new ResponseJson();
        try{
            String phone = login.getPhone();
            String userName = login.getName();
            XmjbqUser user = userMapper.findUserByPhone(phone);
            user.setUserName(userName);
            // 需要注意：如果只是分表或者分库，记得是根据分片字段是更新数据，如果是分表分库是根据分表的分片字段去更新数据，不然会出现路由失败的情况，即更新数据失败
            //userMapper.updateByPrimaryKeySelective(user);
            //userMapper.updateUserNameById(userName,user.getId());
            userMapper.updateUserNameByPhone(userName,phone);
            // 测试出现异常是否会回滚
            //int result = 10/0;
        }catch (Exception e){
            logger.error("更新用户昵称报错：",e);
            responseJson.setSuccess(false);
            responseJson.setCode(500);
            responseJson.setMsg("服务器报错");
            return responseJson;
        }
        return responseJson;
    }

    @Override
    @ShardingTransactionType(TransactionType.XA)
    @Transactional
    public ResponseJson deleteUserByPhone(Login login) {
        ResponseJson responseJson = new ResponseJson();
        try{
            String phone = login.getPhone();
            userMapper.deleteUserByPhone(phone);
        }catch (Exception e){
            logger.error("删除用户报错：",e);
            responseJson.setSuccess(false);
            responseJson.setCode(500);
            responseJson.setMsg("服务器报错");
            return responseJson;
        }
        return responseJson;
    }

    @Override
    @ShardingTransactionType(TransactionType.XA)
    @Transactional
    public ResponseJson findUserByPhone(Login login) {
        ResponseJson responseJson = new ResponseJson();
        try{
            String phone = login.getPhone();
            XmjbqUser user = userMapper.findUserByPhone(phone);
            responseJson.setData(user);
        }catch (Exception e){
            logger.error("查询用户信息报错：",e);
            responseJson.setSuccess(false);
            responseJson.setCode(500);
            responseJson.setMsg("服务器报错");
            return responseJson;
        }
        return responseJson;
    }
}
