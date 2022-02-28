package icu.ashai.mall.product.dao;

import icu.ashai.mall.product.entity.SpuInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * spu信息
 * 
 * @author Ashai
 * @email ashai.cn@gmail.com
 * @date 2021-11-19 01:15:33
 */
@Mapper
public interface SpuInfoDao extends BaseMapper<SpuInfoEntity> {
    /**
     * 更新spu 状态
     * @param spuId spu id
     * @param code 修改的状态码
     */
    void updateSpuStatus(@Param("spuId") Long spuId, @Param("code") int code);
}
