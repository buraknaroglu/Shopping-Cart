package com.shopping.cart.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Burak Naroglu
 */

@Data
public class Category {

    private Category parentCategory;

    private String title;

    public Category(@JsonProperty("title") String title) {
        this.title = title;
    }
}
