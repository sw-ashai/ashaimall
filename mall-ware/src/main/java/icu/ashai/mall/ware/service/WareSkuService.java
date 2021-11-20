package icu.ashai.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import icu.ashai.common.utils.PageUtils;
import icu.ashai.mall.ware.entity.WareSkuEntity;

import java.util.Map;

/**
 * 商品库存
 *
 * @author Ashai
 * @email ashai.cn@gmail.com
 * @date 2021-11-20 15:57:27
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

