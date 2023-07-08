package com.exciting.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class MypointDTO {

	private BigDecimal sum_point;
	private BigDecimal m_point;
	private int order_id;
	private String member_id;
	
	public MypointDTO(BigDecimal sum_point, BigDecimal m_point, int orderId, String memberId) {
	    this.sum_point = sum_point;
	    this.m_point = m_point;
	    this.order_id = orderId;
	    this.member_id = memberId;
	}
	public MypointDTO(BigDecimal sum_point, String memberId) {
		this.sum_point = sum_point;
		this.member_id = memberId;
	}


}
