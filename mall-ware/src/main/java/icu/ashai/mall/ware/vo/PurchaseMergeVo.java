package icu.ashai.mall.ware.vo;

import lombok.Data;

import java.util.List;

/**
 * @author Ashai
 * @date 2/24/2022 11:28 PM
 * @Description 合并采购vo
 */
@Data
public class PurchaseMergeVo {

    /**
     * 采购单id
     */
    private Long purchaseId;
    /**
     * 需要合并的采购详情id列表
     */
    private List<Long> items;
}
