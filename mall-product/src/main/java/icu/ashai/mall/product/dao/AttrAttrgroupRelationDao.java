package icu.ashai.mall.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import icu.ashai.mall.product.entity.AttrAttrgroupRelationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 属性&属性分组关联
 *
 * @author Ashai
 * @email ashai.cn@gmail.com
 * @date 2021-11-19 01:15:33
 */
@Mapper
public interface AttrAttrgroupRelationDao extends BaseMapper<AttrAttrgroupRelationEntity> {

    /**
     * 批量删除绑定关系
     *
     * @param relationEntityList 需要删除的关联关系
     */
    void deleteBatchRelation(@Param("relationList") List<AttrAttrgroupRelationEntity> relationEntityList);
}
