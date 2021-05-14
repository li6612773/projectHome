package com.sjli;


import javax.xml.namespace.QName;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DB01_JDBC_Batch {
    public static void main(String[] args) throws SQLException {
        //JDBC Batch
        //使用JDBC操作数据库的时候，经常会执行一些批量操作。
        //
        //例如，一次性给会员增加可用优惠券若干，我们可以执行以下SQL代码：
        /*
        INSERT INTO coupons (user_id, type, expires) VALUES (123, 'DISCOUNT', '2030-12-31');
        INSERT INTO coupons (user_id, type, expires) VALUES (234, 'DISCOUNT', '2030-12-31');
        INSERT INTO coupons (user_id, type, expires) VALUES (345, 'DISCOUNT', '2030-12-31');
        INSERT INTO coupons (user_id, type, expires) VALUES (456, 'DISCOUNT', '2030-12-31');
        ...
        实际上执行JDBC时，因为只有占位符参数不同，所以SQL实际上是一样的：

        for (var params : paramsList) {
            PreparedStatement ps = conn.preparedStatement("INSERT INTO coupons
            (user_id, type, expires) VALUES (?,?,?)");
            ps.setLong(params.get(0));
            ps.setString(params.get(1));
            ps.setString(params.get(2));
            ps.executeUpdate();
        }
        类似的还有，给每个员工薪水增加10%～30%：

        UPDATE employees SET salary = salary * ? WHERE id = ?
        通过一个循环来执行每个PreparedStatement虽然可行，但是性能很低。SQL数据库对SQL语句相同，
        但只有参数不同的若干语句可以作为batch执行，即批量执行，这种操作有特别优化，速度远远快于循环执行每个SQL。

        在JDBC代码中，我们可以利用SQL数据库的这一特性，把同一个SQL但参数不同的若干次操作合并为一个batch执行。
        我们以批量插入为例，示例代码如下：

         */
        String JDBC_URL = "jdbc:mysql://localhost:3306/learnjdbc";
        String JDBC_USER = "root";
        String JDBC_PASSWORD = "lishijin123";
        try(Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)){
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO students (name, gender, grade, score) VALUES (?, ?, ?, ?)")) {
                // 对同一个PreparedStatement反复设置参数并调用addBatch():
                Student2[] students = new Student2[3];
                students[0] = new Student2("Steven0",true,1,100);
                students[1] = new Student2("Steven1",true,1,100);
                students[2] = new Student2("Steven2",true,1,100);

                for (Student2 s : students) {
                    ps.setString(1, s.name);
                    ps.setBoolean(2, s.gender);
                    ps.setInt(3, s.grade);
                    ps.setInt(4, s.score);
                    ps.addBatch(); // 添加到batch
                }
                // 执行batch:
                int[] ns = ps.executeBatch();
                for (int n : ns) {
                    System.out.println(n + " inserted."); // batch中每个SQL执行的结果数量
                }
            }

        }

    }
}

class Student2 {
    public String name;
    public boolean gender;
    public int grade;
    public int score;

    public Student2(String name, boolean gender, int grade, int score) {
        this.name = name;
        this.gender = gender;
        this.grade = grade;
        this.score = score;
    }
}
