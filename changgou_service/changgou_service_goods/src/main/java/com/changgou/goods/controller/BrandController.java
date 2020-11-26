package com.changgou.goods.controller;

import com.changgou.entity.PageResult;
import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 查询全部品牌数据
     *
     * @return
     */
    @GetMapping("/findAll")
    public Result findAll() {
        List<Brand> list = brandService.findAll();
        return new Result(true, StatusCode.OK, "查询成功", list);
    }

    /**
     * 根据id查询单个品牌数据
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable("id") Integer id) {
        Brand brand = brandService.findById(id);
        return new Result(true, StatusCode.OK, "查询成功", brand);
    }

    /**
     * 新增品牌数据
     *
     * @param brand
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody Brand brand) {
        brandService.add(brand);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    /**
     * 修改品牌数据
     *
     * @param brand
     * @param id
     * @return
     */
    @PutMapping(value = "/update/{id}")
    public Result update(@RequestBody Brand brand, @PathVariable Integer id) {
        brand.setId(id);
        brandService.update(brand);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 根据id删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        brandService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }
    @GetMapping("/search")
    public Result findList(@RequestParam Map searcheMap){
        List list = brandService.findList(searcheMap);
        return new Result(true,StatusCode.OK,"查询成功",list);
    }
    @GetMapping("/search/{page}/{size}")
    public Result findPage(@PathVariable int page,@PathVariable int size){
        Page<Brand> page1 = brandService.findPage(page, size);
        PageResult pageResult = new PageResult(page1.getTotal(), page1.getResult());
        return new Result(true,StatusCode.OK,"查询成功",pageResult);
    }
    @GetMapping(value = "/searchByMap/{page}/{size}")
    public Result findPage(@RequestParam Map searchMap,@PathVariable int page,@PathVariable int size){
        Page<Brand> page1 = brandService.findPage(searchMap,page, size);
        PageResult pageResult = new PageResult(page1.getTotal(), page1.getResult());
        return new Result(true,StatusCode.OK,"查询成功",pageResult);
    }
}
