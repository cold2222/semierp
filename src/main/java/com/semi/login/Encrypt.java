package com.semi.login;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypt {
	public static String encrypt(String text) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(text.getBytes());
		return bytesToHex(md.digest());
	}
	
	private static String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
        	// ����Ʈ ���� 16���� ���ڿ��� ��ȯ�Ͽ� StringBuilder�� �߰�
            builder.append(String.format("%02x", b)); 
        }
        // StringBuilder�� ������ ���ڿ��� ��ȯ
        return builder.toString(); 
	}
	public static String getPW(String id, String pw) throws NoSuchAlgorithmException {
		return encrypt(encrypt(id)+encrypt(pw));
	}
}