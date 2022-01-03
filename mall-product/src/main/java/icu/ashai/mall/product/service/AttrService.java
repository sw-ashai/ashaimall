package icu.ashai.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import icu.ashai.common.utils.PageUtils;
import icu.ashai.mall.product.entity.AttrEntity;
import icu.ashai.mall.product.vo.AttrVo;

import java.util.Map;

/**
 * 商品属性
 *
 * @author Ashai
 * @email ashai.cn@gmail.com
 * @date 2021-11-19 01:15:32
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存属性信息
     *
     * @param attr 属性vo
     */
    void saveAttrVo(AttrVo attr);

    /**
     * 查询规格参数页面信息
     *
     * @param params    查询参数
     * @param catelogId 分类id
     * @return pageUtils
     */
    PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId);
}

