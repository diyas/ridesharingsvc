package com.test.ridesharingsvc.model;

import lombok.Data;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;
    @Column(name = "user_id")
    private String userId;
    private String note;
    @Column(name = "lat_from")
    private String latFrom;
    @Column(name = "lon_from")
    private String lonFrom;
    @Column(name = "lat_to")
    private String latTo;
    @Column(name = "lon_to")
    private String lonTo;
    @Column(name = "sts_order")
    private String stsOrder;

}
