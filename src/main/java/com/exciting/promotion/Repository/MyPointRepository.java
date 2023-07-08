package com.exciting.promotion.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.exciting.dto.MypointDTO;
import com.exciting.entity.Mypoint;

@Repository
public interface MyPointRepository extends JpaRepository<Mypoint, Integer> {
	@Query(value = "SELECT new com.exciting.dto.MypointDTO(SUM(m.m_point) AS sum_point, m.member_id.member_id) "
	        + " FROM Mypoint as m "
	        + " WHERE m.member_id.member_id = :member_id "
	        + " GROUP BY m.member_id")
	List<MypointDTO> select_mypoint1(@Param("member_id") String member_id);
}