package icu.ashai.mall.product;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import icu.ashai.mall.product.entity.BrandEntity;
import icu.ashai.mall.product.service.BrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MallProductApplicationTests {

    @Autowired
    private BrandService brandService;

    @Test
    void contextLoads() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setName("小米");
        brandEntity.setBrandId(1L);

        List<BrandEntity> brand_id = brandService.list(new QueryWrapper<BrandEntity>().eq("brand_id", 1));
        System.out.println(brand_id);
    }

}
