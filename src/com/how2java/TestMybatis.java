package com.how2java;
 
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
 
import com.how2java.pojo.Category;
 
public class TestMybatis {
 
	 public static void main(String[] args) throws IOException {
	        String resource = "mybatis-config.xml";
	        InputStream inputStream = Resources.getResourceAsStream(resource);
	        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	        SqlSession session = sqlSessionFactory.openSession();
	 
	        Category c = new Category();
	        
	      //增加
	        c.setName("新增加的Category");  
	        session.insert("addCategory",c);
	       
	        //删除
	        c.setId(6);
	        session.delete("deleteCategory",c);
	        
	        //获取某条记录
	        c= session.selectOne("getCategory",3);
	        
	        //修改
	        c.setName("修改了的Category名稱");
	        session.update("updateCategory",c);
	        
	        System.out.println(c.getName());
	        
	        //查询所有记录
	        listAll(session);
	        //模糊查询
	       
	        List<Category> cs = session.selectList("listCategoryByName","cat");
	        for (Category cc : cs) {
	            System.out.println(c.getName());
	        }
	        //多条件查询
	        Map<String,Object> params = new HashMap<>();
	        params.put("id", 3);
	        params.put("name", "cat");
	         
	        List<Category> cs1 = session.selectList("listCategoryByIdAndName",params);
	        for (Category c1 : cs) {
	            System.out.println(c.getName());
	        }
	         
	        session.commit();
	        session.close();
	 
	    }
	 
	    private static void listAll(SqlSession session) {
	        List<Category> cs = session.selectList("listCategory");
	        for (Category c : cs) {
	            System.out.println(c.getName());
	        }
	    }
}