package com.hyf.shardingsphere.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author howinfun
 * @version 1.0
 * @desc
 * @date 2018/12/10
 * @company XMJBQ
 */
@Data
public class Login implements Serializable {
    private String phone; // 手机号码
    private String code; // 验证码
    private String os; // 操作系统
    private String osVersion; // 操作系统版本号
    private String deviceMode; // 设备型号
    private String appStore;// 应用商场来源
    private String fromInvitationCode; // 邀请码
    private String name; // 昵称
}
