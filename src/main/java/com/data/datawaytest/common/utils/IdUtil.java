package com.data.datawaytest.common.utils;

/** @author Baijl
 * @date 2020-04-21
 * @time 16:14
 * @description
 */
public class IdUtil {

    public static String getId(){
       return Long.toString(new SnowflakeIdWorker().nextId());
    }
}
