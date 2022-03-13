package icu.ashai.mall.product.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Ashai
 * @date 2022/3/13 21:38
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Catelog2Vo {
    /**
     * 1级分类id
     */
    private String catalog1Id;
    /**
     * 三级分类列表
     */
    private List<Catelog3Vo> catalog3List;
    /**
     * id
     */
    private String id;
    /**
     * 名称
     */
    private String name;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Catelog3Vo {
        /**
         * 二级分类id
         */
        private String catalog2Id;
        /**
         * id
         */
        private String id;
        /**
         * 名称
         */
        private String name;

    }
}
