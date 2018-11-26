package com.test.ridesharingsvc.repository;

import com.test.ridesharingsvc.model.Order;
import com.test.ridesharingsvc.model.OrdersLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderLogRepo extends JpaRepository<OrdersLog, Long> {

    Optional<OrdersLog> findByOrderOrderId(Long orderId);
    Page<OrdersLog> findByOrder(Order order, Pageable page);

}
