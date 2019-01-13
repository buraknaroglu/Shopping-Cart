package com.shopping.cart.reponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author Burak Naroglu
 */

@Data
@AllArgsConstructor
public class ServiceResult<T> {

    private T result;

    private HttpStatus httpStatus = HttpStatus.OK;

}
