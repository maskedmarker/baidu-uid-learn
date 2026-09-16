package org.example.learn.baidu.uid.ch001.config;

import com.baidu.fsg.uid.exception.UidGenerateException;
import com.baidu.fsg.uid.worker.WorkerIdAssigner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 一个最简单的 WorkerIdAssigner 实现 —— 直接从配置文件读取 workerId.
 *
 * <p>背景：百度 UidGenerator 在生产环境通常通过数据库（{@code DisposableWorkerIdAssigner}）
 * 来注册并分配 workerId，以保证分布式环境下每个节点的 workerId 不重复.
 *
 * <p>本 Demo 为了避免引入 MySQL/MyBatis 等重依赖，直接从 application.yml 读取一个固定的 workerId.
 * 这种方式只适合单实例或学习场景，<b>生产环境请使用 DisposableWorkerIdAssigner</b>.
 *
 * @see com.baidu.fsg.uid.worker.WorkerIdAssigner
 */
@Component
public class SimpleWorkerIdAssigner implements WorkerIdAssigner {

    /**
     * 当前节点的 workerId，范围 0 ~ (2^workerBits - 1).
     */
    @Value("${uid-generator.worker-id:0}")
    private long workerId;

    @Override
    public long assignWorkerId() {
        // 简易校验：workerId 必须 >= 0（更严格的上界校验请在配置层控制）
        if (workerId < 0) {
            throw new UidGenerateException("workerId 不能为负数, 当前值: " + workerId);
        }
        return workerId;
    }
}
