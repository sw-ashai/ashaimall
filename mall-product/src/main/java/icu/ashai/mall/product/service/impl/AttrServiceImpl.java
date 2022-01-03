package icu.ashai.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.ashai.common.utils.PageUtils;
import icu.ashai.common.utils.Query;
import icu.ashai.mall.product.dao.AttrAttrgroupRelationDao;
import icu.ashai.mall.product.dao.AttrDao;
import icu.ashai.mall.product.entity.AttrAttrgroupRelationEntity;
import icu.ashai.mall.product.entity.AttrEntity;
import icu.ashai.mall.product.service.AttrService;
import icu.ashai.mall.product.vo.AttrVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


/**
 * @Description 属性service实现类
 * @Author Ashai
 * @email ashai.cn@gmail.com
 * @Date 下午 06:21 2022/1/3
 */
@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    private AttrAttrgroupRelationDao attrAttrgroupRelationDao;

    @Autowired
    public AttrServiceImpl(AttrAttrgroupRelationDao attrAttrgroupRelationDao) {
        this.attrAttrgroupRelationDao = attrAttrgroupRelationDao;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrEntity> page = this.page(new Query<AttrEntity>().getPage(params), new QueryWrapper<>());

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveAttrVo(AttrVo attr) {
//        保存属性
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attr, attrEntity);
        save(attrEntity);

//        保存属性和分组对应关系
        AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = new AttrAttrgroupRelationEntity();
        attrAttrgroupRelationEntity.setAttrId(attrEntity.getAttrId());
        attrAttrgroupRelationEntity.setAttrGroupId(attr.getAttrGroupId());

        attrAttrgroupRelationDao.insert(attrAttrgroupRelationEntity);


    }

}