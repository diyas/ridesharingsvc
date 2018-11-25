package com.test.ridesharingsvc.repository;

import com.test.ridesharingsvc.model.OrdersLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLogRepo extends JpaRepository<OrdersLog, Long> {

    //Optional<Order> findByOrderId(String orderId);

}
