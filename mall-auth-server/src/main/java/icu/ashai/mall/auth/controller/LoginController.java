package icu.ashai.mall.auth.controller;

import icu.ashai.common.constant.AuthServerConstant;
import icu.ashai.common.exception.BizCodeEnum;
import icu.ashai.common.utils.R;
import icu.ashai.mall.auth.feign.ThirdPartFeignService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author ashai
 * @date 2022/7/19 22:50
 * @email ashai@gmail.com
 * @description
 */
@RestController
public class LoginController {

    private final ThirdPartFeignService thirdPartFeignService;

    private final StringRedisTemplate redisTemplate;

    @Autowired
    public LoginController(ThirdPartFeignService thirdPartFeignService, StringRedisTemplate redisTemplate) {
        this.thirdPartFeignService = thirdPartFeignService;
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/sms/sendCode")
    public R sendCode(@RequestParam String phone) {
        //todo 接口防刷

        //60秒后才可以再次发送
        String redisKey = AuthServerConstant.SMS_CODE_CACHE_PREFIX + phone;
        String redisCode = redisTemplate.opsForValue().get(redisKey);
        if (StringUtils.isNotBlank(redisCode)) {
            String[] s = redisCode.split("_");
            long time = Long.parseLong(s[1]);
            if ((System.currentTimeMillis() - time) < 60 * 1000) {
                return R.error(BizCodeEnum.SMS_CODE_EXCEPTION);
            }
        }
        String code = UUID.randomUUID().toString().substring(0, 5);
        // 将验证码保存到redis 5分钟后过期
        redisTemplate.opsForValue().set(redisKey, code + "_" + System.currentTimeMillis(), 5, TimeUnit.MINUTES);

        return thirdPartFeignService.sendCode(phone, code);
    }

}
