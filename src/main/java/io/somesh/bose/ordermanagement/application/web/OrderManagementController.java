package io.somesh.bose.ordermanagement.application.web;

import io.somesh.bose.ordermanagement.application.dto.OrderReqDto;
import io.somesh.bose.ordermanagement.application.service.OrderManagementService;
import io.somesh.bose.ordermanagement.domain.model.Order;
import io.somesh.bose.ordermanagement.domain.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderManagementController {

  @Autowired OrderManagementService orderManagementService;

  @GetMapping("/price")
  public ResponseEntity getPriceQuote(@RequestParam int productId){
    Optional<Product> product= orderManagementService.findProductPriceQuote(productId);

    if(product.isPresent()){
      return ResponseEntity.ok(product.get());
    }

    return ResponseEntity.notFound().build();
  }

  @PostMapping("/purchase")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity purchaseProduct(@RequestBody List<OrderReqDto> orderDto){
    Order order= orderManagementService.purchaseOrder(orderDto);
    if (order !=null)
      return ResponseEntity.ok(order);
    return ResponseEntity.badRequest().build();
  }

}
