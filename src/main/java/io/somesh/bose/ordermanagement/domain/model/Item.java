package io.somesh.bose.ordermanagement.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "ITEM")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Item {

  @Id
  @SequenceGenerator(name = "ORDER_SEQ", sequenceName = "ORDER_SEQ", allocationSize = 100)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_SEQ")
  private long itemId;

  private long product_id;

  private int qnty;

  private double price;

  @JoinColumn(name = "ORDER_NEW_ID", updatable = true, insertable = true)
  @ManyToOne
  @JsonBackReference
  private Order orderRef;

  @Column(name = "CORRELATION_ID", nullable = false)
  private String correlationId;

}
