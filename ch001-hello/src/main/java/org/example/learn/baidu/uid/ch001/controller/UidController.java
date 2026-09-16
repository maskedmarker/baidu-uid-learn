package org.example.learn.baidu.uid.ch001.controller;

import org.example.learn.baidu.uid.ch001.service.UidService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 提供 HTTP 接口访问 UID 生成器.
 *
 * <p>启动应用后访问：
 * <ul>
 *   <li>http://localhost:8080/uid           —— 拿一个 long 类型的 ID</li>
 *   <li>http://localhost:8080/uid/str       —— 拿一个 String 类型的 ID</li>
 *   <li>http://localhost:8080/uid/batch?n=5 —— 批量拿 n 个 ID（默认 5）</li>
 * </ul>
 */
@RestController
public class UidController {

    @Resource
    private UidService uidService;

    @GetMapping("/uid")
    public Map<String, Object> uid() {
        long id = uidService.nextId();
        Map<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("type", "long");
        return result;
    }

    @GetMapping("/uid/str")
    public Map<String, Object> uidStr() {
        String id = uidService.nextIdStr();
        Map<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("type", "string");
        return result;
    }

    @GetMapping("/uid/batch")
    public Map<String, Object> uidBatch(Integer n) {
        int count = (n == null || n <= 0) ? 5 : Math.min(n, 100);
        Map<String, Object> result = new HashMap<>();
        result.put("count", count);
        result.put("ids", new java.util.ArrayList<Long>(count));
        for (int i = 0; i < count; i++) {
            ((java.util.List<Long>) result.get("ids")).add(uidService.nextId());
        }
        return result;
    }
}
