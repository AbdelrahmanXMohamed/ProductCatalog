package com.example.product_catalog.exception.controller;
import com.example.product_catalog.exception.OrderedProductGreaterThanAvailableQuantityException;
import com.example.product_catalog.exception.OrderedProductMoreThanOrderLimitException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class OrderedProductExceptionController {
    @ExceptionHandler(OrderedProductGreaterThanAvailableQuantityException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> orderedProductGreaterThanAvailableQuantity(OrderedProductGreaterThanAvailableQuantityException ex)
     {
         Map<String,String> error=new HashMap<>();
         error.put("message","The ordered product with id : "+ex.getProductId()+" has only available "+ex.getAvailableProductQuantity()+" and you ordered "+ex.getOrderedProductQuantity());
         return error;
     }
    @ExceptionHandler(OrderedProductMoreThanOrderLimitException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> orderedProductMoreThanOrderLimit(OrderedProductMoreThanOrderLimitException ex)
    {
        Map<String,String> error=new HashMap<>();
        if (ex.getPreviousOrderedQuantity()>=ex.getProductOrderedLimit())
        {
            error.put("message","The ordered product with id : "+ex.getProductId()+" can't be ordered because you already passed your limit");
        }
        else {
            error.put("message","The ordered product with id : "+ex.getProductId()+" you can only order "+(ex.getProductOrderedLimit()-ex.getPreviousOrderedQuantity()));
        }

        return error;
    }
}
