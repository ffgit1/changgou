package com.changgou.goods.controller;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.goods.service.SpecService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/spec")
public class SpecController {
    @Autowired
    SpecService specService;
    @GetMapping("/category/{category}")
    public Result findListByCategoryName(@PathVariable String category){
        List<Map> specList = specService.findListByCategoryName(category);
        return new Result(true, StatusCode.OK,"查询成功",specList);
    }
}
