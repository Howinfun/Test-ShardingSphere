package com.hyf.shardingsphere.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author howinfun
 * @version 1.0
 * @desc APP开关配置
 * @date 2018/12/11
 * @company XMJBQ
 */
@Data
@Table(name="xmjbq_switch_configure")
public class XmjbqSwitchConfigure implements Serializable {
    @Id
    private String id;
    private String name; // 名称
    private String code; // 代码
    private Integer isEnabled; // 是否启用
}
