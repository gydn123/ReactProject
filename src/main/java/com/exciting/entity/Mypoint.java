package com.exciting.entity;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "mypoint")
public class Mypoint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "point_id", nullable = false)
    private int point_id;

    @Column(name = "m_point", precision = 7, scale = 2)
    private BigDecimal m_point;
    

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private Order order_id;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    private Member member_id;

}
