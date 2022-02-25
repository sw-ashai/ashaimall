package icu.ashai.mall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.ashai.common.utils.PageUtils;
import icu.ashai.common.utils.Query;
import icu.ashai.mall.ware.dao.WareInfoDao;
import icu.ashai.mall.ware.entity.WareInfoEntity;
import icu.ashai.mall.ware.service.WareInfoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Description 仓库service
 * @Author Ashai
 * @email ashai.cn@gmail.com
 * @Date 10:40 PM 2/24/2022
 */
@Service("wareInfoService")
public class WareInfoServiceImpl extends ServiceImpl<WareInfoDao, WareInfoEntity> implements WareInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        String key = (String) params.get("key");
        IPage<WareInfoEntity> page = this.page(
                new Query<WareInfoEntity>().getPage(params),
                new LambdaQueryWrapper<WareInfoEntity>()
                        .and(StringUtils.isNotBlank(key), wrapper -> wrapper.eq(WareInfoEntity::getId, key).or()
                                .like(WareInfoEntity::getName, key).or()
                                .like(WareInfoEntity::getAddress, key).or()
                                .like(WareInfoEntity::getAreacode, key)
                        )
        );

        return new PageUtils(page);
    }

}