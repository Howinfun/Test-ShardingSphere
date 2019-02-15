package com.hyf.shardingsphere.mapper;

import com.hyf.shardingsphere.entity.XmjbqUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.BaseMapper;

/**
 * @author howinfun
 * @version 1.0
 * @desc 用户
 * @date 2018/12/10
 * @company XMJBQ
 */
@Mapper
@Component("userMapper")
public interface XmjbqUserMapper extends BaseMapper<XmjbqUser> {

    @Select("select * from xmjbq_user where phone = #{phone}")
    public XmjbqUser findUserByPhone(@Param("phone") String phone);

    @Delete("delete from xmjbq_user where phone = #{phone}")
    public Integer deleteUserByPhone(@Param("phone") String phone);
}
