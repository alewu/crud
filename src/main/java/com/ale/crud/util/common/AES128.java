package com.ale.crud.util.common;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * @author chenkai
 * @date 2017年6月20日 下午3:33:16
 * @Description AES128
 */
public class AES128 {
	/**
	 * 加密
	 * 
	 * @param content
	 *            需要加密的内容
	 * @return
	 */
	public static String encrypt(String content) {
		if (content != null) {
			try {
				Cipher cipher = Cipher.getInstance("AES");// 创建密码器
				byte[] byteContent = content.getBytes("utf-8");
				cipher.init(Cipher.ENCRYPT_MODE, genKey());// 初始化
				byte[] result = cipher.doFinal(byteContent);
				return parseByte2HexStr(result); // 加密
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;

	}

	/**
	 * 解密
	 * 
	 * @param content
	 *            待解密内容
	 * @return
	 */
	public static String decrypt(String content) {
		if (content != null) {
			try {
				byte[] decryptFrom = parseHexStr2Byte(content);
				Cipher cipher = Cipher.getInstance("AES");// 创建密码器
				cipher.init(Cipher.DECRYPT_MODE, genKey());// 初始化
				byte[] result = cipher.doFinal(decryptFrom);
				return new String(result, "utf-8"); // 加密
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 根据密钥获得 SecretKeySpec
	 * 
	 * @return
	 */
	private static SecretKeySpec genKey() {
		String strKey = "veir_123";// 加密解密密钥
		byte[] enCodeFormat = { 0 };
		;
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(strKey.getBytes());
			kgen.init(128, secureRandom);
			SecretKey secretKey = kgen.generateKey();
			enCodeFormat = secretKey.getEncoded();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new SecretKeySpec(enCodeFormat, "AES");
	}

	/**
	 * 将二进制转换成16进制
	 * 
	 * @param buf
	 * @return
	 */
	private static String parseByte2HexStr(byte[] buf) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 将16进制转换为二进制
	 * 
	 * @param hexStr
	 * @return
	 */
	private static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1) {
            return null;
        }
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
					16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(encrypt("张三"));
		System.out.println(decrypt("77AB65575095855714229082F8558797"));
		System.out.println(encrypt("13229194914"));
	}
}