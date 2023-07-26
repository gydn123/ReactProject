package com.exciting.promotion.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.exciting.dto.PromotionDTO;
import com.exciting.entity.PromotionPrice;

@Repository
public interface PromotionPriceRepository extends JpaRepository<PromotionPrice, Integer> {
    @Query(value = "SELECT new com.exciting.dto.PromotionDTO(a.promotion_id, a.promotion_content, a.promotion_name, a.promotion_img"
<<<<<<< HEAD
    		+ ", b.ticket_price,b.ticket_id ,b.ticket_name, a.discount)" +
=======
    		+ ", b.ticket_price,b.ticket_id , a.discount)" +
>>>>>>> c4c5d371ef6604e7148b41197bdd85cb47685816
            " FROM Promotion a " +
            " JOIN a.tickets b " +
            " WHERE a.promotion_id = :promotion_id")
    List<PromotionDTO> selectPromotionprice(@Param("promotion_id") Integer promotion_id);
}