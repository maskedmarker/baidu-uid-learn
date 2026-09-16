package org.example.learn.baidu.uid.ch001.config;

import com.baidu.fsg.uid.impl.CachedUidGenerator;
import com.baidu.fsg.uid.worker.WorkerIdAssigner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * UidGenerator 的 Spring 配置.
 *
 * <p>百度 UidGenerator 提供两种实现：
 * <ul>
 *   <li>{@link com.baidu.fsg.uid.impl.DefaultUidGenerator} —— 实时生成，无缓存</li>
 *   <li>{@link com.baidu.fsg.uid.impl.CachedUidGenerator} —— 基于 RingBuffer 预生成 UID，
 *       <b>官方推荐在性能敏感场景使用</b></li>
 * </ul>
 *
 * <p>ID 组成（默认）：<br>
 * sign(1bit) | deltaSeconds(28bits) | workerId(22bits) | sequence(13bits) = 64bits
 */
@Configuration
public class UidGeneratorConfig {

    /**
     * 配置 CachedUidGenerator Bean.
     *
     * <p>参数说明：
     * <ul>
     *   <li>timeBits=28：相对起始时间（epoch）所占的秒数位数，约支持 8.5 年</li>
     *   <li>workerBits=22：机器标识位数，最多支持约 419 万个节点</li>
     *   <li>seqBits=13：每秒内的序列号位数，单机单秒最多生成 8192 个 ID</li>
     *   <li>epochStr="2023-01-01"：起始时间（之后的时间相对它计算）</li>
     *   <li>boostPower=3：RingBuffer 扩容倍数，最终容量 = 8192 &lt;&lt; 3 = 65536</li>
     * </ul>
     *
     * <p>注意：CachedUidGenerator 的 paddingFactor 字段未暴露 setter，
     * 内部默认使用 {@link com.baidu.fsg.uid.buffer.RingBuffer#DEFAULT_PADDING_PERCENT}（即 50）.
     */
    @Bean
    public CachedUidGenerator cachedUidGenerator(WorkerIdAssigner workerIdAssigner) {
        CachedUidGenerator generator = new CachedUidGenerator();
        generator.setWorkerIdAssigner(workerIdAssigner);

        // —— 可选参数，使用默认值也 OK，按需覆盖 ——
        generator.setTimeBits(28);
        generator.setWorkerBits(22);
        generator.setSeqBits(13);
        generator.setEpochStr("2023-01-01");

        // RingBuffer 扩容倍数（提升吞吐量）
        generator.setBoostPower(3);

        return generator;
    }
}
