package org.example.learn.baidu.uid.ch001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ch001-hello 模块的 Spring Boot 启动类.
 *
 * <p>本 Demo 演示了如何集成百度开源的 UidGenerator 分布式唯一 ID 生成器.
 * 启动后访问 http://localhost:8080/uid 即可获取一个唯一的 64 位 long 型 ID.
 */
@SpringBootApplication
public class Ch001HelloApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ch001HelloApplication.class, args);
    }
}
