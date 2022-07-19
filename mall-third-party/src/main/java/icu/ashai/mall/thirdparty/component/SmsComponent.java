package icu.ashai.mall.thirdparty.component;

import icu.ashai.mall.thirdparty.config.SmsConfig;
import icu.ashai.mall.thirdparty.util.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ashai
 * @date 2022/7/19 22:16
 * @email ashai@gmail.com
 * @description
 */
@Component
public class SmsComponent {

    private final SmsConfig smsConfig;

    @Autowired
    public SmsComponent(SmsConfig smsConfig) {this.smsConfig = smsConfig;}

    public void sendSmsCode(String phone, String code) {
        String host = smsConfig.getHost();
        String path = smsConfig.getPath();
        String method = smsConfig.getMethod();
        String appcode = smsConfig.getAppcode();
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "APPCODE " + appcode);
        // 根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<>();
        Map<String, String> bodys = new HashMap<>();
        bodys.put("content", "code:" + code);
        bodys.put("phone_number", phone);
        bodys.put("template_id", smsConfig.getTemplateId());
        try {
            HttpUtils.doPost(host, path, method, headers, querys, bodys);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
