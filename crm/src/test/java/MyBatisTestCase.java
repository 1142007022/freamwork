import kaisheng.project.entity.User;
import kaisheng.project.entity.UserExample;
import kaisheng.project.mapper.UserMapper;
import kaisheng.project.utils.SqlSessionManger;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MyBatisTestCase {
    private SqlSession sqlSession;
    @Before
    public void Before(){
        sqlSession = SqlSessionManger.getSqlSession(true);
    }

    @Test
    public void Test(){
       /* User user = new User();
        user.setAddress("河南焦作");
        user.setAge(23);
        user.setName("jiangdong");
        user.setTel("123");
        UserExample example = new UserExample();
        int num = sqlSession.getMapper(UserMapper.class).insert(user);
        System.out.println(num);*/

       /*for(int i = 0;i < 10;i++) {
           User user = new User();
           user.setAddress("河南焦作");
           user.setAge(23);
           user.setName("jiangdong" + i);
           user.setTel("123"+i);
           sqlSession.getMapper(UserMapper.class).insert(user);
       }*/
      /*  UserExample example = new UserExample();
        List<User> lists = sqlSession.getMapper(UserMapper.class).selectByExample(example);
        for(User user : lists) {
            System.out.println(user);
        }*/

        /*User user= sqlSession.getMapper(UserMapper.class).selectByPrimaryKey(2);
        System.out.println(user.getName());*/

        UserExample example = new UserExample();
        /*example.createCriteria().andNameEqualTo("jiangdong");*/
        example.createCriteria().andNameLike("%jiangdong%");
        List<User> lists = sqlSession.getMapper(UserMapper.class).selectByExample(example);
        for(User user : lists) {
            System.out.println(user.getName());
        }
    }

    @After
    public void After(){
        System.out.println("测试结束");
    }

}
