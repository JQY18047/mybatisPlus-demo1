package com.itheima;



import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Mybatisplus01QuickstartApplicationTests {

    @Autowired
    private UserDao userDao;


    @Test
    void testGetAll() {
        List<User> userList = userDao.selectList(null);
        System.out.println(userList);
    }

    @Test
    void testInsert() {
        User user = new User();
        user.setName("Jerry");
        user.setPassword("123");
        user.setAge(18);
        user.setTel("1452");
        int count = userDao.insert(user);
        System.out.println(count);
    }
    @Test
    void testDelete() {   //tableLogic 已经成为逻辑删除
        int count = userDao.deleteById(1L);
        System.out.println(count);
    }
    @Test
    void testUpdate() {
        User user = new User();
        user.setId(1L);
        user.setName("Tom");
        //是动态sql
        int count = userDao.updateById(user);
        System.out.println(count);
    }

    @Test
    void testGetById() {
        User user = userDao.selectById(1);
        System.out.println(user);
    }
    @Test
    void testGetByPage() {
        IPage page = new Page(1,5);
        userDao.selectPage(page,null);
        System.out.println(page.getCurrent());
        System.out.println(page.getSize());
        System.out.println(page.getPages());
        System.out.println(page.getRecords());
        System.out.println(page.getTotal());
    }
    //条件查询
    @Test
    void testGetAllCondition() {
     //方式一 常规格式
/*        QueryWrapper<User> uqw= new QueryWrapper<>();
        uqw.lt("age",30);
        uqw.gt("age",18);
        List<User> users = userDao.selectList(uqw);
        System.out.println(users);
        */

      //方式二 链式编程
//        QueryWrapper<User> uqw = new QueryWrapper<User>();
//       uqw.gt("age", 18).lt("age", 30);
//        List<User> users = userDao.selectList(uqw);
//        System.out.println(users);

        // 方式三 queryWrapper  lambda
//        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
//        queryWrapper.lambda().lt(User::getAge,18);
//        List<User> users = userDao.selectList(queryWrapper);
//        System.out.println(users);

        //方式四  queryWrapper lambda
  /*      LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.lt(User::getAge,18).or().gt(User::getAge,30);
        List<User> users = userDao.selectList(wrapper);
        System.out.println(users);*/

        //查询投影  非lambda
//        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
//        userQueryWrapper.select("id","age","tel");
//        List<User> users = userDao.selectList(userQueryWrapper);
//        System.out.println(users);
        //查询投影  lambda
//        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
//        wrapper.select(User::getId,User::getName,User::getPassword);
//        List<User> users = userDao.selectList(wrapper);
//        System.out.println(users);


//        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
//        userQueryWrapper.select("count(*) as num","tel");
//        userQueryWrapper.groupBy("tel");
//        List<User> users = userDao.selectList(userQueryWrapper);
//        System.out.println(users);


    }
    @Test
    public void  OptimisticLocker(){
        //乐观锁:防止同一时间 执行两条操作
        //乐观锁会在修改前检查版本信息
        //先查询数据 获取version版本
        User user = userDao.selectById(3L);
        user.setName("Tom and Jerry");
        userDao.updateById(user);

    }

}
