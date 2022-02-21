package icu.ashai.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.ashai.common.utils.PageUtils;
import icu.ashai.common.utils.Query;
import icu.ashai.mall.product.dao.CategoryDao;
import icu.ashai.mall.product.entity.CategoryEntity;
import icu.ashai.mall.product.service.CategoryBrandRelationService;
import icu.ashai.mall.product.service.CategoryService;
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