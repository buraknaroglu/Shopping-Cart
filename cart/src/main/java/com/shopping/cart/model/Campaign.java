package com.shopping.cart.model;

import com.shopping.cart.enums.DiscountType;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Burak Naroglu
 */

@Data
@AllArgsConstructor
public class Campaign {

    private Category category;

    private Double discountValue;

    private int amount;

    private DiscountType discountType;
}
