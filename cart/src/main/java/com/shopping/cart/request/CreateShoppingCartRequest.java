package com.shopping.cart.request;

import com.shopping.cart.model.Category;
import lombok.Data;

/**
 * @author Burak Naroglu
 */

@Data
public class CreateShoppingCartRequest {

    private Category category;

    private String productName;

    private Double productPrice;

    private int productAmount;

}
