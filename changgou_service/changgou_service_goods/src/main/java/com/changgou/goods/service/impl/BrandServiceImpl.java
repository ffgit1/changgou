package com.changgou.goods.service.impl;

import com.changgou.goods.dao.BrandMapper;
import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;
@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandMapper brandMapper;
    @Override
    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }

    @Override
    public Brand findById(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(Brand brand) {
        brandMapper.insertSelective(brand);
    }

    @Override
    public void update(Brand brand) {
        brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public void delete(Integer id) {
        brandMapper.deleteByPrimaryKey(id);
    }

    /**
     * 条件查询
     * @param searchMap
     * @return
     */
    @Override
    public List<Brand> findList(Map<String, Object> searchMap) {
        //创建查询条件example
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap !=null){//如果传入的查询条件不为空
            //判断是否有品牌名称查询
            if(searchMap.get("name")!=null && !"".equals(searchMap.get("name"))){
                //加入条件：根据名称模糊查询
                criteria.andLike("name","%"+searchMap.get("name")+"%");
            }
            //判断是否有品牌的首字母查询
            if (searchMap.get("letter")!=null&&!"".equals(searchMap.get("letter"))){
                //把根据首字母查询加入条件中
                criteria.andEqualTo("letter",searchMap.get("letter"));
            }
        }
        return brandMapper.selectByExample(example);
    }

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<Brand> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        return (Page<Brand>) brandMapper.selectAll();
    }

    /**
     * 条件+分页查询
     * @param searchMap 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页条件查询结果
     */
    @Override
    public Page<Brand> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        //创建查询条件example
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap !=null){//如果传入的查询条件不为空
            //判断是否有品牌名称查询
            if(searchMap.get("name")!=null && !"".equals(searchMap.get("name"))){
                //加入条件：根据名称模糊查询
                criteria.andLike("name","%"+searchMap.get("name")+"%");
            }
            //判断是否有品牌的首字母查询
            if (searchMap.get("letter")!=null&&!"".equals(searchMap.get("letter"))){
                //把根据首字母查询加入条件中
                criteria.andEqualTo("letter",searchMap.get("letter"));
            }
        }
        return (Page<Brand>) brandMapper.selectByExample(example);
    }

}
