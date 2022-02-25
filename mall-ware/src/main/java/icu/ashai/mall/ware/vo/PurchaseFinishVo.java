package icu.ashai.mall.ware.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Ashai
 * @date 2/25/2022 4:31 PM
 * @Description 采购单完成vo
 */
@Data
public class PurchaseFinishVo {
    /**
     * 采购单id
     */
    @NotNull
    private Long id;

    /**
     * 完成/失败的需求详情
     */
    private List<PurchaseItemDoneVo> items;
}
