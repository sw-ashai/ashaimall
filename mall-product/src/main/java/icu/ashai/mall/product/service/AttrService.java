package icu.ashai.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import icu.ashai.common.utils.PageUtils;
import icu.ashai.mall.product.entity.AttrEntity;
import icu.ashai.mall.product.vo.AttrGroupRelationVo;
import icu.ashai.mall.product.vo.AttrRespVo;
import icu.ashai.mall.product.vo.AttrVo;

import java.util.List;
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

    /**
     * 获取属性分组的所有属性
     *
     * @param attrgroupId 属性分组id
     * @return 查询结果
     */
    List<AttrEntity> getRelationAttr(Long attrgroupId);

    /**
     * 删除绑定关系
     *
     * @param attrGroupRelationVoList 需要删除的绑定关系
     */
    void deleteRelation(List<AttrGroupRelationVo> attrGroupRelationVoList);

    /**
     * 获取所有可关联的属性
     *
     * @param params      模糊查询参数
     * @param attrgroupId 分组属性id
     * @return 分页模型
     */
    PageUtils getNoRelation(Map<String, Object> params, Long attrgroupId);

    /**
     * 根据id获取属性集合，并且属性为可检索属性
     *
     * @param attrIds 属性id
     * @return 属性列表
     */
    List<Long> selectSearchAttrIds(List<Long> attrIds);
}

