package com.sjli.spring.usedb.A1_useJDBC;


import com.sjli.spring.IOC.A4_useResource.service.AppService;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Java程序使用JDBC接口访问关系数据库的时候，需要以下几步：
 *
 * 创建全局DataSource实例，表示数据库连接池；
 * 在需要读写数据库的方法内部，按如下步骤访问数据库：
 * 从全局DataSource实例获取Connection实例；
 * 通过Connection实例创建PreparedStatement实例；
 * 执行SQL语句，如果是查询，则通过ResultSet读取结果集，如果是修改，则获得int结果。
 * 正确编写JDBC代码的关键是使用try ... finally释放资源，涉及到事务的代码需要正确提交或回滚事务。
 *
 * 在Spring使用JDBC，首先我们通过IoC容器创建并管理一个DataSource实例，
 * 然后，Spring提供了一个JdbcTemplate，可以方便地让我们操作JDBC，
 * 因此，通常情况下，我们会实例化一个JdbcTemplate。顾名思义，这个类主要使用了Template模式。
 *
 * 编写示例代码或者测试代码时，我们强烈推荐使用HSQLDB这个数据库，它是一个用Java编写的关系数据库，
 * 可以以内存模式或者文件模式运行，本身只有一个jar包，非常适合演示代码或者测试代码。
 */

@Configuration
@ComponentScan
@EnableTransactionManagement //启用事务声明
@PropertySource("jdbc.properties")
public class AppConfig {

    @Value("${jdbc.url}")
    String jdbcUrl;

    @Value("${jdbc.username}")
    String jdbcUsername;

    @Value("${jdbc.password}")
    String jdbcPassword;

    @Bean
    DataSource createDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(jdbcUsername);
        config.setPassword(jdbcPassword);
        config.addDataSourceProperty("autoCommit", "true");
        config.addDataSourceProperty("connectionTimeout", "5");
        config.addDataSourceProperty("idleTimeout", "60");
        return new HikariDataSource(config);
    }

    @Bean
    JdbcTemplate createJdbcTemplate(@Autowired DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    //Spring为了同时支持JDBC和JTA两种事务模型，就抽象出PlatformTransactionManager。因为我们的代码只需要JDBC事务，
    // 因此，在AppConfig中，需要再定义一个PlatformTransactionManager对应的Bean，它的实际类型是DataSourceTransactionManager：
    @Bean
    PlatformTransactionManager createTxManager(@Autowired DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        AppService appService = context.getBean(AppService.class);
        System.out.println(appService.toString());
        String logo  = appService.getLogo();
        System.out.println(logo);
    }
}