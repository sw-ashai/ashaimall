package icu.ashai.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import icu.ashai.common.utils.PageUtils;
import icu.ashai.mall.product.entity.SpuInfoEntity;
import icu.ashai.mall.product.vo.SpuSaveVo;

import java.util.Map;

/**
 * spu信息
 *
 * @author Ashai
 * @email ashai.cn@gmail.com
 * @date 2021-11-19 01:15:33
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存spu信息
     *
     * @param vo 保存数据
     */
    void saveSpuInfo(SpuSaveVo vo);

    /**
     * 保存spu基础信息
     *
     * @param spuInfoEntity 需要保存数据
     */
    void saveBaseSpuInf(SpuInfoEntity spuInfoEntity);

}

