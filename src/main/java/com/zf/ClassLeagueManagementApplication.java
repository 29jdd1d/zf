package com.zf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 校园班团管理系统主启动类
 * 
 * @author zf
 * @since 2026-01-01
 */
@SpringBootApplication
@MapperScan("com.zf.mapper")
public class ClassLeagueManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClassLeagueManagementApplication.class, args);
        System.out.println("========================================");
        System.out.println("校园班团管理系统启动成功!");
        System.out.println("API文档地址: http://localhost:8080/api/doc.html");
        System.out.println("========================================");
    }
}
