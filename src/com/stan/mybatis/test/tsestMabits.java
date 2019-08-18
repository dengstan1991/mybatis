package com.stan.mybatis.test;

import com.stan.mybatis.pojo.Category;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class tsestMabits {

    /**
     * mybatis demo
     */
    @Test
    public void test1() throws IOException {
        String resoure="mybatis-config.xml";
        InputStream inputStream= Resources.getResourceAsStream(resoure);
        SqlSessionFactory sf=new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession=sf.openSession();
        List<Category> cs=sqlSession.selectList("listCategory");
        for (Category c:cs){
            System.out.println(c.getName());
        }
    }

    @Test
    /**
     * crud操作
     */
    public void test2() throws IOException{
        String resoure="mybatis-config.xml";
        InputStream inputStream= Resources.getResourceAsStream(resoure);
        SqlSessionFactory sf=new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession=sf.openSession();
       // Category c=new Category();
        //增加
//        c.setName("java");
//        sqlSession.insert("add",c);
        //删除
//       c.setId(3);
//        sqlSession.delete("delete", c);
        //'改
//        Category c=sqlSession.selectOne("get",1);
//        c.setName("mybatis");
//        sqlSession.update("update",c);
        //查
        Category c=sqlSession.selectOne("get",2);
        System.out.println(c.getName());
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    /*
    模糊查询
     */
    public void test3() throws  IOException{
        String resoure="mybatis-config.xml";
        InputStream inputStream= Resources.getResourceAsStream(resoure);
        SqlSessionFactory sf=new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession=sf.openSession();
        List<Category> cs=sqlSession.selectList("listCategoryByName","cat");
        for (Category c:cs){
            System.out.println(c.getName());
        }
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    /*
    多条件查询
     */
    public void test4() throws  IOException{
        String resoure="mybatis-config.xml";
        InputStream inputStream= Resources.getResourceAsStream(resoure);
        SqlSessionFactory sf=new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession=sf.openSession();
        Map<String,Object> params=new HashMap<>();
        params.put("id",2);
        params.put("id","cat");
        List<Category> cs=sqlSession.selectList("listCategoryByIdAndName",params);
        for (Category c : cs) {
            System.out.println(c.getName());
        }
        sqlSession.commit();
        sqlSession.close();
    }
}
