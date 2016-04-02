package com.hajea.compiler;

import java.io.ByteArrayInputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.util.Collection;  
import java.util.Collections;  
import java.util.HashMap;  
import java.util.Map;  
import javax.tools.JavaFileObject;  
import org.apache.commons.io.IOUtils; 

final class ClassLoaderImpl extends ClassLoader {  
    private final Map<String, JavaFileObjectImpl> classes = new HashMap<String, JavaFileObjectImpl>();  
    ClassLoaderImpl(final ClassLoader parentClassLoader) {  
        super(parentClassLoader);  
    }  
    @Override  
    public InputStream getResourceAsStream(final String name) {  
        if (name.endsWith(".class")) {  
            String qualifiedClassName = name.substring(0, name.length() - ".class".length())  
                    .replace('/', '.');  
            JavaFileObjectImpl file = classes.get(qualifiedClassName);  
            if (file != null) {  
                try {  
                    return new ByteArrayInputStream(IOUtils.toByteArray(file.openInputStream()));  
                } catch (IOException ex) {  
                }  
            }  
        }  
        return super.getResourceAsStream(name);  
    }  
    protected void add(final String qualifiedClassName, final JavaFileObjectImpl javaFile) {  
        classes.put(qualifiedClassName, javaFile);  
    }  
    /** @return ���ز��ɱ��Collection���������г��е�{@link JavaFileObject}���� */  
    protected Collection<JavaFileObjectImpl> files() {  
        return Collections.unmodifiableCollection(classes.values());  
    }  
    @Override  
    protected Class<?> findClass(final String qualifiedClassName) throws ClassNotFoundException {  
        JavaFileObject file = classes.get(qualifiedClassName);  
        if (file != null) {  
            try {  
                byte[] bytes = IOUtils.toByteArray(file.openInputStream());  
                return defineClass(qualifiedClassName, bytes, 0, bytes.length);  
            } catch (IOException ex) {  
            }  
        }  
        // Workaround in Java 6. see http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6434149  
        try {  
            Class<?> c = Class.forName(qualifiedClassName);  
            return c;  
        } catch (ClassNotFoundException nf) {  
        }  
        return super.findClass(qualifiedClassName);  
    }  
    @Override  
    protected synchronized Class<?> loadClass(final String name, final boolean resolve)  
            throws ClassNotFoundException {  
        return super.loadClass(name, resolve);  
    }  
}  
