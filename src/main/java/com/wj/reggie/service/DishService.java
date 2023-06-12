package com.wj.reggie.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wj.reggie.common.R;
import com.wj.reggie.dto.DishDto;
import com.wj.reggie.entity.Dish;

import java.util.List;

public interface DishService extends IService<Dish> {
    public void saveWithFlavor(DishDto dishDto);

    R<Page> page(int page, int pageSize, String name);

    public DishDto getByIdWithFlavor(Long id);

    public void updateWithFlavor(DishDto dishDto);

    public void sale(int status, String[] ids);

    public void delete(String[] ids);

    public R<List<Dish>> list(Dish dish);
}
