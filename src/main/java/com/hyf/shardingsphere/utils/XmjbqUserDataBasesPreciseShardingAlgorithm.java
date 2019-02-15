package com.hyf.shardingsphere.utils;

import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

import java.util.Collection;

/**
 * @author howinfun
 * @version 1.0
 * @desc 分库：精确分片算法，用于=和IN
 * @date 2019/2/14
 * @company XMJBQ
 */

public class XmjbqUserDataBasesPreciseShardingAlgorithm implements PreciseShardingAlgorithm<String> {

    /**
     *
     * @param collection  库名集合
     * @param preciseShardingValue 分片列
     * @return
     */
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<String> preciseShardingValue) {
        // 分片字段值
        String value = preciseShardingValue.getValue();
        // 现在算法是:%2 求余如果是0则ds0.xmjbq_user,如果是1则ds0.xmjbq_user。但是由于id是字符串而且是很长的，所以截取最后一位然后转为Integer类型再求余
        value = value.substring(value.length()-6,value.length()-3);
        Integer number = Integer.valueOf(value);
        int result = number % 2;
        for (String s : collection) {
            if(s.endsWith(result+"")){
                return s;
            }
        }
        throw new UnsupportedOperationException();
    }
}
