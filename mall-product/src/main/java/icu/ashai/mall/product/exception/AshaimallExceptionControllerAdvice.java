package icu.ashai.mall.product.exception;

import icu.ashai.common.exception.BizCodeEnum;
import icu.ashai.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ashai
 * @date 2021/12/11 下午 10:55
 * @Description
 */
@Slf4j
@RestControllerAdvice(basePackages = "icu.ashai.mall.product.controller")
public class AshaimallExceptionControllerAdvice {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleValidException(MethodArgumentNotValidException e) {
        log.error("数据校验出现问题{}，异常类型：{}", e.getMessage(), e.getClass());
        BindingResult bindingResult = e.getBindingResult();
        Map<String, Object> errorMap = new HashMap<>();
        bindingResult.getFieldErrors().forEach(item ->{
            errorMap.put(item.getField(),item.getDefaultMessage());
        });
        return R.error(BizCodeEnum.VALID_EXCEPTION.getCode(),BizCodeEnum.VALID_EXCEPTION.getMsg()).put("data",errorMap);
    }

    @ExceptionHandler(value = Exception.class)
    public R handleException(Throwable throwable){

        return R.error(BizCodeEnum.UNKNOWN_EXCEPTION.getCode(),BizCodeEnum.UNKNOWN_EXCEPTION.getMsg());
    }

}
