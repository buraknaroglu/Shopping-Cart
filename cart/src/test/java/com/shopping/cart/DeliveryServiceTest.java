package com.shopping.cart;

import com.shopping.cart.model.ShoppingCart;
import com.shopping.cart.service.impl.DeliveryCostServiceImpl;
import data.TestData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Burak Naroglu
 */

@RunWith(SpringRunner.class)
public class DeliveryServiceTest {

    @Spy
    @InjectMocks
    private DeliveryCostServiceImpl deliveryCostService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        deliveryCostService.fixedCost = 2.99;
        deliveryCostService.costPerDelivery = 2;
        deliveryCostService.costPerProduct = 3;
    }

    @Test
    public void calculateDeliveryCost() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(TestData.createProductByTelephoneCategory(), 3);
        double response = deliveryCostService.calculateDeliveryCost(shoppingCart);
        Assert.assertTrue(response > 0 ? Boolean.TRUE : Boolean.FALSE);
    }

    @Test
    public void calculateDeliveryCostUnlessCartLineItem() {
        ShoppingCart shoppingCart = new ShoppingCart();
        double response = deliveryCostService.calculateDeliveryCost(shoppingCart);
        Assert.assertTrue(response > 0 ? Boolean.TRUE : Boolean.FALSE);
    }

    @Test(expected = NullPointerException.class)
    public void calculateDeliveryCostPassNullParameter() {
        deliveryCostService.calculateDeliveryCost(null);
    }

}

    