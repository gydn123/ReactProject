package com.exciting.promotion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exciting.dto.MypointDTO;
import com.exciting.dto.PromotionDTO;
import com.exciting.entity.Promotion;
import com.exciting.entity.PromotionPrice;
import com.exciting.promotion.Repository.MyPointRepository;
import com.exciting.promotion.Repository.PromotionPriceRepository;
//import com.exciting.promotion.Repository.PromotionPriceRepository;
import com.exciting.promotion.Repository.PromotionRepository;

@Service
public class PromotionServiceImpl implements PromotionService{

	@Autowired
	PromotionRepository repository;
	@Autowired
	PromotionPriceRepository prorepository;
	@Autowired
	MyPointRepository pointrepository;

	@Override
	public List<Promotion> selectPromotion() {
		return repository.findAll();
	}

	@Override
	public List<PromotionDTO> selectPromotionprice(Integer promotion_id) {
		return prorepository.selectPromotionprice(promotion_id);
	}
//	@Override
//	public List<PromotionDTO> selectPromotionprice(Map<String, Object> map) {
//		return promotionTempDAO.selectPromotionprice(map);
//	}
//
//	@Override
//	public int insertOrder(Map<String, Object> map) {
//		return promotionTempDAO.insertOrder(map);
//	}
//
//	@Override
//	public int insertOrderDetail(List<Map<String, Object>> list) {
//		return promotionTempDAO.insertOrderDetail(list);
//	}
//
//	@Override
//	public List<Map<String, Object>> selectOrderlist(Map<String, Object> map) {
//		return promotionTempDAO.selectOrderlist(map);
//	}
//	@Override
//	public List<Map<String, Object>> selectconfirm(Map<String, Object> map) {
//		return promotionTempDAO.selectconfirm(map);
//	}
//	@Override
//	public List<Map<String, Object>> selectmypoint(Map<String, Object> map) {
//		return promotionTempDAO.selectMypoint(map);
//	}
	@Override
	public List<MypointDTO> selectmypoint1(String member_id) {
		return pointrepository.select_mypoint1(member_id);
	}

//	@Override
//	public int refundOrder(Map<String, Object> map) {
//		return promotionTempDAO.refundOrder(map);
//	}
//	@Override
//	public int checkOrder(Map<String, Object> map) {
//		return promotionTempDAO.checkOrder(map);
//	}
//	
//	@Override
//	public int insertMypoint(Map<String, Object> map) {
//		return promotionTempDAO.insertMypoint(map);
//	}
//	
//	@Override
//	public int insertUsepoint(Map<String, Object> map) {
//		return promotionTempDAO.insertUsepoint(map);
//	}
}
