package io.somesh.bose.ordermanagement.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.*;

@Entity
@Table(name = "ORDER_NEW")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Order {

  @Id
  @SequenceGenerator(name = "ORDER_SEQ", sequenceName = "ORDER_SEQ", allocationSize = 100)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_SEQ")
  private long orderId;

  @OneToMany(fetch=FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "orderRef")
  private List<Item> itemsList;

  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss''Z", timezone = "UTC")
  private Instant orderDate;

  @Column(name = "CORRELATION_ID", nullable = false, unique = true)
  private String correlationId;

  public void addItem(Item item){
    if(itemsList==null){
      itemsList = new ArrayList<>();
    }
    itemsList.add(item);
    item.setOrderRef(this);
  }
}
