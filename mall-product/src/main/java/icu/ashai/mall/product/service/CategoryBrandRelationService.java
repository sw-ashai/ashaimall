package icu.ashai.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import icu.ashai.common.utils.PageUtils;
import icu.ashai.mall.product.entity.CategoryBrandRelationEntity;

import java.util.Map;

/**
 * 品牌分类关联
 *
 * @author Ashai
 * @email ashai.cn@gmail.com
 * @date 2021-11-19 01:15:33
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    /**
     * 查询页面数据
     *
     * @param params 查询条件
     * @return 查询结果
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存详细信息
     *
     * @param categoryBrandRelation 需要保存的信息
     */
    void saveDetail(CategoryBrandRelationEntity categoryBrandRelation);

    /**
     * 更新品牌的名字
     *
     * @param brandId 品牌id
     * @param name    名字
     */
    void updateBrand(Long brandId, String name);

    /**
     * 更新分类名
     *
     * @param catId 分类id
     * @param name  分类名称
     */
    void updateCategory(Long catId, String name);
}

