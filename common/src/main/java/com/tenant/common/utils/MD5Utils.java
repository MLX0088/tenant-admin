package com.tenant.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5工具类
 * @author motree02
 *
 */
public class MD5Utils {
	
	/**
	 * 生成密码MD5
	 * @param plainText
	 * @return
	 */
	public static String getPasswordMD5(String plainText) {
		return getMD5(plainText, 32);
	}
	
	/**
	 * 生成MD5
	 * @param plainText
	 * @param length
	 * @return
	 */
	private static String getMD5(String plainText, int length) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");	// 获取MD5实例
			md.update(plainText.getBytes());						// 此处传入要加密的byte类型值
			byte[] digest = md.digest();							// 此处得到的是md5加密后的byte类型值
			int i;
			StringBuilder sb = new StringBuilder();
			for (int offset = 0; offset < digest.length; offset++) {
				i = digest[offset];
				if (i < 0) {
					i += 256;
				}
				if (i < 16) {
					sb.append(0);
				}
				sb.append(Integer.toHexString(i));					// 通过Integer.toHexString方法把值变为16进制
			}
			return sb.toString().substring(0, length);				// 从下标0开始，length目的是截取多少长度的值
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 生成MD5
	 */
	public static String createMd5(String data) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] array = md.digest(data.getBytes("UTF-8"));
			StringBuilder sb = new StringBuilder();
		    for (byte item : array) {
		        sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
		    }
		    return sb.toString().toUpperCase();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
