package com.shopping.cart.service;

import com.shopping.cart.model.Category;

public interface ICategoryService {

    public Category findByCategoryName(String categoryName);

}
