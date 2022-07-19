package icu.ashai.mall.thirdparty.controller;

import icu.ashai.common.utils.R;
import icu.ashai.mall.thirdparty.component.SmsComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ashai
 * @date 2022/7/19 22:46
 * @email ashai@gmail.com
 * @description 短信发送
 */
@RestController
@RequestMapping("/sms")
@Slf4j
public class SmsSendController {

    private final SmsComponent smsComponent;

    @Autowired
    public SmsSendController(SmsComponent smsComponent) {this.smsComponent = smsComponent;}

    /**
     * 提供给别的服务调用
     *
     * @param phone 手机号
     * @param code  验证码
     */
    @GetMapping("/sendCode")
    public R sendCode(@RequestParam("phone") String phone, @RequestParam("code") String code) {
        smsComponent.sendSmsCode(phone, code);
        return R.ok();
    }
}
