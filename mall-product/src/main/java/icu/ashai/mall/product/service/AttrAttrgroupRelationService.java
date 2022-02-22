package icu.ashai.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import icu.ashai.common.utils.PageUtils;
import icu.ashai.mall.product.entity.AttrAttrgroupRelationEntity;
import icu.ashai.mall.product.vo.AttrGroupRelationVo;

import java.util.List;
import java.util.Map;

/**
 * 属性&属性分组关联
 *
 * @author Ashai
 * @email ashai.cn@gmail.com
 * @date 2021-11-19 01:15:33
 */
public interface AttrAttrgroupRelationService extends IService<AttrAttrgroupRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 添加关联关系
     *
     * @param attrGroupRelationVoList 属性%属性分组的关联关系列表
     */
    void saveBatch(List<AttrGroupRelationVo> attrGroupRelationVoList);

}

