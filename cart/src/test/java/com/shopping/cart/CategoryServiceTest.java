package com.shopping.cart;

import com.shopping.cart.model.Category;
import com.shopping.cart.service.impl.CategoryServiceImpl;
import data.TestData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Burak Naroglu
 */

@RunWith(SpringRunner.class)
public class CategoryServiceTest {

    @Spy
    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getCategory() {
        categoryService.categories = TestData.getCategories();
        Category response = categoryService.findByCategoryName("Telefon");
        Assert.assertEquals("Telefon", response.getTitle());
    }

    @Test
    public void getCategoryNull() {
        Category response = categoryService.findByCategoryName("Akilli Telefon");
        Assert.assertNull(response);
    }

}

    