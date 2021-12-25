package icu.ashai.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import icu.ashai.common.utils.PageUtils;
import icu.ashai.mall.product.entity.AttrGroupEntity;

import java.util.Map;

/**
 * 属性分组
 *
 * @author Ashai
 * @email ashai.cn@gmail.com
 * @date 2021-11-19 01:15:33
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    /**
     * 查询页面
     *
     * @param params 查询条件
     * @return 查询结果
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查询页面
     *
     * @param params     查询条件
     * @param categoryId 三级分类id
     * @return 查询结果
     */
    PageUtils queryPage(Map<String, Object> params, Long categoryId);

    /**
     * 获取详细信息
     * @param attrGroupId 属性分组id
     * @return 查询结果
     */
    AttrGroupEntity getInfo(Long attrGroupId);
}

