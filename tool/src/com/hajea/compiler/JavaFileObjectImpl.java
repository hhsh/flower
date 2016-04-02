package com.hajea.compiler;
import java.io.ByteArrayInputStream;  
import java.io.ByteArrayOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.OutputStream;  
import java.io.OutputStreamWriter;  
import java.io.Writer;  
import javax.tools.FileObject;  
import javax.tools.JavaFileObject;  
import javax.tools.SimpleJavaFileObject;  
/** 
 * {@link FileObject}��{@link JavaFileObject}��һ��ʵ�֣����ܳ���javaԴ����������class�������������ڣ� 
 * <ol> 
 * <li>�����Ҫ���ݸ���������Դ�룬��ʱʹ�õ���{@link JavaFileObjectImpl#JavaFileObjectImpl(String, CharSequence)}��������</li> 
 * <li>��ű������������byte code������ʹ�õ���{@link JavaFileObjectImpl#JavaFileObjectImpl(String, JavaFileObject.Kind)}</li> 
 * </ol> 
 */  
final class JavaFileObjectImpl extends SimpleJavaFileObject {  
    /** ���kind == CLASS, �洢byte code������ͨ��{@link #openInputStream()}�õ� */  
    private ByteArrayOutputStream byteCode;  
    /** ���kind == SOURCE, �洢Դ�� */  
    private final CharSequence source;  
    /** 
     * ��������Դ���ʵ�� 
     *  
     * @param baseName 
     *            the base name 
     * @param source 
     *            the source code 
     */  
    JavaFileObjectImpl(final String baseName, final CharSequence source) {  
        super(URIUtils.toURI(baseName + ".java"), Kind.SOURCE);  
        this.source = source;  
    }  
    /** 
     * �������ж�����byte code��ʵ�� 
     *  
     * @param name 
     *            the file name 
     * @param kind 
     *            the kind of file 
     */  
    JavaFileObjectImpl(final String name, final Kind kind) {  
        super(URIUtils.toURI(name), kind);  
        source = null;  
    }  
    @Override  
    public CharSequence getCharContent(final boolean ignoreEncodingErrors)  
            throws UnsupportedOperationException {  
        if (source == null) {  
            throw new UnsupportedOperationException("getCharContent()");  
        }  
        return source;  
    }  
    @Override  
    public InputStream openInputStream() {  
        return new ByteArrayInputStream(byteCode.toByteArray());  
    }  
    @Override  
    public OutputStream openOutputStream() {  
        return (byteCode = new ByteArrayOutputStream());  
    }  
    @Override  
    public Writer openWriter() throws IOException {  
        return new OutputStreamWriter(openOutputStream(), URIUtils.ENCODING);  
    }  
} 
