package com.shopping.cart.model;

import com.shopping.cart.enums.DiscountType;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Burak Naroglu
 */

@Data
@AllArgsConstructor
public class Coupon {

    private int minPurchase;

    private int percent;

    private DiscountType discountType;
}
