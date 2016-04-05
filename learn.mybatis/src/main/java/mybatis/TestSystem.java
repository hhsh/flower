package mybatis;

import java.io.Console;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class TestSystem {
	
	
	
	
	public static void testSystemIn() throws IOException{
		//13 0D	CR (carriage return)		回车键
		//10 0A	LF (NL line feed, new line)	换行键
		int c = System.in.read();
		while(true){
			char ch = (char)c;
			System.out.print(ch);
			c = System.in.read();
			//System.exit(100);
		}
	}
	
	public static void testSystemErr() throws IOException{
		System.err.print("hello");
		System.err.append(" world");
	}
	
	public static void testSystemConsole() {
		Console console = System.console();
		console.printf("xx%d", 12);
	}

	public static void testSystemExit(){
		System.exit(100);
	}
	
	public static void testGetenv(){
		//不是大小写敏感
		String val = System.getenv("java_home");
		System.out.println(val);
		Map<String,String> map = System.getenv();
		System.out.println(map);
	}
	public static void testGetProperties(){
		Properties prop = System.getProperties();
//		Set<Entry<Object, Object>>  set = prop.entrySet();
//		Iterator it = set.iterator();
//		if(it.hasNext()){
//			Entry<Object, Object> entry = (Entry<Object, Object>)it.next();
//			System.out.println(entry.getKey() + ":" + entry.getValue());
//		}
		System.out.println(prop);
	 
	}
	
	public static boolean isNameStart(int c) {
		int MASK_NAME_START = 0x04;
		byte[] CHARS = new byte[1 << 16];
		Arrays.fill(CHARS, 57344, 65534, (byte) 33 );
        return c < 0x10000 && (CHARS[c] & MASK_NAME_START) != 0;
    } 
	
	public static void main(String[]args) throws IOException, Exception{
		//testSystemIn();
		//testSystemErr();
		//testSystemConsole();
		//testGetenv();
		//testGetProperties();
		System.out.println( isNameStart(100));
		
		
		 Logger logger = LoggerFactory.getLogger(TestSystem.class);
		 logger.debug("HAO 22");
	}
	
}
