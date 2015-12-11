package com.varela.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AESUtils {

	private static Logger logger = LoggerFactory.getLogger(AESUtils.class);

	public static String encrypt4AES(String source, String key) {
		try {
			IvParameterSpec zeroIv = new IvParameterSpec(key.getBytes());
			SecretKeySpec key1 = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key1, zeroIv);
			byte[] encryptedData = cipher.doFinal(source.getBytes());
			String encryptResultStr = Base64.encodeBase64String(encryptedData);
			return encryptResultStr;
			// 加密
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	public static String decrypt4AES(String content, String key) {
		try {
			byte[] decryptFrom = Base64.decodeBase64(content);
			IvParameterSpec zeroIv = new IvParameterSpec(key.getBytes("utf-8"));
			SecretKeySpec key1 = new SecretKeySpec(key.getBytes("utf-8"), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key1, zeroIv);
			byte decryptedData[] = cipher.doFinal(decryptFrom);
			return new String(decryptedData); // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 数组转换成十六进制字符串
	 *
	 * @param bArray
	 * @return HexString
	 */
	public static final String bytesToHexString(byte[] bArray) {
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for (int i = 0; i < bArray.length; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase());
		}
		return sb.toString();
	}

}
