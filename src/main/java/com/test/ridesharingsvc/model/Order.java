package com.test.ridesharingsvc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User users;
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
