package com.hajea.url;

import java.security.MessageDigest;

 
/**
 * @project baidamei
 * @author cevencheng <cevencheng@gmail.com>
 * @create 2012-11-10 ����1:15:05
 */
public class ShortUrlGenerator {
	
	 public final static String MD5(String s) {
	        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       
	        try {
	            byte[] btInput = s.getBytes();
	            // ���MD5ժҪ�㷨�� MessageDigest ����
	            MessageDigest mdInst = MessageDigest.getInstance("MD5");
	            // ʹ��ָ�����ֽڸ���ժҪ
	            mdInst.update(btInput);
	            // �������
	            byte[] md = mdInst.digest();
	            // ������ת����ʮ�����Ƶ��ַ�����ʽ
	            int j = md.length;
	            char str[] = new char[j * 2];
	            int k = 0;
	            for (int i = 0; i < j; i++) {
	                byte byte0 = md[i];
	                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
	                str[k++] = hexDigits[byte0 & 0xf];
	            }
	            return new String(str);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// �����ӣ� http://tech.sina.com.cn/i/2011-03-23/11285321288.shtml
		// ���˽�����Ķ�����Ϊ�� http://t.cn/h1jGSC
		String sLongUrl = "http://tech.sina.com.cn/i/2011-03-23/11285321288.shtml"; // 3BD768E58042156E54626860E241E999
		String[] aResult = shortUrl(sLongUrl);
		// ��ӡ�����
		for (int i = 0; i < aResult.length; i++) {
			System.out.println("[" + i + "]:::" + aResult[i]);
		}
	}

	public static String[] shortUrl(String url) {
		// �����Զ������� MD5 �����ַ���ǰ�Ļ�� KEY
		String key = "wuguowei";
		// Ҫʹ������ URL ���ַ�
		String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x",
				"y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
				"Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"

		};
		// �Դ�����ַ���� MD5 ����
//		String sMD5EncryptResult = (new CMyEncrypt()).getMD5OfStr(key + url);
		 
		String sMD5EncryptResult = MD5(key +  url);
		String hex = sMD5EncryptResult;

		String[] resUrl = new String[4];
		for (int i = 0; i < 4; i++) {

			// �Ѽ����ַ����� 8 λһ�� 16 ������ 0x3FFFFFFF ����λ������
			String sTempSubString = hex.substring(i * 8, i * 8 + 8);

			// ������Ҫʹ�� long ����ת������Ϊ Inteper .parseInt() ֻ�ܴ��� 31 λ , ��λΪ����λ , �������
			// long �����Խ��
			long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
			String outChars = "";
			for (int j = 0; j < 6; j++) {
				// �ѵõ���ֵ�� 0x0000003D ����λ�����㣬ȡ���ַ����� chars ����
				long index = 0x0000003D & lHexLong;
				// ��ȡ�õ��ַ����
				outChars += chars[(int) index];
				// ÿ��ѭ����λ���� 5 λ
				lHexLong = lHexLong >> 5;
			}
			// ���ַ��������Ӧ�������������
			resUrl[i] = outChars;
		}
		return resUrl;
	}
}

	    			