package io.somesh.bose.ordermanagement;

import io.somesh.bose.ordermanagement.application.dto.OrderReqDto;
import io.somesh.bose.ordermanagement.application.service.OrderManagementService;
import io.somesh.bose.ordermanagement.domain.model.Product;
import io.somesh.bose.ordermanagement.domain.repo.OrderRepository;
import io.somesh.bose.ordermanagement.domain.repo.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
@ActiveProfiles("test")
class OrderManagementApplicationTests {

	@Autowired OrderManagementService service;

	@Autowired ProductRepository product_repository;

	@Autowired OrderRepository order_repository;

	@Test
	void testMultipleCalltopurchase() {
		product_repository.save(Product.builder().productId(1).productName("test").availableCount(1).price(12.99).build());

		List<OrderReqDto> orderReqDtoList = new ArrayList<>();
		orderReqDtoList.add(OrderReqDto.builder().productId(1).qnty(1).build());

		List<OrderReqDto> orderReqDtoList2 = new ArrayList<>();
		orderReqDtoList.add(OrderReqDto.builder().productId(1).qnty(1).build());

		Thread t1= new Thread(new Runnable() {
			@Override public void run() {
				service.purchaseOrder(orderReqDtoList);
			}
		});

		Thread t2= new Thread(new Runnable() {
			@Override public void run() {
				service.purchaseOrder(orderReqDtoList2);
			}
		});

		t1.start();
		t2.start();

		Assertions.assertNotEquals(order_repository.count(),2);
	}


}
