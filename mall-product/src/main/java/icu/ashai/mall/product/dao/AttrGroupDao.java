package icu.ashai.mall.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import icu.ashai.mall.product.entity.AttrGroupEntity;
import icu.ashai.mall.product.vo.SkuItemVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 属性分组
 * 
 * @author Ashai
 * @email ashai.cn@gmail.com
 * @date 2021-11-19 01:15:33
 */
@Mapper
public interface AttrGroupDao extends BaseMapper<AttrGroupEntity> {

    List<SkuItemVo.SpuItemAttrGroupVo> getAttrsGroupWithAttrsBySpuId(@Param("spuId") Long spuId,@Param("catalogId") Long catalogId);
}
