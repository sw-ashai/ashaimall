package icu.ashai.mall.auth.feign;

import icu.ashai.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ashai
 * @date 2022/7/19 22:51
 * @email ashai@gmail.com
 * @description
 */
@FeignClient("mall-third-party")
public interface ThirdPartFeignService {

    @GetMapping("/sms/sendCode")
    R sendCode(@RequestParam("phone") String phone,@RequestParam("code") String code);
}
