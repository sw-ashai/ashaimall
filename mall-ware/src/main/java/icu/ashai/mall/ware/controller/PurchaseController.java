package icu.ashai.mall.ware.controller;

import icu.ashai.common.utils.PageUtils;
import icu.ashai.common.utils.R;
import icu.ashai.mall.ware.entity.PurchaseEntity;
import icu.ashai.mall.ware.service.PurchaseService;
import icu.ashai.mall.ware.vo.PurchaseFinishVo;
import icu.ashai.mall.ware.vo.PurchaseMergeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 采购信息
 *
 * @author Ashai
 * @email ashai.cn@gmail.com
 * @date 2021-11-20 15:57:27
 */
@RestController
@RequestMapping("ware/purchase")
public class PurchaseController {
    /**
     * 采购service
     */
    private final PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = purchaseService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 查询未领取的采购但
     */
    @RequestMapping("/unreceive/list")
    public R unreceivedList(@RequestParam Map<String, Object> params) {
        PageUtils page = purchaseService.queryPageUnreceivedList(params);

        return R.ok().put("page", page);
    }

    /**
     * 合并采购单
     *
     * @param purchaseMergeVo 合并采购单vo
     * @return result
     */
    @PostMapping("/merge")
    public R mergePurchase(@RequestBody PurchaseMergeVo purchaseMergeVo) {
        purchaseService.mergePurchase(purchaseMergeVo);
        return R.ok();
    }

    /**
     * 领取采购单
     *
     * @param purchaseId 采购单id
     * @return result
     */
    @PostMapping("/received")
    public R receivePurchase(@RequestBody List<Long> purchaseId) {
        purchaseService.receivePurchase(purchaseId);
        return R.ok();
    }


    @PostMapping("/done")
    public R purchaseFinish(@RequestBody PurchaseFinishVo purchaseFinishVo) {
        purchaseService.purchaseFinish(purchaseFinishVo);
        return R.ok();
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        PurchaseEntity purchase = purchaseService.getById(id);

        return R.ok().put("purchase", purchase);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody PurchaseEntity purchase) {
        purchase.setCreateTime(new Date());
        purchase.setUpdateTime(new Date());
        purchaseService.save(purchase);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody PurchaseEntity purchase){
		purchaseService.updateById(purchase);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		purchaseService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
