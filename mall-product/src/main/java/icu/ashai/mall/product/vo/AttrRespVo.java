package icu.ashai.mall.product.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Ashai
 * @date 2022/1/3 下午 06:47
 * @Description
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AttrRespVo extends AttrVo {

    /**
     * 分类名
     */
    private String catelogName;
    /**
     * 分组名
     */
    private String groupName;

}
