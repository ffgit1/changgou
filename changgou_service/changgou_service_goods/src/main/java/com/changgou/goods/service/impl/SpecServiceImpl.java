package com.changgou.goods.service.impl;

import com.changgou.goods.dao.SpecMapper;
import com.changgou.goods.service.SpecService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class SpecServiceImpl implements SpecService {
    @Autowired
    SpecMapper specMapper;
    @Override
    public List<Map> findListByCategoryName(String categoryName) {
        List<Map> specList = specMapper.findListByCategoryName(categoryName);
        for(Map spec: specList){
            String[] options = ((String) spec.get("options")).split(",");//规格选项列表
            spec.put("options",options);
        }
        return specList;
    }
}
