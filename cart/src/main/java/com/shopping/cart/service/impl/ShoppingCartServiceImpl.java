package com.shopping.cart.service.impl;

import com.shopping.cart.constants.GlobalConstants;
import com.shopping.cart.model.Product;
import com.shopping.cart.model.ShoppingCart;
import com.shopping.cart.reponse.ServiceResult;
import com.shopping.cart.request.CreateShoppingCartRequest;
import com.shopping.cart.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Burak Naroglu
 */

@Service
public class ShoppingCartServiceImpl extends BaseService implements IShoppingCartService {

    @Autowired
    private IDiscountService iDiscountService;

    @Autowired
    private ICouponService iCouponService;

    @Autowired
    private IDeliveryCostService iDeliveryCostService;

    @Override
    public ServiceResult<ShoppingCart> createShoppingCart(List<CreateShoppingCartRequest> createShoppingCartRequests) {

        if (createShoppingCartRequests == null || createShoppingCartRequests.isEmpty()) {
            log.info("Invalid createShoppingCartRequest.");
            return new ServiceResult(GlobalConstants.EMPTY_CREATE_SHOPPING_CART_REQUEST, HttpStatus.BAD_REQUEST);
        }

        ShoppingCart shoppingCart = new ShoppingCart();
        createShoppingCartRequests.forEach(createShoppingCartRequest -> {
            shoppingCart.addItem(new Product(createShoppingCartRequest.getProductName(), createShoppingCartRequest.getProductPrice(), createShoppingCartRequest.getCategory()), createShoppingCartRequest.getProductAmount());
        });

        iDiscountService.applyDiscountToCartLineItem(shoppingCart);
        iCouponService.applyCouponToCart(shoppingCart);
        shoppingCart.setDeliveryCost(iDeliveryCostService.calculateDeliveryCost(shoppingCart));
        return new ServiceResult<>(shoppingCart, HttpStatus.OK);
    }

}

