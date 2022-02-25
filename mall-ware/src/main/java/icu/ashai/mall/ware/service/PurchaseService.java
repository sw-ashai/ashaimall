package icu.ashai.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import icu.ashai.common.utils.PageUtils;
import icu.ashai.mall.ware.entity.PurchaseEntity;
import icu.ashai.mall.ware.vo.PurchaseFinishVo;
import icu.ashai.mall.ware.vo.PurchaseMergeVo;

import java.util.List;
import java.util.Map;

/**
 * 采购信息
 *
 * @author Ashai
 * @email ashai.cn@gmail.com
 * @date 2021-11-20 15:57:27
 */
public interface PurchaseService extends IService<PurchaseEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查询未领取的采购单
     *
     * @param params 查询参数
     * @return result
     */
    PageUtils queryPageUnreceivedList(Map<String, Object> params);

    /**
     * 合并采购单
     *
     * @param purchaseMergeVo vo
     */
    void mergePurchase(PurchaseMergeVo purchaseMergeVo);

    /**
     * 领取采购单
     *
     * @param purchaseId 需要领取的采购单
     */
    void receivePurchase(List<Long> purchaseId);

    /**
     * 完成采购单
     *
     * @param purchaseFinishVo 完成采购单vo
     */
    void purchaseFinish(PurchaseFinishVo purchaseFinishVo);
}

