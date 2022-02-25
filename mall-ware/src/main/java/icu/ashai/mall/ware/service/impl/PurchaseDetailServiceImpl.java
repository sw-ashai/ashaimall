package icu.ashai.mall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.ashai.common.utils.PageUtils;
import icu.ashai.common.utils.Query;
import icu.ashai.mall.ware.dao.PurchaseDetailDao;
import icu.ashai.mall.ware.entity.PurchaseDetailEntity;
import icu.ashai.mall.ware.service.PurchaseDetailService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("purchaseDetailService")
public class PurchaseDetailServiceImpl extends ServiceImpl<PurchaseDetailDao, PurchaseDetailEntity> implements PurchaseDetailService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String status = (String) params.get("status");
        String wareId = (String) params.get("wareId");
        String key = (String) params.get("key");

        IPage<PurchaseDetailEntity> page = this.page(
                new Query<PurchaseDetailEntity>().getPage(params),
                new LambdaQueryWrapper<PurchaseDetailEntity>()
                        .and(StringUtils.isNotBlank(key), wrapper -> {
                            wrapper.eq(PurchaseDetailEntity::getId, key).or()
                                    .eq(PurchaseDetailEntity::getPurchaseId, key);
                        })
                        .eq(StringUtils.isNotBlank(status), PurchaseDetailEntity::getStatus, status)
                        .eq(StringUtils.isNotBlank(wareId), PurchaseDetailEntity::getWareId, wareId)
        );

        return new PageUtils(page);
    }

}