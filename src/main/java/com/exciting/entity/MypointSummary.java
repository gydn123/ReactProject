package com.exciting.entity;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class MypointSummary {
	private BigDecimal sumPoint;
	private BigDecimal mPoint;
	private int orderId;
	private String memberId;
	
	public MypointSummary(BigDecimal sumPoint, BigDecimal mPoint, int orderId, String memberId) {
        this.sumPoint = sumPoint;
        this.mPoint = mPoint;
        this.orderId = orderId;
        this.memberId = memberId;
    }

}
