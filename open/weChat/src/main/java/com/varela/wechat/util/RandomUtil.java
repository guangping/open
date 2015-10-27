package com.varela.wechat.util;

import java.util.Random;


public class RandomUtil {

	private static final String TEMPLATE_STRING = "qwertyuiopasdfghjklzxcvbnm1234567890";

	/**
	 * 获取32位随机字符串
	 * 
	 * @return
	 */
	public static String getRandomStr() {
		StringBuffer s = new StringBuffer();
		for (int i = 0; i < 32; i++) {
			s.append(TEMPLATE_STRING.charAt(getRandomNum()));
		}
		return s.toString().toUpperCase();
	}

	/**
	 * 获取0-35之间的随机数
	 * 
	 * @return
	 */
	public static Integer getRandomNum() {
		return new Random().nextInt(35);
	}
	
}
