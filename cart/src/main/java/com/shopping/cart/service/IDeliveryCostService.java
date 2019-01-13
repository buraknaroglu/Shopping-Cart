package com.shopping.cart.service;

import com.shopping.cart.model.ShoppingCart;

public interface IDeliveryCostService {

    public double calculateDeliveryCost(ShoppingCart shoppingCart);
}
