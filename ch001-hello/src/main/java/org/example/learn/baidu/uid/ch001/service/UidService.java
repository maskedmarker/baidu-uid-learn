package org.example.learn.baidu.uid.ch001.service;

import com.baidu.fsg.uid.UidGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * UID 业务服务层.
 *
 * <p>封装百度 UidGenerator，对上层屏蔽实现细节，调用方只需依赖本类即可生成 UID.
 */
@Service
public class UidService {

    @Resource
    private UidGenerator uidGenerator;

    /**
     * 生成一个唯一的 long 型 ID.
     */
    public long nextId() {
        return uidGenerator.getUID();
    }

    /**
     * 生成一个唯一的 String 型 ID（其实就是 long 转字符串）.
     */
    public String nextIdStr() {
        return Long.toString(uidGenerator.getUID());
    }
}
