package io.somesh.bose.ordermanagement.domain.repo;

import io.somesh.bose.ordermanagement.domain.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order,Long> {
}
