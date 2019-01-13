package com.shopping.cart.service;

import com.shopping.cart.model.ShoppingCart;
import com.shopping.cart.reponse.ServiceResult;
import com.shopping.cart.request.CreateShoppingCartRequest;

import java.util.List;

public interface IShoppingCartService {

    public ServiceResult<ShoppingCart> createShoppingCart(List<CreateShoppingCartRequest> createShoppingCartRequests);

}
