package icu.ashai.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.ashai.common.utils.PageUtils;
import icu.ashai.common.utils.Query;
import icu.ashai.mall.product.dao.CategoryDao;
import icu.ashai.mall.product.entity.CategoryEntity;
import icu.ashai.mall.product.service.CategoryBrandRelationService;
import icu.ashai.mall.product.service.CategoryService;
import icu.ashai.mall.product.vo.Catelog2Vo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description 分类管理实现类
 * @Author Ashai
 * @email ashai.cn@gmail.com
 * @Date 下午 05:50 2022/1/3
 */
@Service("categoryService")
@Slf4j
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    private final CategoryBrandRelationService categoryBrandRelationService;

    @Autowired
    public CategoryServiceImpl(CategoryBrandRelationService categoryBrandRelationService) {
        this.categoryBrandRelationService = categoryBrandRelationService;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        List<CategoryEntity> categoryEntities = baseMapper.selectList(null);

        return categoryEntities.stream()
                .filter(categoryEntity -> categoryEntity.getParentCid() == 0)
                .peek(categoryEntity -> categoryEntity.setChildren(getChildren(categoryEntity, categoryEntities)))
                .sorted(Comparator.comparingInt(menu1 -> (menu1.getSort() == null ? 0 : menu1.getSort())))
                .collect(Collectors.toList());
    }

    @Override
    public void removeMenuByIds(List<Long> catIds) {
        baseMapper.deleteBatchIds(catIds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDetail(CategoryEntity category) {
        this.updateById(category);
        if (StringUtils.isNotEmpty(category.getName())) {
            categoryBrandRelationService.updateCategory(category.getCatId(), category.getName());
        }
    }

    /**
     * 获取完整分类路径
     *
     * @param catelogId 分类id
     * @return 分类路径
     */
    @Override
    public Long[] findCatelogPath(Long catelogId) {
        ArrayList<Long> path = new ArrayList<>();
        findParentPath(catelogId, path);
        Collections.reverse(path);
        return path.toArray(new Long[0]);
    }

    /**
     * 获取所有一级分类
     *
     * @return list
     */
    @Override
    public List<CategoryEntity> getLevel1Categorys() {
        return this.list(new LambdaQueryWrapper<CategoryEntity>().eq(CategoryEntity::getParentCid, 0));
    }

    /**
     * 获取各级分类
     *
     * @return map
     */
    @Override
    public Map<String, List<Catelog2Vo>> getCatalogJson() {
        long l = System.currentTimeMillis();

//      循环查库 getCatalogJson() 运行时间1890ms
//        List<CategoryEntity> level1Categorys = getLevel1Categorys();
//        Map<String, List<Catelog2Vo>> collect = level1Categorys.stream().collect(Collectors.toMap(k -> k.getCatId().toString(), v -> {
//            List<CategoryEntity> list = this.list(new LambdaQueryWrapper<CategoryEntity>().eq(CategoryEntity::getParentCid, v.getCatId()));
//            return list.stream().map(item -> {
//                Catelog2Vo catelog2Vo = new Catelog2Vo();
//                catelog2Vo.setCatalog1Id(item.getParentCid().toString());
//                catelog2Vo.setId(item.getCatId().toString());
//                catelog2Vo.setName(item.getName());
//                List<CategoryEntity> catalog3 = this.list(new LambdaQueryWrapper<CategoryEntity>().eq(CategoryEntity::getParentCid, item.getCatId()));
//                List<Catelog2Vo.Catelog3Vo> catelog3Vos = catalog3.stream().map(c -> {
//                    Catelog2Vo.Catelog3Vo catelog3Vo = new Catelog2Vo.Catelog3Vo();
//                    catelog3Vo.setCatalog2Id(c.getParentCid().toString());
//                    catelog3Vo.setName(c.getName());
//                    catelog3Vo.setId(c.getCatId().toString());
//                    return catelog3Vo;
//                }).collect(Collectors.toList());
//                catelog2Vo.setCatalog3List(catelog3Vos);
//                return catelog2Vo;
//            }).collect(Collectors.toList());
//        }));


//        getCatalogJson() 运行时间105ms
        List<CategoryEntity> allCategorys = list();
        List<CategoryEntity> category1List = new ArrayList<>();
        Iterator<CategoryEntity> iterator = allCategorys.iterator();
        while (iterator.hasNext()) {
            CategoryEntity next = iterator.next();
            if (next.getParentCid().equals(0L)) {
                category1List.add(next);
                iterator.remove();
            }
        }

        Map<String, List<Catelog2Vo>> collect = category1List.stream().collect(Collectors.toMap(k -> k.getCatId().toString(), v -> {
            Iterator<CategoryEntity> iterator1 = allCategorys.iterator();
            List<Catelog2Vo> catelog2Vos = new ArrayList<>();
            while (iterator1.hasNext()) {
                CategoryEntity next = iterator1.next();
                if (next.getParentCid().equals(v.getCatId())) {
                    catelog2Vos.add(new Catelog2Vo(v.getCatId().toString(), null, next.getCatId().toString(), next.getName()));
                    iterator1.remove();
                }
            }
            return catelog2Vos;
        }));
        for (List<Catelog2Vo> value : collect.values()) {
            for (Catelog2Vo catelog2Vo : value) {
                Iterator<CategoryEntity> iterator1 = allCategorys.iterator();
                ArrayList<Catelog2Vo.Catelog3Vo> catelog3Vos = new ArrayList<>();
                while (iterator1.hasNext()) {
                    CategoryEntity category = iterator1.next();
                    if (category.getParentCid().equals(Long.valueOf(catelog2Vo.getId()))) {
                        catelog3Vos.add(new Catelog2Vo.Catelog3Vo(catelog2Vo.getId(), category.getCatId().toString(), category.getName()));
                        iterator1.remove();
                    }
                }
                catelog2Vo.setCatalog3List(catelog3Vos);
            }
        }

        log.debug("getCatalogJson() 运行时间{}ms", System.currentTimeMillis() - l);

        return collect;
    }

    /**
     * 寻找父路径并添加
     *
     * @param catelogId 分类id
     * @param path      完整路径集合
     */
    private void findParentPath(Long catelogId, ArrayList<Long> path) {
        path.add(catelogId);
        CategoryEntity byId = this.getById(catelogId);
        if (byId != null) {
            if (byId.getParentCid() != 0) {
                findParentPath(byId.getParentCid(), path);
            }
        }
    }


    /**
     * 获取子菜单
     *
     * @param menu    需要获取子菜单的对象
     * @param allMenu 整个菜单列表
     * @return 获取到的子菜单
     */
    private List<CategoryEntity> getChildren(CategoryEntity menu, List<CategoryEntity> allMenu) {

        return allMenu.stream()
                .filter(categoryEntity -> categoryEntity.getParentCid().equals(menu.getCatId()))
                .peek(categoryEntity -> categoryEntity.setChildren(getChildren(categoryEntity, allMenu)))
                .sorted(Comparator.comparingInt(menu1 -> (menu1.getSort() == null ? 0 : menu1.getSort())))
                .collect(Collectors.toList());
    }

}