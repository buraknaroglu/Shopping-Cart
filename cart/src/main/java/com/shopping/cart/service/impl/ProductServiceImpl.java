package com.shopping.cart.service.impl;

import com.shopping.cart.model.Product;
import com.shopping.cart.service.BaseService;
import com.shopping.cart.service.ICategoryService;
import com.shopping.cart.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl extends BaseService implements IProductService {

    @Autowired
    private ICategoryService iCategoryService;

    public List<Product> products = new ArrayList<>();

    @PostConstruct
    void init() {
        products.add(new Product("Apple", 50.5, iCategoryService.findByCategoryName("Telefon")));
        products.add(new Product("Telefon Kılıfı", 5.5, iCategoryService.findByCategoryName("Aksesuar")));
    }
}
