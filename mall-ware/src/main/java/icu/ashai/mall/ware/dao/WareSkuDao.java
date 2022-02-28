package icu.ashai.mall.ware.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import icu.ashai.mall.ware.entity.WareSkuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 商品库存
 *
 * @author Ashai
 * @email ashai.cn@gmail.com
 * @date 2021-11-20 15:57:27
 */
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {

    /**
     * 添加库存
     *
     * @param skuId  sku id
     * @param wareId 仓库id
     * @param skuNum sku 数量
     */
    void addStock(@Param("skuId") Long skuId, @Param("wareId") Long wareId, @Param("skuNum") Integer skuNum);

    /**
     * 获取sku库存两
     * @param skuId  sku id
     * @return 库存量
     */
    Long getSkuStock(@Param("skuId") Long skuId);
}
