package com.hajea.compiler;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
public abstract class URIUtils {
	public static final String ENCODING = Charset.defaultCharset().name();
	/** ��Stringת����URI�����ת���쳣���׳�URISyntaxException����ֱ���׳�RuntimeException�� */
	static URI toURI(String name) {
		try {
			return new URI(name);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}
}
