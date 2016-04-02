package com.hajea.compiler.test;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;
import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import complier.CharSequenceCompiler;
import complier.CharSequenceCompilerException;
import complier.URIUtils;
public class ExpressionCal {
	/** ����ǰ׺ */
	private static final String PACKAGE_NAME = "javaxtools.compiler.test.runtime";
	public static class Tester {
		public static void main(String[] args) throws ScriptException {
			//��һ�����
			test();
			System.out.println("------Test Twice------/n");
			test();
		}
		public static void test() throws ScriptException {
			DecimalFormat df = new DecimalFormat("0.00");
			int loop = 10 * 10000 - 1;
			String exp = "x*x+x";
			double d = new Random().nextDouble() * 100;
			long start;
			//ֱ�Ӽ���
			start = System.nanoTime();
			for (int i = 0; i < loop; i++) {
				@SuppressWarnings("unused")
				double a = d * d + d;
			}
			System.out.printf(exp.replace("x", df.format(d)) + "=%2.4f/n", d * d + d);
			System.out.printf("Time of direct cal %d loops: %10.2f΢��./n/n", loop + 1,
					(System.nanoTime() - start) / 1000d);
			//����Դ�벢����
			start = System.nanoTime();
			Function func = new ExpressionCal().newFunction(exp);
			System.out.printf("Java src complain time: %10.2f΢��, /t", (System.nanoTime() - start) / 1000d);
			start = System.nanoTime();
			for (int i = 0; i < loop; i++) {
				func.doFunction(d);
			}
			System.out.printf(exp.replace("x", df.format(d)) + "=%2.4f/n", func.doFunction(d));
			System.out.printf("Complained source %d loops: %10.2f΢��./n/n", loop + 1,
					(System.nanoTime() - start) / 1000d);
			//����Javascript����
			start = System.nanoTime();
			ScriptEngine se = new ScriptEngineManager().getEngineByName("ECMAScript");
			CompiledScript script = ((Compilable) se).compile("var x;" + exp);
			System.out.printf("JS complain time: %10.2f΢��, /t", (System.nanoTime() - start) / 1000d);
			start = System.nanoTime();
			Bindings binding = new SimpleBindings();
			for (int i = 0; i < loop; i++) {
				binding.put("x", d);
				script.eval(binding);
			}
			binding.put("x", d);
			System.out.printf(exp.replace("x", df.format(d)) + "=%2.4f/n", script.eval(binding));
			System.out.printf("Javascript %d loops: %10.2f΢��./n", loop + 1,
					(System.nanoTime() - start) / 1000d);
		}
	}
	/** ������׺ */
	private int classNameSuffix = 0;
	/** �������������������������İ��������� */
	private static final Random random = new Random();
	/** �ַ�����ʽ��JavaԴ�ļ����� */
	private String template;
	private static final String TEMPLATE_NAME = "Function.java.template";
	private static final String TARGET_VERSION = "1.6";
	private final CharSequenceCompiler<Function> compiler = new CharSequenceCompiler<Function>(getClass()
			.getClassLoader(), Arrays.asList(new String[] { "-target", TARGET_VERSION, "-encoding",
			URIUtils.ENCODING }));
	public Function newFunction(String expr) {
		StringBuilder errStr = new StringBuilder();
		Function result = null;
		try {
			//����Ψһ�İ���������
			final String packageName = PACKAGE_NAME + digits();
			final String className = "C_" + (classNameSuffix++) + digits();
			final String qName = packageName + '.' + className;
			//�������Դ��
			final String source = fillTemplate(packageName, className, expr);
			//����Դ��
			Class<Function> compiledFunction = compiler.compile(qName, source,new Class<?>[] { Function.class });
			result = compiledFunction.newInstance();
		} catch (CharSequenceCompilerException ex) {
			errStr.append(log(ex.getDiagnostics()));
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			errStr.append(ExceptionUtils.getFullStackTrace(ex)).append("/n");
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			errStr.append(ExceptionUtils.getFullStackTrace(ex)).append("/n");
			ex.printStackTrace();
		} catch (IOException ex) {
			errStr.append(ExceptionUtils.getFullStackTrace(ex)).append("/n");
			ex.printStackTrace();
		}
		if (errStr.toString().trim().length() > 0) {
			System.err.println(errStr.toString());
		}
		return result;
	}
	/** @return ������'_'��ͷ�����16�����ַ��� */
	private String digits() {
		return '_' + Long.toHexString(random.nextLong());
	}
	/**
	 * �����ַ�����ʽ��javaԴ�ļ�����
	 * 
	 * @param packageName
	 *            ����
	 * @param className
	 *            ����
	 * @param expression
	 *            ���ʽ
	 * @return �ַ�����ʽ��javaԴ�ļ�����
	 * @throws IOException
	 */
	private String fillTemplate(String packageName, String className, String expression) throws IOException {
		if (template == null) {
			template = IOUtils.toString(Function.class.getResourceAsStream(TEMPLATE_NAME), URIUtils.ENCODING);
		}
		String source = template.replace("$packageName", packageName)//
				.replace("$className", className)//
				.replace("$expression", expression);
		return source;
	}
	/** ��¼{@link DiagnosticCollector}�еĴ������� */
	private CharSequence log(final DiagnosticCollector<JavaFileObject> diagnostics) {
		final StringBuilder msgs = new StringBuilder();
		for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics.getDiagnostics()) {
			msgs.append(diagnostic.getMessage(null)).append("/n");
		}
		return msgs;
	}
}
