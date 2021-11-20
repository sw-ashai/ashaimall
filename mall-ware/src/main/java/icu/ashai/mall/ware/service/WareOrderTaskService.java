package icu.ashai.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import icu.ashai.common.utils.PageUtils;
import icu.ashai.mall.ware.entity.WareOrderTaskEntity;

import java.util.Map;

/**
 * 库存工作单
 *
 * @author Ashai
 * @email ashai.cn@gmail.com
 * @date 2021-11-20 15:57:27
 */
public interface WareOrderTaskService extends IService<WareOrderTaskEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

