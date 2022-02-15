package io.somesh.bose.ordermanagement.domain.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Product {

  @Id
  private long productId;

  private String productName;

  private double price;

  private int availableCount;

  public void decreaseCount(int count){
    this.availableCount-=count;
  }

}
