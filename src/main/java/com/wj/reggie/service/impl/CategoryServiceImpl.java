package com.wj.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wj.reggie.common.CustomException;
import com.wj.reggie.common.R;
import com.wj.reggie.entity.Category;
import com.wj.reggie.entity.Dish;
import com.wj.reggie.entity.Setmeal;
import com.wj.reggie.mapper.CategoryMapper;
import com.wj.reggie.service.CategoryService;
import com.wj.reggie.service.DishService;
import com.wj.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private DishService dishService;
    @Autowired
    private SetmealService setmealService;

    @Override
    public R<Page> page(int page, int size) {
        Page<Category>pageInfo=new Page<>(page, size);

        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Category::getSort);

        categoryService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }

    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId,id);
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        int count1 = dishService.count(dishLambdaQueryWrapper);
        int count2 = setmealService.count(setmealLambdaQueryWrapper);

        if (count1 > 0) {
            throw new CustomException("已经关联菜品，不能删除");
        }
        if (count2 > 0) {
            throw new CustomException("已经关联套餐，不能删除");
        }

        super.removeById(id);
    }

    @Override
    public R<List<Category>> list(Category category) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(category.getType()!=null,Category::getType,category.getType());
        queryWrapper.orderByAsc(Category::getSort).orderByAsc(Category::getUpdateTime);
        List<Category> list = categoryService.list(queryWrapper);
        return R.success(list);
    }
}
