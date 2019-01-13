package com.shopping.cart.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppliedDiscount {

    private Double discountPrice;

    public AppliedDiscount() { }
}
