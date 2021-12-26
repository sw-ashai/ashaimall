package icu.ashai.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.ashai.common.utils.PageUtils;
import icu.ashai.common.utils.Query;
import icu.ashai.mall.product.dao.BrandDao;
import icu.ashai.mall.product.entity.BrandEntity;
import icu.ashai.mall.product.service.BrandService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        LambdaQueryWrapper<BrandEntity> wrapper = new LambdaQueryWrapper<>();

        if (StringUtils.isNotEmpty(key)) {
            wrapper.eq(BrandEntity::getBrandId,key).or().like(BrandEntity::getName,key);
        }

        IPage<BrandEntity> page = this.page(
                new Query<BrandEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

}