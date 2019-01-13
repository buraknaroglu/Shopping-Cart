package com.shopping.cart.service.impl;

import com.shopping.cart.model.ShoppingCart;
import com.shopping.cart.service.BaseService;
import com.shopping.cart.service.IDeliveryCostService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DeliveryCostServiceImpl extends BaseService implements IDeliveryCostService {

    @Value("${fixed.cost}")
    public double fixedCost;

    @Value("${cost.per.delivery}")
    public double costPerDelivery;

    @Value("${cost.per.product}")
    public double costPerProduct;

    @Override
    public double calculateDeliveryCost(ShoppingCart shoppingCart) {
        log.info("Delivery cost is calculating.");
        Set<String> categoryNameList = new HashSet<>();

        shoppingCart.getCartLineItem().forEach(cartLineItem -> {
            categoryNameList.add(cartLineItem.getProduct().getCategory().getTitle());
        });

        int numberOfDeliveries = categoryNameList.size();
        int numberOfProducts = shoppingCart.getCartLineItem().size();
        return calculate(numberOfDeliveries, numberOfProducts);
    }

    private double calculate(int numberOfDeliveries, int numberOfProducts) {
        return (costPerDelivery * numberOfDeliveries) + (costPerProduct * numberOfProducts) + fixedCost;
    }
}
