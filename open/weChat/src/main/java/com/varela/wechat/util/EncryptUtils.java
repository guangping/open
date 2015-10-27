package com.varela.wechat.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtils {

	private static final String MD5="MD5";

	private static final String SHA1="SHA-1";

	private static final String UTF8="UTF-8";


	private static final String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	public static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static String MD5Encode(String origin) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance(MD5);
			resultString = byteArrayToHexString(md.digest(resultString
					.getBytes()));
		} catch (Exception localException) {
		}
		return resultString;
	}

	public static String SHA1Encode(String data)throws UnsupportedEncodingException {
		byte[] bytes = null;
		try {
			MessageDigest md = MessageDigest.getInstance(SHA1);
			bytes = md.digest(data.getBytes(UTF8));
		} catch (NoSuchAlgorithmException gse) {
			gse.printStackTrace();
		}
		return byte2hex(bytes);
	}

	/**
	 * 二进制转十六进制字符串
	 *
	 * @param bytes
	 * @return
	 */
	private static String byte2hex(byte[] bytes) {
		StringBuilder sign = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			if (hex.length() == 1) {
				sign.append("0");
			}
			sign.append(hex.toUpperCase());
		}
		return sign.toString();
	}

}