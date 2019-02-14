package com.hyf.shardingsphere.utils;

import cn.hutool.core.util.IdUtil;

/**
 * @author howinfun
 * @version 1.0
 * @desc
 * @date 2018/12/24
 * @company XMJBQ
 */
public class UUIDUtil {

    /**
     * 带-的UUID字符串
     * @return
     */
    public static String randomUUID(){
        return IdUtil.randomUUID();
    }
    /**
     * 不带-的UUID字符串，32位
     * @return
     */
    public static String simpleUUID(){
        return IdUtil.simpleUUID();
    }
}
