package mybatis;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataSource {
	
	{
		System.out.println();
		System.out.println();
	}
	
	public static void main(String [] arg) throws IOException, Exception{	
		//String resource = "BlogMapper.xml";
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name", "hh");
		List<User> list = session.selectList("com.hhsh.dao.Test.getUser",map);
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i).getName());
		}
		
		
		System.out.println("----------------");
		
		List<Integer> idList = new ArrayList<Integer>(2);
		idList.add(1101);
		idList.add(1102);
		list = session.selectList("com.hhsh.dao.Test.selectUserIn",idList);
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i).getName());
		}

		
		 Logger logger = LoggerFactory.getLogger(DataSource.class);
		 logger.info("Hello World");
		 
		 
		 System.out.println("----------------"); 
		 
		 Connection conn = session.getConnection();
		 Integer id = 1101;//1101
		 PreparedStatement  pst = conn.prepareStatement(selectUsers(id));
		 if(id!=null){
			 pst.setObject(1,id );
		 }
		 
		 ResultSet rs =  pst.executeQuery();
		 
		 while (rs.next()) {
			 System.out.println( rs.getObject("name"));
			 ResultSetMetaData rsmd = rs.getMetaData();
		 }
		
//		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//		DocumentBuilder db = dbf.newDocumentBuilder();
//		db.parse("");
//			
//		XMLReader xmlReader  = XMLReaderFactory.createXMLReader();
//		xmlReader.parse("");
	}
	
	
	private static String selectUsers(Integer id){
		BEGIN();
		SELECT("*");
		FROM("user");
		if(id!=null){
			WHERE("id=?");
		}
		return SQL();
	}
	
	private String selectUserSql() {
		  return new SQL() {
			  {
			    SELECT("P.ID, P.USERNAME, P.PASSWORD, P.FULL_NAME");
			    SELECT("P.LAST_NAME, P.CREATED_ON, P.UPDATED_ON");
			    FROM("PERSON P");
			    FROM("ACCOUNT A");
			    INNER_JOIN("DEPARTMENT D on D.ID = P.DEPARTMENT_ID");
			    INNER_JOIN("COMPANY C on D.COMPANY_ID = C.ID");
			    WHERE("P.ID = A.ID");
			    WHERE("P.FIRST_NAME like ?");
			    OR();
			    WHERE("P.LAST_NAME like ?");
			    GROUP_BY("P.ID");
			    HAVING("P.LAST_NAME like ?");
			    OR();
			    HAVING("P.FIRST_NAME like ?");
			    ORDER_BY("P.ID");
			    ORDER_BY("P.FULL_NAME");
		  }
		}.toString();
	}
	
	
	//@Insert("insert into user (id, name,score) values(#{id}, #{name} ,#{score})")
	//@SelectKey(statement="call next value for TestSequence", keyProperty="nameId", before=true, resultType=int.class)
	//int insertTable3();

}
