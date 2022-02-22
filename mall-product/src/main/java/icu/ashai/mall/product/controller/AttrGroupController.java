package icu.ashai.mall.product.controller;

import icu.ashai.common.utils.PageUtils;
import icu.ashai.common.utils.R;
import icu.ashai.mall.product.entity.AttrEntity;
import icu.ashai.mall.product.entity.AttrGroupEntity;
import icu.ashai.mall.product.service.AttrAttrgroupRelationService;
import icu.ashai.mall.product.service.AttrGroupService;
import icu.ashai.mall.product.service.AttrService;
import icu.ashai.mall.product.vo.AttrGroupRelationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 属性分组
 *
 * @author Ashai
 * @email ashai.cn@gmail.com
 * @date 2021-11-19 01:15:33
 */
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {

    /**
     * 属性分组service
     */
    private final AttrGroupService attrGroupService;

    /**
     * 属性service
     */
    private final AttrService attrService;

    /**
     * 关联关系service
     */
    private final AttrAttrgroupRelationService relationService;


    @Autowired
    public AttrGroupController(AttrGroupService attrGroupService, AttrService attrService, AttrAttrgroupRelationService relationService) {
        this.attrGroupService = attrGroupService;
        this.attrService = attrService;
        this.relationService = relationService;
    }

    /**
     * 获取该属性分组下的所有属性
     *
     * @param attrgroupId 属性分组id
     * @return list
     */
    @GetMapping("/{attrgroupId}/attr/relation")
    public R attrRelation(@PathVariable Long attrgroupId) {
        List<AttrEntity> entityList = attrService.getRelationAttr(attrgroupId);

        return R.ok().put("data", entityList);
    }

    /**
     * 获取该属性分组可以绑定的所有属性
     *
     * @param attrgroupId 属性分组id
     * @param params      分页参数以及模糊查询条件
     * @return list
     */
    @GetMapping("/{attrgroupId}/noattr/relation")
    public R attrNoRelation(@PathVariable Long attrgroupId, @RequestParam Map<String, Object> params) {
        PageUtils page = attrService.getNoRelation(params, attrgroupId);
        return R.ok().put("page", page);
    }

    /**
     * 删除关联关系
     *
     * @param attrGroupRelationVoList 关联关系列表
     * @return success
     */
    @PostMapping("/attr/relation/delete")
    public R deleteRelation(@RequestBody List<AttrGroupRelationVo> attrGroupRelationVoList) {
        attrService.deleteRelation(attrGroupRelationVoList);
        return R.ok();
    }

    @PostMapping("/attr/relation")
    public R addRelation(@RequestBody List<AttrGroupRelationVo> attrGroupRelationVoList) {
        relationService.saveBatch(attrGroupRelationVoList);

        return R.ok();
    }

    /**
     * 列表
     */
    @RequestMapping("/list/{catelogId}")
    public R list(@RequestParam Map<String, Object> params, @PathVariable Long catelogId) {
        PageUtils page = attrGroupService.queryPage(params, catelogId);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrGroupId}")
    public R info(@PathVariable("attrGroupId") Long attrGroupId) {
        AttrGroupEntity attrGroup = attrGroupService.getInfo(attrGroupId);

        return R.ok().put("attrGroup", attrGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody AttrGroupEntity attrGroup) {
        attrGroupService.save(attrGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody AttrGroupEntity attrGroup) {
        attrGroupService.updateById(attrGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] attrGroupIds) {
        attrGroupService.removeByIds(Arrays.asList(attrGroupIds));

        return R.ok();
    }

}
