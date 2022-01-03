package icu.ashai.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import icu.ashai.common.utils.PageUtils;
import icu.ashai.mall.product.entity.BrandEntity;

import java.util.Map;

/**
 * 品牌
 *
 * @author Ashai
 * @email ashai.cn@gmail.com
 * @date 2021-11-19 01:15:33
 */
public interface BrandService extends IService<BrandEntity> {

    /**
     * 查询页面
     *
     * @param params 查询条件
     * @return 查询结果
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 更新详细信息
     *
     * @param brand 品牌类
     */
    void updateDetail(BrandEntity brand);
}

