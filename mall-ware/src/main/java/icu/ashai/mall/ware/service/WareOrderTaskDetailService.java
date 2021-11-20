package icu.ashai.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import icu.ashai.common.utils.PageUtils;
import icu.ashai.mall.ware.entity.WareOrderTaskDetailEntity;

import java.util.Map;

/**
 * 库存工作单
 *
 * @author Ashai
 * @email ashai.cn@gmail.com
 * @date 2021-11-20 15:57:27
 */
public interface WareOrderTaskDetailService extends IService<WareOrderTaskDetailEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

