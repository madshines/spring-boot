package com.madshines.springbootjpa;

import com.madshines.springbootjpa.pojo.MyClass;
import com.madshines.springbootjpa.repository.test1.Test1Repository;
import com.madshines.springbootjpa.repository.test2.Test2Repository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SpringbootjpaApplicationTests {
    @Resource
    private Test1Repository test1Repository;
    @Resource
    private Test2Repository test2Repository;
    @Test
    void contextLoads() {
        //test1Repository.deleteAll();
//        List<MyClass> list = test1Repository.getList();
//        System.out.println(list);
//        MyClass class1 = test1Repository.getById(1);
//        System.out.println(class1);
//        List<MyClass> all = test2Repository.findAll();
//        System.out.println(all);

//        test1Repository.insertClass(5,"三班");
//        MyClass myClass=new MyClass(5,"五班");
//        test1Repository.save(myClass);
        MyClass myClass1 = new MyClass(2, "二班");
        test2Repository.save(myClass1);
    }

}
