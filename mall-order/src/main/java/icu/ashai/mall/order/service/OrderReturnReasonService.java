package icu.ashai.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import icu.ashai.common.utils.PageUtils;
import icu.ashai.mall.order.entity.OrderReturnReasonEntity;

import java.util.Map;

/**
 * 退货原因
 *
 * @author Ashai
 * @email ashai.cn@gmail.com
 * @date 2021-11-20 15:56:25
 */
public interface OrderReturnReasonService extends IService<OrderReturnReasonEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

