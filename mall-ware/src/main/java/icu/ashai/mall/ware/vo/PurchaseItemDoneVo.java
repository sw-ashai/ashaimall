package icu.ashai.mall.ware.vo;

import lombok.Data;

/**
 * @author Ashai
 * @date 2/25/2022 4:32 PM
 * @Description
 */
@Data
public class PurchaseItemDoneVo {

    /**
     * 采购需求id
     */
    private Long itemId;
    /**
     * 采购需求状态
     */
    private Integer status;
    /**
     * 原因
     */
    private String reason;
}
