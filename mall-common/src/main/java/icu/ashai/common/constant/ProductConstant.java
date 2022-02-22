package icu.ashai.common.constant;

/**
 * @author Ashai
 * @date 2/22/2022 8:18 PM
 * @Description ��Ʒ��س���
 */
public class ProductConstant {
    /**
     * ����ö����
     */
    public enum AttrEnum {
        /**
         * ��������
         */
        ATTR_TYPE_BASE(1, "��������"),
        /**
         * ��������
         */
        ATTR_TYPE_SALE(0, "��������");

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
}
