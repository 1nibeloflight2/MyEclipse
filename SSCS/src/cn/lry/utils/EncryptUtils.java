package cn.lry.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class EncryptUtils {

	public static String getMD5Algo(String message) {	//algorithm

		try {
			//����ָ����ȡ
			MessageDigest md = MessageDigest.getInstance("md5");
			byte md5[] = md.digest(message.getBytes());
			
			//������ת��Ϊ����
			BASE64Encoder encoding = new BASE64Encoder();
			return encoding.encode(md5);
			
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
};
