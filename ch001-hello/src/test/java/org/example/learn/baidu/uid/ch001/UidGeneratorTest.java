package org.example.learn.baidu.uid.ch001;

import com.baidu.fsg.uid.UidGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 简单验证 UidGenerator 注入与生成.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UidGeneratorTest {

    @Resource
    private UidGenerator uidGenerator;

    @Test
    public void testGenerateUid() {
        long uid1 = uidGenerator.getUID();
        long uid2 = uidGenerator.getUID();
        System.out.println("uid1 = " + uid1);
        System.out.println("uid2 = " + uid2);

        // 验证 1：两个 UID 必然不相等
        org.junit.Assert.assertNotEquals(uid1, uid2);

        // 验证 2：UID 必须为正数
        org.junit.Assert.assertTrue(uid1 > 0);

        // 验证 3: UID长度为18位数字
        org.junit.Assert.assertEquals(18, String.valueOf(uid1).length());
        org.junit.Assert.assertEquals(18, String.valueOf(uid2).length());
    }
}
