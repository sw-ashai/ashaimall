package icu.ashai.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import icu.ashai.common.utils.PageUtils;
import icu.ashai.mall.product.entity.AttrEntity;
import icu.ashai.mall.product.vo.AttrRespVo;
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
     * @param attrType
     * @return pageUtils
     */
    PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId, String attrType);

    /**
     * 根据分类属性id获取详细信息
     *
     * @param attrId 分类属性id
     * @return result
     */
    AttrRespVo getAttrInfo(Long attrId);

    /**
     * 修改规格参数
     *
     * @param attr 属性vo
     */
    void updateAttr(AttrVo attr);
}

