/**
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.varela.open.id;

/**
 * 分布式全局唯一ID生成器<br>
 * <p>
 * 结构：id(64bit long) = timestamp(41bit 70years) + biz(7bit)+ machine(4bit) + state(4bit) + sequence(8bit)<br>
 * <br>
 * （1）41bit高段区存储距初始时间毫秒间隔。41bit可以存储70年时差，此值可满足99.9%的系统寿命<br>
 * （2）7bit中段区存储业务排它号,大小区间0-127，可以最大限度100%保证128个业务在同一毫秒绝对不重复<br>
 * （3）4bit中低段区存储机器排它编号，大小区间0-15，可以最大限度100%保证16个机器在同一毫秒绝对不重复<br>
 * （4）4bit低段中区存储状态排它编号，大小区间0-15，可以最大限度100%保证16个状态下在同一毫秒绝对不重复<br>
 * （5）8bit低低段区存储自增排它数值，大小区间0-255，可以最大限度100%保证以上4个指标相同情况下绝对不重复<br>
 * <br>
 * <br>
 * 1、优点：<br>
 * （1）符合时间序列，自增性非随机性；<br>
 * （2）分布式下全局重复概率极低；<br>
 * （3）根据ID数值可反算各个指标值；<br>
 * <p>
 * 2、缺点：<br>
 * （1）使用期限70年；<br>
 * （2）存在冲突可能，概率多少没仔细算过；<br>
 * （3）低段位小，排它度不高；<br>
 */
public interface UniqueidService {

    /**
     * 生成唯一ID
     *
     * @return
     */
    long generateId();

    /**
     * 解析唯一ID
     *
     * @param id
     * @return
     */
    UniqueElement explainId(long id);
}
