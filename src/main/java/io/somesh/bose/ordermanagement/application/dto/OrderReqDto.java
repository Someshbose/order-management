package io.somesh.bose.ordermanagement.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderReqDto {

  @JsonProperty("productId")
  private long productId;

  @JsonProperty("qnty")
  private int qnty;
}
