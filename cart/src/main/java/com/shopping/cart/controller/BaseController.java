package com.shopping.cart.controller;

import com.shopping.cart.reponse.ServiceResult;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * @author Burak Naroglu
 */

@Component
public abstract class BaseController {

    protected ResponseEntity createResponse(ServiceResult serviceResult) {
        return new ResponseEntity<>(serviceResult.getResult(), serviceResult.getHttpStatus());
    }

}

    