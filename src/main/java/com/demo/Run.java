package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.util.StopWatch;
import ru.yandex.clickhouse.ClickHouseConnectionImpl;

import java.sql.SQLException;

/**
 * @author: koen.zhang
 * @email: zhangzhi@gmail.com
 * @date: 2021-05-26 19:56
 **/
@SpringBootApplication
@ServletComponentScan
public class Run {

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("run");

        String result = testjdbc();
        System.out.println(result);
        SpringApplication.run(Run.class, args);
        stopWatch.stop();


    }

    public static String testjdbc() {


        ClickHouseConnectionImpl clickHouseConnection = null;
        try {
            String jdbc= "jdbc:clickhouse://lcaolhost:8123?user=app&password=123456";

            clickHouseConnection = new ClickHouseConnectionImpl(jdbc);


//            ClickHouseDataSource dataSource = new ClickHouseDataSource(jdbc);
//            ClickHouseConnection clickHouseConnection = dataSource.getConnection();
            return "success=============:" + clickHouseConnection.getServerTimeZone() + ", version:" + clickHouseConnection.getServerVersion();



        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            return "error=============:";


        } finally {
            clickHouseConnection = null;
        }
    }
}