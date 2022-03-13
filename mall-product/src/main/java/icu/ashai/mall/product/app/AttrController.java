package icu.ashai.mall.product.app;

import icu.ashai.common.utils.PageUtils;
import icu.ashai.common.utils.R;
import icu.ashai.mall.product.entity.ProductAttrValueEntity;
import icu.ashai.mall.product.service.AttrService;
import icu.ashai.mall.product.service.ProductAttrValueService;
import icu.ashai.mall.product.vo.AttrRespVo;
import icu.ashai.mall.product.vo.AttrVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 商品属性
 *
 * @author Ashai
 * @email ashai.cn@gmail.com
 * @date 2021-11-19 01:15:32
 */
@RestController
@RequestMapping("product/attr")
public class AttrController {

    private final AttrService attrService;

    private final ProductAttrValueService productAttrValueService;

    @Autowired
    public AttrController(AttrService attrService, ProductAttrValueService productAttrValueService) {
        this.attrService = attrService;
        this.productAttrValueService = productAttrValueService;
    }


    /**
     * 获取规格参数列表
     */
    @GetMapping("/{attrType}/list/{catelogId}")
    public R baseAttrList(@RequestParam Map<String, Object> params, @PathVariable Long catelogId, @PathVariable String attrType) {
        PageUtils page = attrService.queryBaseAttrPage(params, catelogId, attrType);

        return R.ok().put("page", page);
    }

    /**
     * 根据spu id 查询spu规格
     *
     * @param spuId spu id
     * @return result
     */
    @GetMapping("/base/listforspu/{spuId}")
    public R baseAttrListForSpu(@PathVariable Long spuId) {
        List<ProductAttrValueEntity> list = productAttrValueService.baseAttrListForSpu(spuId);
        return R.ok().put("data", list);
    }

    /**
     * 修改
     */
    @PostMapping("/update/{spuId}")
    public R updateSpuAttr(@RequestBody List<ProductAttrValueEntity> entities, @PathVariable Long spuId) {
        productAttrValueService.updateSpuAttr(spuId, entities);

        return R.ok();
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = attrService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrId}")
    public R info(@PathVariable("attrId") Long attrId) {
        AttrRespVo respVo = attrService.getAttrInfo(attrId);
        return R.ok().put("attr", respVo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody AttrVo attr) {
        attrService.saveAttrVo(attr);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody AttrVo attr) {
        attrService.updateAttr(attr);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] attrIds) {
        attrService.removeByIds(Arrays.asList(attrIds));

        return R.ok();
    }

}
