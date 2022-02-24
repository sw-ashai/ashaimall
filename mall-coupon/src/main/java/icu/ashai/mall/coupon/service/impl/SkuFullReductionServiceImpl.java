package icu.ashai.mall.coupon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.ashai.common.to.SkuReductionTo;
import icu.ashai.common.utils.PageUtils;
import icu.ashai.common.utils.Query;
import icu.ashai.mall.coupon.dao.SkuFullReductionDao;
import icu.ashai.mall.coupon.entity.MemberPriceEntity;
import icu.ashai.mall.coupon.entity.SkuFullReductionEntity;
import icu.ashai.mall.coupon.entity.SkuLadderEntity;
import icu.ashai.mall.coupon.service.MemberPriceService;
import icu.ashai.mall.coupon.service.SkuFullReductionService;
import icu.ashai.mall.coupon.service.SkuLadderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("skuFullReductionService")
public class SkuFullReductionServiceImpl extends ServiceImpl<SkuFullReductionDao, SkuFullReductionEntity> implements SkuFullReductionService {

    /**
     * 商品阶梯价格service
     */
    private final SkuLadderService skuLadderService;

    /**
     * 会员价格service
     */
    private final MemberPriceService memberPriceService;

    @Autowired
    public SkuFullReductionServiceImpl(SkuLadderService skuLadderService, MemberPriceService memberPriceService) {
        this.skuLadderService = skuLadderService;
        this.memberPriceService = memberPriceService;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuFullReductionEntity> page = this.page(
                new Query<SkuFullReductionEntity>().getPage(params),
                new QueryWrapper<SkuFullReductionEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveSkuReduction(SkuReductionTo skuReductionTo) {
        SkuLadderEntity skuLadderEntity = new SkuLadderEntity();
        skuLadderEntity.setSkuId(skuReductionTo.getSkuId());
        skuLadderEntity.setFullCount(skuReductionTo.getFullCount());
        skuLadderEntity.setDiscount(skuReductionTo.getDiscount());
        skuLadderEntity.setAddOther(skuReductionTo.getCountStatus());
        if (skuReductionTo.getFullCount() > 0) {
            skuLadderService.save(skuLadderEntity);
        }

        SkuFullReductionEntity skuFullReductionEntity = new SkuFullReductionEntity();
        BeanUtils.copyProperties(skuReductionTo, skuFullReductionEntity);
        if (skuFullReductionEntity.getFullPrice().compareTo(BigDecimal.ZERO) > 0) {
            this.save(skuFullReductionEntity);
        }


        List<MemberPriceEntity> collect = skuReductionTo.getMemberPrice().stream().map(item -> {
            MemberPriceEntity memberPriceEntity = new MemberPriceEntity();
            memberPriceEntity.setSkuId(skuReductionTo.getSkuId());
            memberPriceEntity.setMemberLevelId(item.getId());
            memberPriceEntity.setMemberLevelName(item.getName());
            memberPriceEntity.setMemberPrice(item.getPrice());
            memberPriceEntity.setAddOther(1);
            return memberPriceEntity;
        }).filter(obj -> obj.getMemberPrice().compareTo(BigDecimal.ZERO) > 0).collect(Collectors.toList());
        memberPriceService.saveBatch(collect);
    }

}