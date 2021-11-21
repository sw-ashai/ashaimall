package icu.ashai.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import icu.ashai.common.utils.PageUtils;
import icu.ashai.mall.product.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author Ashai
 * @email ashai.cn@gmail.com
 * @date 2021-11-19 01:15:33
 */
public interface CategoryService extends IService<CategoryEntity> {


    /**
     * 常规列表查询
     * @param params 查询参数
     * @return list
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 三级分类获取
     * @return list
     */
    List<CategoryEntity> listWithTree();

    /**
     * 批量删除菜单
     * @param catIds 需要删除的列表
     */
    void removeMenuByIds(List<Long> catIds);
}

