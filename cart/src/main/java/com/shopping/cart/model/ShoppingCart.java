package com.shopping.cart.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Burak Naroglu
 */

@Data
public class ShoppingCart {

    private double totalAmountBeforeDiscounts;

    private double totalAmountAfterDiscounts;

    private double couponDiscount;

    private double campaignDiscount;

    private double deliveryCost;

    private double totalPrice;

    private List<CartLineItem> cartLineItem = new ArrayList<>();

    public void addItem(Product product, int productAmount) {
        cartLineItem.add(new CartLineItem(product, productAmount));
    }

    public double getTotalAmountBeforeDiscounts() {
        return cartLineItem.stream().mapToDouble(CartLineItem::getTotalAmountBeforeDiscounts).sum();
    }

    public double getTotalAmountAfterDiscounts() {
        double discountTotalPrice = cartLineItem.stream().mapToDouble(CartLineItem::getTotalAmountAfterDiscounts).sum();
        if (discountTotalPrice != 0) {
            return discountTotalPrice - getCouponDiscount();
        }
        return cartLineItem.stream().mapToDouble(CartLineItem::getTotalAmountBeforeDiscounts).sum() - getCouponDiscount();
    }

    public double getTotalPrice() {
        return getDeliveryCost() + (getTotalAmountAfterDiscounts() != 0.0 ? getTotalAmountAfterDiscounts() : getTotalAmountBeforeDiscounts());
    }

    public double getCampaignDiscount() {
        return cartLineItem.stream().mapToDouble(CartLineItem::getCampaignDiscount).sum();
    }
}
