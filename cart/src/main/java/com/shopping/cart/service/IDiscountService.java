package com.shopping.cart.service;

import com.shopping.cart.model.ShoppingCart;

public interface IDiscountService {

    public ShoppingCart applyDiscountToCartLineItem(ShoppingCart shoppingCart);
}
