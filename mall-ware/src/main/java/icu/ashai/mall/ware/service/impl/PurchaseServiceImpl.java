package icu.ashai.mall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.ashai.common.constant.WareConstant;
import icu.ashai.common.utils.PageUtils;
import icu.ashai.common.utils.Query;
import icu.ashai.mall.ware.dao.PurchaseDao;
import icu.ashai.mall.ware.entity.PurchaseDetailEntity;
import icu.ashai.mall.ware.entity.PurchaseEntity;
import icu.ashai.mall.ware.service.PurchaseDetailService;
import icu.ashai.mall.ware.service.PurchaseService;
import icu.ashai.mall.ware.service.WareSkuService;
import icu.ashai.mall.ware.vo.PurchaseFinishVo;
import icu.ashai.mall.ware.vo.PurchaseItemDoneVo;
import icu.ashai.mall.ware.vo.PurchaseMergeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description 采购单service
 * @Author Ashai
 * @email ashai.cn@gmail.com
 * @Date 11:31 PM 2/24/2022
 */
@Service("purchaseService")
public class PurchaseServiceImpl extends ServiceImpl<PurchaseDao, PurchaseEntity> implements PurchaseService {

    /**
     * 采购单详情service
     */
    private final PurchaseDetailService purchaseDetailService;

    /**
     * 仓库sku service
     */
    private final WareSkuService wareSkuService;

    @Autowired
    public PurchaseServiceImpl(PurchaseDetailService purchaseDetailService, WareSkuService wareSkuService) {
        this.purchaseDetailService = purchaseDetailService;
        this.wareSkuService = wareSkuService;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PurchaseEntity> page = this.page(
                new Query<PurchaseEntity>().getPage(params),
                new QueryWrapper<PurchaseEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageUnreceivedList(Map<String, Object> params) {
        IPage<PurchaseEntity> page = this.page(
                new Query<PurchaseEntity>().getPage(params),
                new LambdaQueryWrapper<PurchaseEntity>()
                        .eq(PurchaseEntity::getStatus, 0).or().eq(PurchaseEntity::getStatus, 1)
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void mergePurchase(PurchaseMergeVo purchaseMergeVo) {

        List<Long> items = purchaseMergeVo.getItems();

        if (purchaseMergeVo.getPurchaseId() == null) {
            PurchaseEntity purchaseEntity = new PurchaseEntity();
            purchaseEntity.setCreateTime(new Date());
            purchaseEntity.setUpdateTime(new Date());
            purchaseEntity.setStatus(WareConstant.PurchaseStatusEnum.CREATED.getCode());
            this.save(purchaseEntity);

            List<PurchaseDetailEntity> collect = items.stream().map(item -> {
                PurchaseDetailEntity purchaseDetailEntity = new PurchaseDetailEntity();
                purchaseDetailEntity.setPurchaseId(purchaseEntity.getId());
                purchaseDetailEntity.setStatus(WareConstant.PurchaseDetailStatusEnum.ASSIGNED.getCode());
                purchaseDetailEntity.setId(item);
                return purchaseDetailEntity;
            }).collect(Collectors.toList());
            purchaseDetailService.updateBatchById(collect);

        } else {
            PurchaseEntity byId = this.getById(purchaseMergeVo.getPurchaseId());
            if (byId.getStatus() == WareConstant.PurchaseStatusEnum.CREATED.getCode() || byId.getStatus() == WareConstant.PurchaseStatusEnum.ASSIGNED.getCode()) {
                List<PurchaseDetailEntity> collect = items.stream().map(item -> {
                    PurchaseDetailEntity purchaseDetailEntity = new PurchaseDetailEntity();
                    purchaseDetailEntity.setPurchaseId(purchaseMergeVo.getPurchaseId());
                    purchaseDetailEntity.setStatus(WareConstant.PurchaseDetailStatusEnum.ASSIGNED.getCode());
                    purchaseDetailEntity.setId(item);
                    return purchaseDetailEntity;
                }).collect(Collectors.toList());
                purchaseDetailService.updateBatchById(collect);

                this.update(new LambdaUpdateWrapper<PurchaseEntity>()
                        .set(PurchaseEntity::getUpdateTime, new Date())
                        .eq(PurchaseEntity::getId, purchaseMergeVo.getPurchaseId())
                );
            }

        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void receivePurchase(List<Long> purchaseId) {
        List<PurchaseEntity> purchaseEntities = this.listByIds(purchaseId);
//        修改采购单状态
        List<PurchaseEntity> collect = purchaseEntities.stream()
                .filter(item -> item.getStatus() == WareConstant.PurchaseStatusEnum.CREATED.getCode() || item.getStatus() == WareConstant.PurchaseStatusEnum.ASSIGNED.getCode())
                .peek(item -> {
                    item.setStatus(WareConstant.PurchaseStatusEnum.RECEIVE.getCode());
                    item.setUpdateTime(new Date());
                })
                .collect(Collectors.toList());

        this.updateBatchById(collect);
//        修改采购需求状态
        collect.forEach(item -> {
            List<PurchaseDetailEntity> list = purchaseDetailService.list(new LambdaQueryWrapper<PurchaseDetailEntity>().eq(PurchaseDetailEntity::getPurchaseId, item.getId()));
            List<PurchaseDetailEntity> collect1 = list.stream().map(entity -> {
                PurchaseDetailEntity purchaseDetailEntity = new PurchaseDetailEntity();
                purchaseDetailEntity.setId(entity.getId());
                purchaseDetailEntity.setStatus(WareConstant.PurchaseDetailStatusEnum.BUYING.getCode());
                return purchaseDetailEntity;
            }).collect(Collectors.toList());
            purchaseDetailService.updateBatchById(collect1);
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void purchaseFinish(PurchaseFinishVo purchaseFinishVo) {

//        更新采购需求状态
        boolean flag = true;

        ArrayList<PurchaseDetailEntity> updateDetails = new ArrayList<>();

        for (PurchaseItemDoneVo item : purchaseFinishVo.getItems()) {
            if (item.getStatus() == WareConstant.PurchaseDetailStatusEnum.HAS_ERROR.getCode()) {
                flag = false;
            } else {
                //        采购的商品入库
                PurchaseDetailEntity byId = purchaseDetailService.getById(item.getItemId());
                wareSkuService.addStock(byId.getSkuId(), byId.getWareId(), byId.getSkuNum());
            }
            PurchaseDetailEntity purchaseDetailEntity = new PurchaseDetailEntity();
            purchaseDetailEntity.setId(item.getItemId());
            purchaseDetailEntity.setStatus(item.getStatus());
            updateDetails.add(purchaseDetailEntity);
        }
        purchaseDetailService.updateBatchById(updateDetails);

//        更新采购单状态
        PurchaseEntity purchaseEntity = new PurchaseEntity();
        purchaseEntity.setId(purchaseFinishVo.getId());
        purchaseEntity.setUpdateTime(new Date());
        purchaseEntity.setStatus(flag ? WareConstant.PurchaseStatusEnum.FINISH.getCode() : WareConstant.PurchaseStatusEnum.HAS_ERROR.getCode());
        this.updateById(purchaseEntity);

    }

}