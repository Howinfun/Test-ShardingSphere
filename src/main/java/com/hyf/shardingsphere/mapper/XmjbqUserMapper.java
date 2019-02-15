package com.hyf.shardingsphere.mapper;

import com.hyf.shardingsphere.entity.XmjbqUser;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.BaseMapper;

/**
 * @author howinfun
 * @version 1.0
 * @desc 用户
 * @date 2018/12/10
 * @company XMJBQ
 */
@Mapper
public interface XmjbqUserMapper extends BaseMapper<XmjbqUser> {

    @Select("select * from xmjbq_user where phone = #{phone}")
    public XmjbqUser findUserByPhone(@Param("phone") String phone);

    @Update("update xmjbq_user set user_name = #{userName} where id = #{id}")
    public Integer updateUserNameById(@Param("userName") String userName,@Param("id") String id);

    @Update("update xmjbq_user set user_name = #{userName} where phone = #{phone}")
    public Integer updateUserNameByPhone(@Param("userName") String userName,@Param("phone") String phone);

    @Delete("delete from xmjbq_user where phone = #{phone}")
    public Integer deleteUserByPhone(@Param("phone") String phone);
}
