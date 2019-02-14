package com.hyf.shardingsphere.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author howinfun
 * @version 1.0
 * @desc
 * @date 2018/11/26
 * @company XMJBQ
 */
@Data
public class ResponseJson implements Serializable {

    private Integer code = 200; // 请求结果 200:成功,400:失败,500:服务器错误
    private Boolean success = true; // 操作结果  true:成功,false:失败
    private Object data; // 存放操作成功后的数据
    private String msg = "操作成功"; // 提示信息
}
