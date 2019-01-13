package com.shopping.cart.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Burak Naroglu
 */

@Data
@AllArgsConstructor
public class Product {

    private String title;

    private Double price;

    private Category category;
}
