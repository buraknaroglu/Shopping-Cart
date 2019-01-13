package com.shopping.cart.model;

import lombok.Data;

/**
 * @author Burak Naroglu
 */

@Data
public class CartLineItem {

    private Product product;

    private int productAmount;

    private double campaignDiscount;

    private double totalAmountBeforeDiscounts;

    private double totalAmountAfterDiscounts;

    public CartLineItem(Product product, int productAmount) {
        this.product = product;
        this.productAmount = productAmount;
    }

    public double getTotalAmountBeforeDiscounts() {
        return getProduct().getPrice() * getProductAmount();
    }
}
