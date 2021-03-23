package com.lyl.plugin.data.safe;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * 对称加密DES
 */
public class DESUtil {

	/**
	 * 加密
	 * @param str
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String str, String key) throws Exception {
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.ENCRYPT_MODE, generateKey(key));
		byte[] bytes = cipher.doFinal(str.getBytes());
		return Base64.getEncoder().encodeToString(bytes);
	}

	/**
	 * 解密
	 * @param str
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String str, String key) throws Exception {
		byte[] bytes = Base64.getDecoder().decode(str);
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.DECRYPT_MODE, generateKey(key));
		return new String(cipher.doFinal(bytes));
	}

	/**
	 * 生成key
	 * @param key
	 * @return
	 * @throws Exception
	 */
    public static Key generateKey(String key) throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
//		keyGenerator.init(56, new SecureRandom(key.getBytes()));
        //解决Linux操作系统下aes解密失败的问题
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );
        secureRandom.setSeed(key.getBytes());
        keyGenerator.init(56,secureRandom);
		return new SecretKeySpec(keyGenerator.generateKey().getEncoded(), "DES");
	}

}
