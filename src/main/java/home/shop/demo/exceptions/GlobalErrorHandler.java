package home.shop.demo.exceptions;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(value = InsufficientStockException.class)
    @ResponseBody
    public String insufficientStock(InsufficientStockException ex) {
        return "Not enough products in stock for this order.";
    }
}