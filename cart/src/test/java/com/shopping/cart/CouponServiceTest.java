package com.shopping.cart;

import com.shopping.cart.model.ShoppingCart;
import com.shopping.cart.service.impl.CouponServiceImpl;
import data.TestData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

/**
 * @author Burak Naroglu
 */

@RunWith(SpringRunner.class)
public class CouponServiceTest {

    @Spy
    @InjectMocks
    private CouponServiceImpl couponService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        couponService.coupons = TestData.getCoupons();
    }

    @Test
    public void applyCouponToCartWithInvalidAmount() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(TestData.createProductByTelephoneCategory(), 2);
        ShoppingCart response = couponService.applyCouponToCart(shoppingCart);
        Assert.assertTrue(response.getCouponDiscount() == 0 ? Boolean.TRUE : Boolean.FALSE);
    }

    @Test
    public void applyCouponToCartWithEmptyCoupon() {
        couponService.coupons = new ArrayList<>();
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(TestData.createProductByTelephoneCategory(), 2);
        ShoppingCart response = couponService.applyCouponToCart(shoppingCart);
        Assert.assertTrue(response.getCouponDiscount() == 0 ? Boolean.TRUE : Boolean.FALSE);
    }

    @Test
    public void applyCouponToCartWithValidAmount() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(TestData.createProductByTelephoneCategory(), 3);
        ShoppingCart response = couponService.applyCouponToCart(shoppingCart);
        Assert.assertTrue(response.getCouponDiscount() == 0 ? Boolean.FALSE : Boolean.TRUE);
    }

    @Test
    public void applyCouponToCartWithCampaing() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(TestData.createProductByTelephoneCategory(), 3);
        shoppingCart.getCartLineItem().get(0).setCampaignDiscount(123);
        ShoppingCart response = couponService.applyCouponToCart(shoppingCart);
        Assert.assertTrue(response.getCouponDiscount() == 0 ? Boolean.FALSE : Boolean.TRUE);
    }

    @Test(expected = NullPointerException.class)
    public void applyCouponToCartPassNullParameter() {
        couponService.applyCouponToCart(null);
    }

}

    