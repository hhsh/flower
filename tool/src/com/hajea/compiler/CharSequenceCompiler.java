package com.hajea.compiler;
import java.nio.charset.Charset;  
import java.util.ArrayList;  
import java.util.Collection;  
import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  
import java.util.Map.Entry;  
import javax.tools.DiagnosticCollector;  
import javax.tools.JavaCompiler;  
import javax.tools.JavaFileObject;  
import javax.tools.StandardLocation;  
import javax.tools.ToolProvider;  
import javax.tools.JavaCompiler.CompilationTask;  

/** 
 * ����{@link CharSequence}��ʽ��Դ�룬��ʵ����������һ��ʵ����<br> 
 * �÷�ʾ�����Ա���MyInterface��һ��ʵ����Ϊ������ 
 *  
 * <pre> 
 * MyInterface instance = null; 
 * JavaStringCompiler<MyInterface> compiler = new JavaStringCompiler<MyInterface>(null, null); 
 * try { 
 *  Class<MyInterface> newClass = compiler.compile("com.mypackage.NewClass", 
 *          stringContaininSourceForNewClass, new Class<?>[] { MyInterface.class }); 
 *  instance = newClass.newInstance(); 
 * } catch (JavaStringCompilerException ex) { 
 *  ex.printStackTrace(); 
 * } catch (IllegalAccessException ex) { 
 *  ex.printStackTrace(); 
 * } 
 * instance.someOperation(someArgs); 
 * </pre> 
 */  
public class CharSequenceCompiler<T> {  
    /** ����ʹ�õı����� */  
    private final JavaCompiler compiler;  
    private final ClassLoaderImpl classLoader;  
    /** ��������������е������Ϣ */  
    private DiagnosticCollector<JavaFileObject> diagnostics;  
    private final FileManagerImpl javaFileManager;  
    /** ������� */  
    private final List<String> options;  
    /** 
     * ����һ���µ�ʵ������ʵ������ָ����classloader 
     *  
     * @param loader 
     *            Ӧ�õ�{@link ClassLoader} 
     * @param options 
     *            �������ı������������ɲο�javac������� 
     * @throws IllegalStateException 
     *             ���java������������ȷ�������׳��쳣 
     */  
    public CharSequenceCompiler(ClassLoader loader, Collection<String> options) {  
        compiler = ToolProvider.getSystemJavaCompiler();  
        if (compiler == null) {  
            throw new IllegalStateException("ϵͳjava�������޷��ҵ�����ȷ����·�����Ѿ�����tools.jar��ע��JDK 6��Ĭ���Դ���JRE 6��Ĭ�ϲ�������");  
        }  
        if (loader == null) {  
            classLoader = new ClassLoaderImpl(this.getClass().getClassLoader());  
        } else {  
            classLoader = new ClassLoaderImpl(loader);  
        }  
        this.options = new ArrayList<String>();  
        if (options != null) {  
            this.options.addAll(options);  
        }  
        diagnostics = new DiagnosticCollector<JavaFileObject>();  
        javaFileManager = new FileManagerImpl(compiler.getStandardFileManager(diagnostics, null, Charset  
                .forName(URIUtils.ENCODING)), classLoader);  
    }  
    /** 
     * ������Java���Դ�� 
     *  
     * @param classes 
     *            keyΪ�����ȫ�޶�����valueΪ��Ӧ��Դ�롣 
     * @return �������� 
     * @throws CharSequenceCompilerException 
     */  
    public synchronized Map<String, Class<T>> compile(Map<String, CharSequence> classes)  
            throws CharSequenceCompilerException {  
        //׼���������ļ�  
        List<JavaFileObject> sources = new ArrayList<JavaFileObject>();  
        for (Entry<String, CharSequence> entry : classes.entrySet()) {  
            String qualifiedClassName = entry.getKey();  
            CharSequence javaSource = entry.getValue();  
            if (javaSource != null) {  
                int dotPos = qualifiedClassName.lastIndexOf('.');  
                String className = dotPos == -1 ? qualifiedClassName : qualifiedClassName  
                        .substring(dotPos + 1);  
                String packageName = dotPos == -1 ? "" : qualifiedClassName.substring(0, dotPos);  
                JavaFileObjectImpl source = new JavaFileObjectImpl(className, javaSource);  
                sources.add(source);  
                javaFileManager.putFileForInput(StandardLocation.SOURCE_PATH, packageName, className  
                        + ".java", source);  
            }  
        }  
        //�������  
        CompilationTask task = compiler.getTask(null, javaFileManager, diagnostics, options, null, sources);  
        Boolean result = task.call();  
        //���ر�����  
        if ((result == null) || !result.booleanValue()) {  
            throw new CharSequenceCompilerException("Compilation failed.", classes.keySet(), diagnostics);  
        }  
        try {  
            Map<String, Class<T>> compiled = new HashMap<String, Class<T>>();  
            for (String qualifiedClassName : classes.keySet()) {  
                compiled.put(qualifiedClassName, loadClass(qualifiedClassName));  
            }  
            return compiled;  
        } catch (ClassNotFoundException ex) {  
            throw new CharSequenceCompilerException(classes.keySet(), ex, diagnostics);  
        } catch (IllegalArgumentException ex) {  
            throw new CharSequenceCompilerException(classes.keySet(), ex, diagnostics);  
        } catch (SecurityException ex) {  
            throw new CharSequenceCompilerException(classes.keySet(), ex, diagnostics);  
        }  
    }  
    /** 
     * ����һ��Java�ࡣ 
     *  
     * @param qualifiedClassName 
     *            �����ȫ�޶����� 
     * @param javaSource 
     *            �����java��������Դ�롣 
     * @param types 
     *            0�����࣬���Լ��鱻��������ܷ�ת������Щ�����κ�һ���� 
     * @return �������� 
     * @throws CharSequenceCompilerException 
     *             ������޷����������׳��쳣�� 
     * @throws ClassCastException 
     *             ������������޷�ת����types�е��κ�һ�����ͣ����׳��쳣�� 
     */  
    public synchronized Class<T> compile(String qualifiedClassName, CharSequence javaSource,  
            Class<?>... types) throws CharSequenceCompilerException, ClassCastException {  
        diagnostics = new DiagnosticCollector<JavaFileObject>();  
        Map<String, CharSequence> classes = new HashMap<String, CharSequence>(1);  
        classes.put(qualifiedClassName, javaSource);  
        Map<String, Class<T>> compiled = compile(classes);  
        Class<T> newClass = compiled.get(qualifiedClassName);  
        for (Class<?> type : types) {  
            if (!type.isAssignableFrom(newClass)) {  
                throw new ClassCastException(type.getName());  
            }  
        }  
        return newClass;  
    }  
    /** ����Java�ࡣ */  
    @SuppressWarnings("unchecked")  
    private Class<T> loadClass(final String qualifiedClassName) throws ClassNotFoundException {  
        return (Class<T>) classLoader.loadClass(qualifiedClassName);  
    }  
}  
