package com.help.helpme.controller.sys;


import com.help.helpme.utils.RedisLockUp;
import com.help.helpme.utils.RedisUnLock;
import com.help.helpme.utils.Result;
import com.help.helpme.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.UUID;

/**
 * @description: redis - 缓存
 * @author: zhangshuai
 * @create: 2018-11-14 15:00
 */
@RestController
@RequestMapping("/redis")
@Api(description = "redis缓存")
public class RedisController {
    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private JedisPool jedisPool;

    @ApiOperation(
            value = "redis测试",
            notes = "redis测试",
            consumes = "application/json",
            responseReference = "com.help.helpme.utils.Result"
    )
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/test",method=RequestMethod.GET)
    public Result test() {

        // 从连接池中获取连接
        Jedis jedis = jedisPool.getResource();

        String requestId = UUID.randomUUID().toString();

        String lockKey = "test";
        //加锁
        Boolean lock = RedisLockUp.tryGetDistributedLock(jedis, lockKey,requestId , 2000);

        if(lock){
            //加锁成功
            logger.info("加锁成功并处理业务,key="+lockKey+",requestId="+requestId);
        }else {
            //关闭客户端
            jedis.close();
            return ResultUtil.errorExceptionMsg("加锁失败");
        }

        logger.info("业务处理完成开始解锁");
        //解锁
        boolean unLock = RedisUnLock.releaseDistributedLock(jedis, lockKey, requestId);
        if(unLock){
            logger.info("解锁成功,key="+lockKey+",requestId="+requestId);
        }


        //关闭客户端
        jedis.close();

        return ResultUtil.success();
    }

}
