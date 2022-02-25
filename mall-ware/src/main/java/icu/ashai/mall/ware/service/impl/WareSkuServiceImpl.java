package icu.ashai.mall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.ashai.common.utils.PageUtils;
import icu.ashai.common.utils.Query;
import icu.ashai.common.utils.R;
import icu.ashai.mall.ware.dao.WareSkuDao;
import icu.ashai.mall.ware.entity.WareSkuEntity;
import icu.ashai.mall.ware.feign.ProductFeignService;
import icu.ashai.mall.ware.service.WareSkuService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description 仓库sku serivce
 * @Author Ashai
 * @email ashai.cn@gmail.com
 * @Date 5:22 PM 2/25/2022
 */
@Service("wareSkuService")
public class WareSkuServiceImpl extends ServiceImpl<WareSkuDao, WareSkuEntity> implements WareSkuService {

    /**
     * 商品系统远程调用service
     */
    private final ProductFeignService productFeignService;

    @Autowired
    public WareSkuServiceImpl(ProductFeignService productFeignService) {
        this.productFeignService = productFeignService;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String wareId = (String) params.get("wareId");
        String skuId = (String) params.get("skuId");

        IPage<WareSkuEntity> page = this.page(
                new Query<WareSkuEntity>().getPage(params),
                new LambdaQueryWrapper<WareSkuEntity>()
                        .eq(StringUtils.isNotBlank(wareId), WareSkuEntity::getWareId, wareId)
                        .eq(StringUtils.isNotBlank(skuId), WareSkuEntity::getSkuId, skuId)
        );

        return new PageUtils(page);
    }

    @Override
    public void addStock(Long skuId, Long wareId, Integer skuNum) {
        List<WareSkuEntity> list = this.list(new LambdaQueryWrapper<WareSkuEntity>()
                .eq(WareSkuEntity::getSkuId, skuId)
                .eq(WareSkuEntity::getWareId, wareId)
        );

        if (list == null || list.isEmpty()) {
            WareSkuEntity wareSkuEntity = new WareSkuEntity();
            wareSkuEntity.setSkuId(skuId);
            wareSkuEntity.setWareId(wareId);
            wareSkuEntity.setStock(skuNum);
            wareSkuEntity.setStockLocked(0);
            // TODO: 2/25/2022 5:28 PM Ashai 高级部分在出现异常后不回滚的另一种方法
            try {
                R info = productFeignService.info(skuId);
                if (info.getCode() == 0) {
                    Map<String, Object> data = (Map<String, Object>) info.get("data");
                    wareSkuEntity.setSkuName((String) data.get("skuName"));
                }
                this.save(wareSkuEntity);
            } catch (Exception ignored) {

            }
        } else {
            this.baseMapper.addStock(skuId, wareId, skuNum);
        }
    }

}