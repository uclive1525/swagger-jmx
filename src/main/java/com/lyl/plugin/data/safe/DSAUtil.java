package com.lyl.plugin.data.safe;

import java.security.*;
import java.util.Base64;

/**
 * 数字签名DSA
 */
public class DSAUtil {
	/**
	 * 签名
	 * @param data
	 * @param privateKey
	 * @return
	 * @throws Exception
	 */
	public static String sign(String data, PrivateKey privateKey) throws Exception {
		Signature signature = Signature.getInstance(KeyFactory.getInstance("DSA").getAlgorithm());
		signature.initSign(privateKey);
		signature.update(data.getBytes());
		return Base64.getEncoder().encodeToString(signature.sign());
	}

	/**
	 * 验证
	 * @param data
	 * @param sign
	 * @param publicKey
	 * @return
	 * @throws Exception
	 */
	public static boolean verify(String data, String sign, PublicKey publicKey) throws Exception {
		byte[] bytes = Base64.getDecoder().decode(sign);
		Signature signature = Signature.getInstance(KeyFactory.getInstance("DSA").getAlgorithm());
		signature.initVerify(publicKey);
		signature.update(data.getBytes());
		return signature.verify(bytes);
	}

	/**
	 * 生成密钥对
	 * @return
	 * @throws Exception
	 */
	public static KeyPair genKeyPair() throws Exception {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
		keyPairGenerator.initialize(1024);
		return keyPairGenerator.generateKeyPair();
	}
}
