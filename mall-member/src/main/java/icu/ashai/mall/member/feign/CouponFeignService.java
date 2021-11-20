package icu.ashai.mall.member.feign;

import icu.ashai.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Ashai
 * @date 2021/11/20 下午 06:03
 * @Description
 */
@FeignClient("mall-coupon")
@Component
public interface CouponFeignService {

    @GetMapping("/coupon/coupon/member/list")
    public R memberCoupons();
}
