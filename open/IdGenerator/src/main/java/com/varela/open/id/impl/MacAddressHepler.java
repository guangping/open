package com.varela.open.id.impl;

import java.net.InetAddress;
import java.net.NetworkInterface;


public class MacAddressHepler {

    /**
     * 获取Mac地址
     *
     * @return
     */
    public static String getMacAddress() throws Exception {
        InetAddress inetAddress = InetAddress.getLocalHost();
        NetworkInterface networkInterface = NetworkInterface.getByInetAddress(inetAddress);

        byte[] mac = networkInterface.getHardwareAddress();
        StringBuffer macAddressBuffer = new StringBuffer("");
        for (int i = 0; i < mac.length; i++) {
            if (i != 0) {
                macAddressBuffer.append("-");
            }
            // 字节转换为整数
            int temp = mac[i] & 0xff;
            String str = Integer.toHexString(temp);
            if (str.length() == 1) {
                macAddressBuffer.append("0" + str);
            } else {
                macAddressBuffer.append(str);
            }
        }
        return macAddressBuffer.toString();
    }

    /**
     * 前24位叫做组织唯一标志符（Organizationally Unique Identifier，即OUI）， 是由IEEE的注册管理机构给不同厂家分配的代码，区分了不同的厂家。<br>
     * <p>
     * 后24位是由厂家自己分配的，称为扩展标识符。同一个厂家生产的网卡中MAC地址后24位是不同的。<br>
     * <p>
     * URL：http://en.wikipedia.org/wiki/MAC_address
     * <p>
     * 获取Mac的扩展标识符
     *
     * @return
     */
    public static int getMacExtId() throws Exception {
        InetAddress inetAddress = InetAddress.getLocalHost();
        NetworkInterface networkInterface = NetworkInterface.getByInetAddress(inetAddress);
        int macExtId = 0;
        byte[] mac = networkInterface.getHardwareAddress();
        for (int i = 3; i < mac.length; i++) {
            // 字节转换为整数
            int temp = mac[i] & 0xff;
            macExtId |= temp << (8 * (5 - i));
        }
        return macExtId;
    }

}
