package io.somesh.bose.ordermanagement.application.web;

import io.somesh.bose.ordermanagement.application.service.OrderManagementService;
import io.somesh.bose.ordermanagement.domain.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = OrderManagementController.class)
public class OrderManagementControllerTest {

  @Autowired MockMvc mockMvc;

  @MockBean OrderManagementService service;

  @Test
  public void findProductByIdTest() throws Exception{

    Mockito.when(service.findProductPriceQuote(1)).thenReturn(Optional.of(Product.builder().productName("test").productId(1).availableCount(1).price(12.33).build()));

    mockMvc.perform(MockMvcRequestBuilders.get("/order/price?productId=1"))
          .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  public void findProductByIdTestFail() throws Exception{

    mockMvc.perform(MockMvcRequestBuilders.get("/order/price?productId=1"))
        .andExpect(MockMvcResultMatchers.status().is4xxClientError());
  }


}
