package com.lyl.plugin.data.safe;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 摘要算法MD5
 */
public class MD5Util {

	/**
	 * md5
	 * @param str
	 * @return
	 */
	public final static String encode(String str){
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		messageDigest.update(str.getBytes());
		return Base64.getEncoder().encodeToString(messageDigest.digest());
	}

}
