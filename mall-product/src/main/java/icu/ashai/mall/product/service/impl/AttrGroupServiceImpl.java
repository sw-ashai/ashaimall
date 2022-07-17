package icu.ashai.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.ashai.common.utils.PageUtils;
import icu.ashai.common.utils.Query;
import icu.ashai.mall.product.dao.AttrGroupDao;
import icu.ashai.mall.product.entity.AttrEntity;
import icu.ashai.mall.product.entity.AttrGroupEntity;
import icu.ashai.mall.product.entity.CategoryEntity;
import icu.ashai.mall.product.service.AttrGroupService;
import icu.ashai.mall.product.service.AttrService;
import icu.ashai.mall.product.service.CategoryService;
import icu.ashai.mall.product.vo.AttrGroupWithAttrsVo;
import icu.ashai.mall.product.vo.SkuItemVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    /**
     * 三级分类service
     */
    private final CategoryService categoryService;

    /**
     * 属性service
     */
    private final AttrService attrService;

    @Autowired
    public AttrGroupServiceImpl(CategoryService categoryService, AttrService attrService) {
        this.categoryService = categoryService;
        this.attrService = attrService;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(new Query<AttrGroupEntity>().getPage(params), new QueryWrapper<>());

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long catelogId) {

        LambdaQueryWrapper<AttrGroupEntity> wrapper;

        if (catelogId.equals(0L)) {
//            查询全部
            wrapper = new LambdaQueryWrapper<>();
        } else {
//            查询指定id数据
            wrapper = new LambdaQueryWrapper<AttrGroupEntity>().eq(AttrGroupEntity::getCatelogId, catelogId);
        }

        String key = (String) params.get("key");
//        添加搜索条件
        if (StringUtils.isNotEmpty(key)) {
            wrapper.and(attrGroupEntityLambdaQueryWrapper -> {
                attrGroupEntityLambdaQueryWrapper.eq(AttrGroupEntity::getAttrGroupId, key).or().like(AttrGroupEntity::getAttrGroupName, key);
            });
        }

        IPage<AttrGroupEntity> page = this.page(new Query<AttrGroupEntity>().getPage(params), wrapper);
        return new PageUtils(page);
    }

    @Override
    public AttrGroupEntity getInfo(Long attrGroupId) {
        AttrGroupEntity attrGroup = getById(attrGroupId);
        CategoryEntity category = categoryService.getById(attrGroup.getCatelogId());

        Long[] path = categoryService.findCatelogPath(category.getCatId());

        attrGroup.setCatelogPath(path);

        return attrGroup;
    }

    @Override
    public List<AttrGroupWithAttrsVo> getAttrGroupWithAttrsByCatelogId(Long catelogId) {
        List<AttrGroupEntity> attrGroupList = this.list(new LambdaQueryWrapper<AttrGroupEntity>().eq(AttrGroupEntity::getCatelogId, catelogId));

        return attrGroupList.stream().map(item -> {
            AttrGroupWithAttrsVo attrGroupWithAttrsVo = new AttrGroupWithAttrsVo();
            BeanUtils.copyProperties(item, attrGroupWithAttrsVo);
            List<AttrEntity> attrEntities = attrService.getRelationAttr(item.getAttrGroupId());
            attrGroupWithAttrsVo.setAttrs(attrEntities);
            return attrGroupWithAttrsVo;
        }).collect(Collectors.toList());
    }

    /**
     * 获取分类路径  自己写的，用分类service下的findCatelogPath方法
     *
     * @param category 分类对象
     * @param paths    路径集合
     * @return 查询结果
     */
    @Deprecated
    private List<Long> getCategoryPath(CategoryEntity category, List<Long> paths) {

        paths.add(category.getCatId());

        if (category.getParentCid().equals(0L)) {
            return paths;
        }

        return getCategoryPath(categoryService.getById(category.getParentCid()), paths);
    }


    @Override
    public List<SkuItemVo.SpuItemAttrGroupVo> getAttrGroupWithAttrsBySpuId(Long spuId, Long catalogId) {
        return baseMapper.getAttrsGroupWithAttrsBySpuId(spuId,catalogId);
    }


}