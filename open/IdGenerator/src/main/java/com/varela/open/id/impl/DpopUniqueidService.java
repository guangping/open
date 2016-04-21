package com.varela.open.id.impl;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import com.varela.open.id.StateService;
import com.varela.open.id.SysBizService;
import com.varela.open.id.UniqueElement;
import com.varela.open.id.UniqueidService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 此组件是一个“分布式全局唯一ID生成器”的设计。
 * ID的Bit结构：id(64bit long) = timestamp(41bit 70years) + biz(7bit)+ machine(4bit ) + state(4bit) + sequence(8bit)
 * 其中ID组成描述类为：IdElement.java
 * id生成和解析 接口是：IdService.java
 * 实现类是UnbizIdService.java，测试类为 UnbizI dServiceTest.java
 * biz(7bit)部分的生成接口是：SysBizService.java
 * 实现是DefaultSysBizService.java
 * state(4bit)部分的生成接口是 StateService.java 实现是DefaultStateService.java 单
 * 测类为DefaultStateServiceTest.java
 */
public final class DpopUniqueidService implements UniqueidService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DpopUniqueidService.class);

    /**
     * 默认起始时间,2015年
     */
    private static final int DEFAULT_YEAR = 2015;

    /**
     * 总字节数目
     */
    private static final int TOTAL_BIT = 64;

    /**
     * 时间差值存储长度
     */
    private static final int MAX_TIME_TAMP_BIT = 41;

    /**
     * 时间差值左移位数
     */
    private static final int IME_TAMP_LEFT_BIT = TOTAL_BIT - MAX_TIME_TAMP_BIT;

    /**
     * 业务排他值存储长度
     */
    private static final int MAX_BIZ_BIT = 7;

    /**
     * 业务排他值左移位数
     */
    private static final int BIZ_LEFT_BIT = IME_TAMP_LEFT_BIT - MAX_BIZ_BIT;

    /**
     * 机器排他值存储长度
     */
    private static final int MAX_MAC_BIT = 4;

    /**
     * 机器排他值左移位数
     */
    private static final int MAC_LEFT_BIT = BIZ_LEFT_BIT - MAX_MAC_BIT;

    /**
     * 状态排他值存储长度
     */
    private static final int MAX_STATE_BIT = 4;

    /**
     * 状态排他值左移位数
     */
    private static final int STATE_LEFT_BIT = MAC_LEFT_BIT - MAX_STATE_BIT;

    /**
     * 自增值存储长度
     */
    private static final int MAX_SEQ_BIT = 8;

    /**
     * 自增值最大长度
     */
    private static final int MAX_SEQ_NUM = 255;

    /**
     * 自增值复位时，系统处理线程休眠时间，单位：毫秒
     */
    private static final long SEQ_RESET_SLEEP_TIME = 1L;

    /**
     * MAC地址后3位扩展字段
     */
    private int macExtId;

    /**
     * 初始化时间
     */
    private Date startDate;

    /**
     * 自增序列
     */
    private AtomicInteger sequence = new AtomicInteger(0);

    private long crrentTime = 0L;

    /**
     * 业务ID获取器
     */
    private SysBizService sysBizService;

    /**
     * 业务状态ID获取器
     */
    private StateService stateService;

    public DpopUniqueidService() {
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        initStartDate();
        if (null == sysBizService) {
            // bizService 实际使用的时候可以通过重写sysBizService，并注入Override
            sysBizService = new DefaultSysBizService();
        }
        if (null == stateService) {
            stateService = new DefaultStateService();
        }
        try {
            macExtId = getMacExtId();
        } catch (Exception e) {
            LOGGER.error("InetAddressHepler.getMacExtId() has error:", e);
            macExtId = 0;
        }
    }

    /**
     * 初始化初始时间,默认初始化时间为 2015年1月1日 零时
     */
    private void initStartDate() {
        if (null == startDate) {
            Calendar startDateCalendar = Calendar.getInstance();
            startDateCalendar.set(DEFAULT_YEAR, 0, 1, 0, 0, 0);
            startDate = startDateCalendar.getTime();
        }
    }

    /**
     * 生成唯一编号
     */
    @Override
    public final long generateId() {
        long timestamp = getTimestamp();
        long bizId = Math.abs(sysBizService.getBiz());
        long stateId = Math.abs(stateService.getState());

        long generateId = timestamp << IME_TAMP_LEFT_BIT;

        // bizId % 128 得到2的8次幂（0xxxxxxx）对应的（低位包含7bit，其他为为0）数字
        // 然后左移16位，使得7位map到最终值对应的7bit的位置，然后做亦或
        generateId |= (bizId % 128) << BIZ_LEFT_BIT;

        // 机器标识ID，取的是运行所在机器的mac地址映射的4位bit
        generateId |= (macExtId % 16) << MAC_LEFT_BIT;

        // 状态标识ID，默认set为线程相关，根据线程ID来Map对应后四位
        generateId |= (stateId % 16) << STATE_LEFT_BIT;

        // 自增序号ID
        generateId |= sequence.incrementAndGet() % MAX_SEQ_NUM;

        return generateId;
    }

    ;

    /**
     * 解释唯一ID
     *
     * @param id：分布式唯一ID
     * @return 分布式ID的组成元素，定义于 @see IdElement
     * @see
     */
    @Override
    public final UniqueElement explainId(long id) {
        UniqueElement uniqueElement = new UniqueElement();
        long generateStartNum = 1L << IME_TAMP_LEFT_BIT;

        // 如果小于其实时间的，说明是不符号id生成策略，返回一个空对象

        if (id <= generateStartNum) {
            throw new RuntimeException("illegal id");
        }
        // id向右移动 64 - 41 位
        long timestamp = id >> IME_TAMP_LEFT_BIT;
        uniqueElement.setTimestamp(timestamp);

        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTimeInMillis(startDate.getTime() + timestamp);
        uniqueElement.setTimestampDate(dateCalendar.getTime());

        // id向先右位移(64 - 41 - 7)位，再向左位移(64 - 7) ，最后无符号右位移(64 - 7)
        long bizId = id >> BIZ_LEFT_BIT << (TOTAL_BIT - MAX_BIZ_BIT) >>> (TOTAL_BIT - MAX_BIZ_BIT);
        uniqueElement.setBizId(bizId);

        // id向先右位移(64 - 41 - 7 - 4)位，再向左位移64 - 4 ，最后无符号右位移64 - 4
        long macExt = id >> MAC_LEFT_BIT << (TOTAL_BIT - MAX_MAC_BIT) >>> (TOTAL_BIT - MAX_MAC_BIT);
        uniqueElement.setMachineId(macExt);

        // id向先右位移(64 - 41 - 7 - 4 -4 )位，再向左位移64 - 4 ，最后无符号右位移64 - 4
        long stateId = id >> STATE_LEFT_BIT << (TOTAL_BIT - MAX_STATE_BIT) >>> (TOTAL_BIT - MAX_STATE_BIT);
        uniqueElement.setStateId(stateId);

        // id向先右位移(64 - 41 - 7 - 4 -4 -8)位，再向左位移64 - 8 ，最后无符号右位移64 - 8
        long sequenceId = id << (TOTAL_BIT - MAX_SEQ_BIT) >>> (TOTAL_BIT - MAX_SEQ_BIT);
        uniqueElement.setSequence(sequenceId);

        return uniqueElement;
    }

    private long getTimestamp() {
        if (null == startDate) {
            initStartDate();
        }

        // 当自增原子字段sequence值到MAX_SEQ_NUM时，重新set为0
        if (sequence.compareAndSet(MAX_SEQ_NUM, 0)) {

            // 如果存在这种极限情况（同一时刻点，sequence所有的自增值都用完，这个时候休眠一毫秒，如高核高CPU的机器）
            // 当随机自增号复位时，当前时间需等一毫秒，尽量保证ID是随时间序列递增
            while (System.currentTimeMillis() == crrentTime) {
                try {
                    Thread.sleep(SEQ_RESET_SLEEP_TIME);
                } catch (InterruptedException e) {
                    LOGGER.error(String.format("sequence is [%d] reset Thread.sleep(%d) has error", MAX_SEQ_NUM,
                            SEQ_RESET_SLEEP_TIME), e);
                }
            }
        }
        crrentTime = System.currentTimeMillis();
        return crrentTime - startDate.getTime();
    }

    /**
     * 服务所在机器MAC 映射为一个唯一的数字
     * eg mac 地址：A4-4E-31-83-AE-8C
     */
    public static int getMacExtId() throws Exception {
        // 获取机器的本地IP地址
        InetAddress inetAddress = InetAddress.getLocalHost();
        NetworkInterface networkInterface = NetworkInterface.getByInetAddress(inetAddress);
        // 得到本机的MAC地址
        byte[] mac = networkInterface.getHardwareAddress();

        // mac地址映射为一个唯一的integer数字
        int macExtId = 0;
        for (int index = 3; index < mac.length; index++) {
            // 字节转换为整数
            int temp = mac[index] & 0xff;
            macExtId |= temp << (8 * (5 - index));
        }
        return macExtId;
    }

    public StateService getStateService() {
        return stateService;
    }

    public void setStateService(StateService stateService) {
        this.stateService = stateService;
    }

    public SysBizService getSysBizService() {
        return sysBizService;
    }

    public void setSysBizService(SysBizService sysBizService) {
        this.sysBizService = sysBizService;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
