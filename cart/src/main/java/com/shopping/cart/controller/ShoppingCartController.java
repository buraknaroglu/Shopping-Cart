package com.shopping.cart.controller;

import com.shopping.cart.model.ShoppingCart;
import com.shopping.cart.request.CreateShoppingCartRequest;
import com.shopping.cart.service.IShoppingCartService;
import com.shopping.cart.reponse.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Burak Naroglu
 */

@RestController
@RequestMapping("cart")
public class ShoppingCartController extends BaseController {

    @Autowired
    private IShoppingCartService iShoppingCartService;

    @PostMapping
    public ResponseEntity<ServiceResult<ShoppingCart>> createShoppingCart(@RequestBody List<CreateShoppingCartRequest> createShoppingCartRequest) {
        return createResponse(iShoppingCartService.createShoppingCart(createShoppingCartRequest));
    }
}

