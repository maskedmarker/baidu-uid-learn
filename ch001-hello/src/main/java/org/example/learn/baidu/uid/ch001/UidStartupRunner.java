package org.example.learn.baidu.uid.ch001;

import com.baidu.fsg.uid.UidGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 启动时生成几个测试 ID 打印到控制台，方便学习者直观感受 UidGenerator 的工作.
 *
 * <p>一个 long 型 ID 形如：<br>
 * {@code 7118901042711623881} —— 19-20 位数字，刚好放进 long 的 64 位.
 */
@Component
public class UidStartupRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(UidStartupRunner.class);

    @Resource
    private UidGenerator uidGenerator;

    @Override
    public void run(String... args) {
        log.info("================= UidGenerator 启动自检 =================");
        for (int i = 0; i < 5; i++) {
            long uid = uidGenerator.getUID();
            log.info("生成第 {} 个 UID = {}", i + 1, uid);
        }
        log.info("=========================================================");
        log.info("接下来可以访问以下地址试用:");
        log.info("  http://localhost:8080/uid");
        log.info("  http://localhost:8080/uid/str");
        log.info("  http://localhost:8080/uid/batch?n=10");
    }
}
