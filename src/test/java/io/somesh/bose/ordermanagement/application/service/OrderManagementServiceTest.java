package io.somesh.bose.ordermanagement.application.service;

import io.somesh.bose.ordermanagement.application.dto.OrderReqDto;
import io.somesh.bose.ordermanagement.domain.model.Product;
import io.somesh.bose.ordermanagement.domain.repo.OrderRepository;
import io.somesh.bose.ordermanagement.domain.repo.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class OrderManagementServiceTest {

  @Mock
  private OrderRepository orderRepository;

  @Mock
  private ProductRepository productRepository;

  @InjectMocks
  private OrderManagementService service;

  @Test
  public void purchaseOrderTest(){
    Mockito.when(productRepository.findById(1L)).thenReturn(
        Optional.of(Product.builder().productName("test").productId(1).availableCount(1).price(12.33).build()));

    List<OrderReqDto> orderReqDtoList = new ArrayList<>();
    orderReqDtoList.add(OrderReqDto.builder().productId(1).qnty(1).build());

    service.purchaseOrder(orderReqDtoList);

    Mockito.verify(orderRepository,Mockito.times(1)).save(Mockito.any());
  }

}
