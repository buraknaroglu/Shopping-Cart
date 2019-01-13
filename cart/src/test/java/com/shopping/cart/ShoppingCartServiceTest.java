package com.shopping.cart;

import com.shopping.cart.service.ICouponService;
import com.shopping.cart.service.IDeliveryCostService;
import com.shopping.cart.service.IDiscountService;
import com.shopping.cart.reponse.ServiceResult;
import com.shopping.cart.service.impl.ShoppingCartServiceImpl;
import data.TestData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Burak Naroglu
 */

@RunWith(SpringRunner.class)
public class ShoppingCartServiceTest {

    @Spy
    @InjectMocks
    private ShoppingCartServiceImpl shoppingCartService;

    @Mock
    private IDiscountService iDiscountService;

    @Mock
    private ICouponService iCouponService;

    @Mock
    private IDeliveryCostService iDeliveryCostService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createShoppingCartPassNullParameter() {
        ServiceResult response = shoppingCartService.createShoppingCart(null);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, ((ServiceResult) response).getHttpStatus());
    }

    @Test
    public void createShoppingCart() {
        ServiceResult response = shoppingCartService.createShoppingCart(TestData.createShoppingCartRequests());
        Assert.assertEquals(HttpStatus.OK, ((ServiceResult) response).getHttpStatus());
    }

}

    