package com.hyf.shardingsphere.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author howinfun
 * @version 1.0
 * @desc 用户
 * @date 2018/12/10
 * @company XMJBQ
 */
@Data
@Table(name="xmjbq_user")
public class XmjbqUser implements Serializable {

    @Id
    private String id;
    private String userName; // 昵称
    private String realName; // 真实姓名
    private String identify; // 身份证号码
    private String phone; // 手机号码
}
