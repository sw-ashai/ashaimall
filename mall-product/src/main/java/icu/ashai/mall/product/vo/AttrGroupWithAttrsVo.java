package icu.ashai.mall.product.vo;

import icu.ashai.mall.product.entity.AttrEntity;
import lombok.Data;

import java.util.List;

/**
 * @author Ashai
 * @date 2/23/2022 4:44 PM
 * @Description 属性分组以及属性分组下的属性
 */
@Data
public class AttrGroupWithAttrsVo {
    /**
     * 分组id
     */
    private Long attrGroupId;
    /**
     * 组名
     */
    private String attrGroupName;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 描述
     */
    private String descript;
    /**
     * 组图标
     */
    private String icon;
    /**
     * 所属分类id
     */
    private Long catelogId;

    /**
     * 属性分组下的所有属性
     */
    private List<AttrEntity> attrs;

}
