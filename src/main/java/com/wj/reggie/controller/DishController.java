package com.wj.reggie.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wj.reggie.common.R;
import com.wj.reggie.dto.DishDto;
import com.wj.reggie.entity.Dish;
import com.wj.reggie.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dish")
public class DishController {

    @Autowired
    DishService dishService;

    @PostMapping
    public R<String> save(@RequestBody DishDto dishDto) {
        dishService.saveWithFlavor(dishDto);
        return R.success("新增菜品成功");
    }

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        return dishService.page(page, pageSize, name);
    }

    @GetMapping("/{id}")
    public R<DishDto> getById(@PathVariable Long id) {
        DishDto dishDto = dishService.getByIdWithFlavor(id);
        return R.success(dishDto);
    }

    @PutMapping
    public R<String> update(@RequestBody DishDto dishDto) {
        dishService.updateWithFlavor(dishDto);
        return R.success("修改菜品成功");
    }

    @PostMapping("/status/{status}")
    public R<String>sale(@PathVariable int status, String [] ids){
        dishService.sale(status, ids);
        return R.success("修改成功");
    }

    @DeleteMapping
    public R<String>delete(String [] ids){
        dishService.delete(ids);
        return R.success("删除成功");
    }

    @GetMapping("/list")
    public R<List<Dish>>list(Dish dish){
        return dishService.list(dish);
    }
}
