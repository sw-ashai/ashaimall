package icu.ashai.mall.order.dao;

import icu.ashai.mall.order.entity.PaymentInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 支付信息表
 * 
 * @author Ashai
 * @email ashai.cn@gmail.com
 * @date 2021-11-20 15:56:25
 */
@Mapper
public interface PaymentInfoDao extends BaseMapper<PaymentInfoEntity> {
	
}
