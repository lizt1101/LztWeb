package com.lzt.util;

import org.apache.shiro.crypto.hash.SimpleHash;

public class MD5Util {
	
	public static String MD5(String password){
		String salt = "lzt";
		//内部使用 MessageDigest
		String simpleHash = new SimpleHash("Md5", password, salt).toString();
		return simpleHash;
	}

	public static void main(String[] args) {
		System.out.println(MD5Util.MD5("123456"));

	}

}
