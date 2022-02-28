package icu.ashai.common.constant;

/**
 * @author Ashai
 * @date 2/22/2022 8:18 PM
 * @Description 产品相关常量
 */
public class ProductConstant {
    /**
     * 属性枚举类
     */
    public enum AttrEnum {
        /**
         * 属性类型
         */
        ATTR_TYPE_BASE(1, "基本属性"),
        ATTR_TYPE_SALE(0, "销售属性");

        private final int code;
        private final String msg;

        AttrEnum(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }

    /**
     * 属性枚举类
     */
    public enum StatusEnum {
        /**
         * spu 状态
         */
        NEW_SPU(0, "新建"),
        SPU_UP(1, "上架"),
        SPU_DOWN(2, "下架");

        private final int code;
        private final String msg;

        StatusEnum(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }
}
