package com.sjli;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class DB01_JDBC_Pooling {
    public static void main(String[] args) throws SQLException {
        //JDBC连接池
        /*
        我们在讲多线程的时候说过，创建线程是一个昂贵的操作，如果有大量的小任务需要执行，并且频繁地创建和销毁线程，
        实际上会消耗大量的系统资源，往往创建和消耗线程所耗费的时间比执行任务的时间还长，所以，为了提高效率，可以用线程池。

        类似的，在执行JDBC的增删改查的操作时，如果每一次操作都来一次打开连接，操作，关闭连接，
        那么创建和销毁JDBC连接的开销就太大了。为了避免频繁地创建和销毁JDBC连接，我们可以通过连接池（Connection Pool）
        复用已经创建好的连接。

        JDBC连接池有一个标准的接口javax.sql.DataSource，注意这个类位于Java标准库中，但仅仅是接口。
        要使用JDBC连接池，我们必须选择一个JDBC连接池的实现。常用的JDBC连接池有：

        HikariCP
        C3P0
        BoneCP
        Druid
        目前使用最广泛的是HikariCP。我们以HikariCP为例，要使用JDBC连接池，先添加HikariCP的依赖如下：

        <dependency>
            <groupId>com.zaxxer</groupId>
            <artifactId>HikariCP</artifactId>
            <version>2.7.1</version>
        </dependency>
        紧接着，我们需要创建一个DataSource实例，这个实例就是连接池：
         */
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/learnjdbc");
        config.setUsername("root");
        config.setPassword("lishijin123");
        config.addDataSourceProperty("connectionTimeout", "1000"); // 连接超时：1秒
        config.addDataSourceProperty("idleTimeout", "60000"); // 空闲超时：60秒
        config.addDataSourceProperty("maximumPoolSize", "10"); // 最大连接数：10
        DataSource ds = new HikariDataSource(config);
        //注意创建DataSource也是一个非常昂贵的操作，所以通常DataSource实例总是作为一个全局变量存储，
        // 并贯穿整个应用程序的生命周期。
        //
        //有了连接池以后，我们如何使用它呢？和前面的代码类似，只是获取Connection时，
        // 把DriverManage.getConnection()改为ds.getConnection()：
        try (Connection conn = ds.getConnection()) {
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery("SELECT id, grade, name, gender FROM students WHERE gender=1")) {
                    while (rs.next()) {
                        long id = rs.getLong(1); // 注意：索引从1开始
                        long grade = rs.getLong(2);
                        String name = rs.getString(3);
                        int gender = rs.getInt(4);
                    }
                }
                stmt.close();
            }
            conn.close();
        }
    }
}
