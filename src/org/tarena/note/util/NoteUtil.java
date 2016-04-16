package org.tarena.note.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;


public final class NoteUtil {
	/**
	 * md5加密处理
	 * @param msg 要加密的字符串
	 * @return 加密后经过处理的字符串
	 */
	public static String md5(String msg){
		try {
			MessageDigest md=MessageDigest.getInstance("MD5");//MD5   SHA
			byte[] input=msg.getBytes();
			byte[] output=md.digest(input);//算法处理
			//System.out.println(output.length);
			
			//将加密后的结果变成字符串,采用Base64处理
			String base64_msg=Base64.encodeBase64String(output);
			return base64_msg;
			
		/*	String s=new String(output);
			return s;*/
		} catch (Exception e) {
			return "";
		}
		
	}
	
	public static String createId(){
		UUID uuid=UUID.randomUUID();
		return uuid.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(createId());
	}

}
