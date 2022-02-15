package io.somesh.bose.ordermanagement.application.service;

import io.somesh.bose.ordermanagement.application.exception.OrderException;
import io.somesh.bose.ordermanagement.application.dto.OrderReqDto;
import io.somesh.bose.ordermanagement.domain.model.*;
import io.somesh.bose.ordermanagement.domain.repo.OrderRepository;
import io.somesh.bose.ordermanagement.domain.repo.ProductRepository;
import io.somesh.bose.ordermanagement.infra.web.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderManagementService {

  @Autowired ProductRepository productRepository;

  @Autowired OrderRepository orderRepository;

  public Optional<Product> findProductPriceQuote(long productId){
    return productRepository.findById(productId);
  }

  @Transactional(isolation= Isolation.SERIALIZABLE, rollbackFor = OrderException.class)
  public Order purchaseOrder(List<OrderReqDto> orderReqDtoList){

   // log.debug("purchase order started for orderId");
    Order order = createOrder();

    for(OrderReqDto orderReq:orderReqDtoList) {
      Optional<Product> tempProduct = findProductPriceQuote(orderReq.getProductId());

      if(orderReq.getQnty()==0){
        log.error("Product with productId"+ orderReq.getProductId()+" has asked quantity 0!!");
        continue;
      }

      if (tempProduct.isPresent()){
        if (tempProduct.get().getAvailableCount() >= orderReq.getQnty()) {
          Item item = createItem(orderReq, tempProduct.get());
          order.addItem(item);
          tempProduct.get().decreaseCount(orderReq.getQnty());
        }
        else {
          throw  new OrderException("Error as productId"+ orderReq.getProductId() +"not available due to out of stock!!");
        }
      }
      else {
        log.error("Error no product with productId");
        throw  new OrderException("Error no product with productId "+orderReq.getProductId()+"!!");
      }
    }
    order = orderRepository.save(order);
    //log.info("order finished");
    return order;
  }

  private Order createOrder(){
    Order order = new Order();
    order.setOrderDate(Instant.now());
    order.setStatus(OrderStatus.PENDING);
    order.setCorrelationId(RequestContext.get(RequestContext.CORRELATION_ID_KEY));
    return order;
  }

  private Item createItem(OrderReqDto orderReqDto, Product product){
    Item item = new Item();
    item.setProduct_id(orderReqDto.getProductId());
    item.setQnty(orderReqDto.getQnty());
    item.setPrice(orderReqDto.getQnty()*product.getPrice());
    item.setCorrelationId(RequestContext.get(RequestContext.CORRELATION_ID_KEY));
    return item;
  }
}
