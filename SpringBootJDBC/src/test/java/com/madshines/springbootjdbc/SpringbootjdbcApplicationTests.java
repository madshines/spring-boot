package com.madshines.springbootjdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SpringbootjdbcApplicationTests {
    @Autowired
    DataSource dataSource;
    @Test
    void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from student");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Object> list=new ArrayList<>();
        while (resultSet.next()){
            list.add(resultSet.getInt(1));
            list.add(resultSet.getString(2));
            list.add(resultSet.getString(3));
            list.add(resultSet.getString(4));
            list.add(resultSet.getString(5));
            list.add(resultSet.getInt(6));
        }
        System.out.println(list);
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

}
