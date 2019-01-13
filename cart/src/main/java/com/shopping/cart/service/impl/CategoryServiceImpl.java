package com.shopping.cart.service.impl;

import com.shopping.cart.model.Category;
import com.shopping.cart.model.Product;
import com.shopping.cart.service.BaseService;
import com.shopping.cart.service.ICategoryService;
import com.shopping.cart.service.IDiscountService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CategoryServiceImpl extends BaseService implements ICategoryService {

    public List<Category> categories = new ArrayList<>();

    @PostConstruct
    void init() {
        categories.add(new Category("Telefon"));
        categories.add(new Category("Aksesuar"));
    }

    @Override
    public Category findByCategoryName(String categoryName) {
        return categories.stream().filter(category -> category.getTitle().equals(categoryName)).findFirst().orElse(null);
    }
}
