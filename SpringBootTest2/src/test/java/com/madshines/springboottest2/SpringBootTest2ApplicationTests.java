package com.madshines.springboottest2;

import com.madshines.springboottest2.pojo.Dog;
import com.madshines.springboottest2.pojo.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class SpringBootTest2ApplicationTests {
    @Autowired
    private Student student;
    @Autowired
    private Dog dog;
    @Autowired
    private ApplicationContext applicationContext;
    public boolean contrainService(){
        boolean helloService = applicationContext.containsBean("helloService");
        return  helloService;
    }
    @Test
    void contextLoads() {
        System.out.println(student);
        System.out.println(contrainService());
        System.out.println(dog);
    }

}
