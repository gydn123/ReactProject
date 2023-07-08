package com.exciting.entity;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
@Entity
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id", nullable = false)
    private int orderId;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    private Member member;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "checkorder", columnDefinition = "BOOLEAN")
    private boolean checkOrder;

    @Column(name = "checkrefund", columnDefinition = "BOOLEAN")
    private boolean checkRefund;

    @Column(name = "use_point", precision = 7, scale = 2)
    private BigDecimal usePoint;

}
