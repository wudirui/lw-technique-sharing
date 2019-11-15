package com.zr.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

/**
 * @ClassName
 * @Description
 * @Author
 * @Date 2019/11/15
 */
@RestController
@RequestMapping("test/")
public class Controller {
    private Logger logger = LoggerFactory.getLogger(Controller.class);

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value = "index/", method = RequestMethod.GET)
    public Object index() {

        String lockKey = "key_001";
        String valueKey = "key1";
        //Jedis jedis = new Jedis("localhost");
        try {
            Boolean b = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, "zhangrui");
            if (!b) {
                logger.error("error");
                return "error";
            }
            int num = Integer.parseInt(stringRedisTemplate.opsForValue().get(valueKey));
            if (num > 0) {
                int nowNum = num - 1;
                stringRedisTemplate.opsForValue().set(valueKey, nowNum + "");
                logger.info("减库存成功，剩余库存：{}", nowNum);
            } else {
                logger.info("减库存失败！！");
            }
        } finally {
            stringRedisTemplate.delete(lockKey);
        }
        return "end";
    }


}
