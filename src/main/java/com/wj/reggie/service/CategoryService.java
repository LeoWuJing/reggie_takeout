package com.wj.reggie.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wj.reggie.common.R;
import com.wj.reggie.entity.Category;

import java.util.List;

public interface CategoryService extends IService<Category> {
    R<Page> page(int page, int size);

    public void remove(Long id);

    public R<List<Category>> list(Category category);
}
