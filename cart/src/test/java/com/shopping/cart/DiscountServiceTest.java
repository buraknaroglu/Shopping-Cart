package com.shopping.cart;

import com.shopping.cart.enums.DiscountType;
import com.shopping.cart.model.AppliedDiscount;
import com.shopping.cart.model.Campaign;
import com.shopping.cart.model.ShoppingCart;
import com.shopping.cart.service.impl.DiscountServiceImpl;
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
import java.util.List;

/**
 * @author Burak Naroglu
 */

@RunWith(SpringRunner.class)
public class DiscountServiceTest {

    @Spy
    @InjectMocks
    private DiscountServiceImpl discountService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        discountService.campaigns = TestData.getCampaigns();
    }

    @Test
    public void applyDiscountToCartLineItemUnlessCartLineItem() {
        ShoppingCart shoppingCart = new ShoppingCart();
        ShoppingCart response = discountService.applyDiscountToCartLineItem(shoppingCart);
        Assert.assertTrue(response.getCampaignDiscount() == 0 ? Boolean.TRUE : Boolean.FALSE);
    }

    @Test
    public void applyDiscountToCartLineItemInadequateProductAmount() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(TestData.createProductByTelephoneCategory(), 2);
        ShoppingCart response = discountService.applyDiscountToCartLineItem(shoppingCart);
        Assert.assertTrue(response.getCampaignDiscount() == 0 ? Boolean.TRUE : Boolean.FALSE);
    }

    @Test
    public void applyDiscountToCartLineItemWithDiscountTypeRate() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(TestData.createProductByTelephoneCategory(), 4);
        ShoppingCart response = discountService.applyDiscountToCartLineItem(shoppingCart);
        Assert.assertTrue(response.getCampaignDiscount() > 0 ? Boolean.TRUE : Boolean.FALSE);
    }

    @Test
    public void applyDiscountToCartLineItemWithDiscountTypeAmount() {
        List<Campaign> campaigns = new ArrayList<>();
        campaigns.add(new Campaign(TestData.getCategories().get(1), 5.0, 5, DiscountType.AMOUNT));
        discountService.campaigns = campaigns;
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(TestData.createProductByAccessoryCategory(), 6);
        ShoppingCart response = discountService.applyDiscountToCartLineItem(shoppingCart);
        Assert.assertTrue(response.getCampaignDiscount() > 0 ? Boolean.TRUE : Boolean.FALSE);
    }

    @Test
    public void applyDiscountToCartLineItemWithDiscountTypeRateAndAmount() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(TestData.createProductByAccessoryCategory(), 6);
        ShoppingCart response = discountService.applyDiscountToCartLineItem(shoppingCart);
        Assert.assertTrue(response.getCampaignDiscount() > 0 ? Boolean.TRUE : Boolean.FALSE);
    }

    @Test(expected = NullPointerException.class)
    public void applyDiscountToCartLineItemPassNullParameter() {
        discountService.applyDiscountToCartLineItem(null);
    }

    @Test
    public void applyDiscountWithRate() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(TestData.createProductByAccessoryCategory(), 6);
        AppliedDiscount response = discountService.applyDiscount(shoppingCart.getCartLineItem().get(0), TestData.getCampaigns().get(0), DiscountType.RATE);
        Assert.assertTrue(response.getDiscountPrice() == 54 ? Boolean.TRUE : Boolean.FALSE);
    }

    @Test
    public void applyDiscountWithAmount() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(TestData.createProductByAccessoryCategory(), 6);
        AppliedDiscount response = discountService.applyDiscount(shoppingCart.getCartLineItem().get(0), TestData.getCampaigns().get(0), DiscountType.AMOUNT);
        Assert.assertTrue(response.getDiscountPrice() == 20 ? Boolean.TRUE : Boolean.FALSE);
    }

    @Test(expected = NullPointerException.class)
    public void applyDiscountToCartLineItemPassNullCartLineItem() {
        discountService.applyDiscount(null, TestData.getCampaigns().get(0), DiscountType.RATE);
    }

    @Test(expected = NullPointerException.class)
    public void applyDiscountToCartLineItemPassNullCampaing() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(TestData.createProductByAccessoryCategory(), 6);
        discountService.applyDiscount(shoppingCart.getCartLineItem().get(0), null, DiscountType.RATE);
    }

    @Test(expected = NullPointerException.class)
    public void getBestDiscountAmountPassNullParameter() {
        discountService.getBestDiscountAmount(null);
    }

    @Test
    public void getBestDiscountAmount() {
        AppliedDiscount response = discountService.getBestDiscountAmount(TestData.appliedDiscounts());
        Assert.assertTrue(response.getDiscountPrice() == 13 ? Boolean.TRUE : Boolean.FALSE);
    }

    @Test
    public void getBestDiscountAmountPassEmptyParameter() {
        AppliedDiscount response = discountService.getBestDiscountAmount(new ArrayList<>());
        Assert.assertNull(response);
    }

}

    