package icu.ashai.mall.product.vo;

import lombok.Data;

/**
 * @Description
 * @Author Ashai
 * @email ashai.cn@gmail.com
 * @Date 5:25 PM 2/23/2022
 */
@Data
public class Images {

    /**
     * 图片url
     */
    private String imgUrl;
    /**
     * 是否默认图片
     */
    private int defaultImg;

}
