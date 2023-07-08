package com.exciting.promotion.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.exciting.dto.MypointDTO;
import com.exciting.dto.OrderResponseDTO;
import com.exciting.dto.PromotionDTO;
import com.exciting.dto.PromotionTicketDTO;
import com.exciting.entity.Promotion;
import com.exciting.entity.PromotionPrice;
import com.exciting.promotion.service.PromotionService;


@Controller
@RestController
public class PromotionController {

	@Autowired
	PromotionService promotionService;
	

	@GetMapping(value = "/promotion")
	@ResponseBody
	public List<PromotionDTO> promotionList() {
		List<Promotion> list = this.promotionService.selectPromotion();

		List<PromotionDTO> dtoList = list.stream()
				.map(promotion -> new PromotionDTO(
						promotion.getPromotion_id(),
						promotion.getPromotion_content(),
						promotion.getPromotion_name(),
						promotion.getPromotion_img()))
				.collect(Collectors.toList());

		return dtoList;
	}

	@GetMapping(value = "/promotionprice")
	@ResponseBody
	public List<PromotionDTO> promotionprice(@RequestParam("promotion_id") Integer promotion_id) {
	    List<PromotionDTO> pricelist = promotionService.selectPromotionprice(promotion_id);
	    
	    List<PromotionDTO> dtoList = pricelist.stream()
				.map(promotionprice -> new PromotionDTO(
						promotionprice.getPromotion_id(),
		                promotionprice.getPromotion_name(),
		                promotionprice.getPromotion_content(),
		                promotionprice.getPromotion_img(),
		                promotionprice.getTicket_price(),
		                promotionprice.getTicket_id(),
		                promotionprice.getDiscount()))
				.collect(Collectors.toList());

	    return dtoList;
	}

	@GetMapping(value = "/order")
	public ResponseEntity<OrderResponseDTO> orderGet(@RequestParam("promotion_id") Integer promotion_id) {
	    String member_id = "qwer1234";
	    List<PromotionDTO> price = this.promotionService.selectPromotionprice(promotion_id);
	    List<MypointDTO> mypoint = this.promotionService.selectmypoint1(member_id);

	    OrderResponseDTO response = new OrderResponseDTO(price, mypoint);
	    return ResponseEntity.ok(response);
	}

	//	@ResponseBody
	//	@PostMapping(value = "/order")
	//	public int orderPost(@RequestBody Map<String, Object> map, HttpSession session) {
	//		System.out.println(map+"map@!@$!@$!@$!@$");
	//		String jsonStr = (String)map.get("myJSON");
	//		// ObjectMapper 객체 생성
	//		ObjectMapper objectMapper = new ObjectMapper();
	//		List<Map<String, Object>> list = null;
	//		// List로 변환
	//		try{
	//			list = objectMapper.readValue(jsonStr, new TypeReference<List<Map<String, Object>>>(){});
	//		}catch(Exception e) {
	//		}
	//		Map<String, Object> mapT = new HashMap<String, Object>();
	//		//		member_id값 세션에서 받아와서 mapT에 넣음
	//		//String member_id = (String)session.getAttribute("member_id");
	//		String member_id = "qwer1234";
	//		double use_point = Double.parseDouble((String) map.get("use_point"));
	//		mapT.put("use_point", use_point);
	//		mapT.put("member_id", member_id);
	//		//		order테이블 데이터 추가
	//		int check = promotionTempService.insertOrder(mapT);
	//
	//		//		order테이블을 추가할 때 얻은 PK값을 list의 map에 각각 추가
	//		int order_id = Integer.valueOf(mapT.get("order_id").toString()).intValue();
	//		for(int i=0; i < list.size(); i++) {
	//			Map<String, Object> changeMap = list.get(i);
	//			changeMap.put("order_id", order_id);
	//		}
	//		//		order_detail테이블에 데이터 추가
	//		if(check == 1) {
	//			promotionTempService.insertOrderDetail(list);
	//		}
	//
	//		return check;
	//	}
	//
	//
	//	@GetMapping(value = "/orderlist")
	//	public ModelAndView orderlist(HttpSession session) {
	//		String member_id = (String)session.getAttribute("member_id");
	//		Map<String, Object> dataMap = new HashMap<>();
	//		dataMap.put("member_id", member_id);
	//		
	//		List<MypointDTO> mypoint = this.promotionTempService.selectmypoint1(dataMap);
	//		List<Map<String, Object>> orderlist = this.promotionTempService.selectOrderlist(dataMap);
	//
	//		for(Map<String, Object> order : orderlist) {
	//			String date = order.get("order_date").toString();
	//			String ymd=date.substring(0,10);
	//			String ymd2=ymd.replace("-",".");
	//			String hms=date.substring(11);
	//			String postdate=ymd2+" "+hms;
	//			order.put("order_date", postdate);
	//		}
	//		System.out.println("@@@@@@@@@@"+orderlist+"@@@@@@@@@@@@");
	//		ModelAndView mav = new ModelAndView();
	//		mav.addObject("data1", mypoint);
	//		mav.addObject("data", orderlist);
	//		mav.setViewName("orderlist");
	//		return mav;
	//	}
	//	@GetMapping(value = "/confirm")
	//	public ModelAndView confirm(HttpSession session, @RequestParam Map<String,Object> map) {
	//		String member_id = (String)session.getAttribute("member_id");
	//		Map<String, Object> dataMap = new HashMap<>();
	//		dataMap.put("member_id", member_id);
	//		dataMap.put("order_id", map.get("order_id"));
	//		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@ !!!!!!!!!!!!!!!!!!!!!"+dataMap);
	//		List<Map<String, Object>> orderlist = this.promotionTempService.selectconfirm(dataMap);
	//		
	//		for(Map<String, Object> order : orderlist) {
	//			String date = order.get("order_date").toString();
	//			String ymd=date.substring(0,10);
	//			String ymd2=ymd.replace("-",".");
	//			String hms=date.substring(11);
	//			String postdate=ymd2+" "+hms;
	//			order.put("order_date", postdate);
	//		}
	//		System.out.println("@@@@@@@@@@"+orderlist+"@@@@@@@@@@@@");
	//		ModelAndView mav = new ModelAndView();
	//		mav.addObject("data", orderlist);
	//		mav.setViewName("confirm");
	//		return mav;
	//	}
	//	
	//	@GetMapping(value = "/refund")
	//	public ModelAndView refund(HttpSession session) {
	//		String member_id = (String)session.getAttribute("member_id");
	//		Map<String, Object> dataMap = new HashMap<>();
	//		dataMap.put("member_id", member_id);
	//
	//		List<Map<String, Object>> orderlist = this.promotionTempService.selectOrderlist(dataMap);
	//
	//		for(Map<String, Object> order : orderlist) {
	//			String date = order.get("order_date").toString();
	//			String ymd=date.substring(0,10);
	//			String ymd2=ymd.replace("-",".");
	//			String hms=date.substring(11);
	//			String postdate=ymd2+" "+hms;
	//			order.put("order_date", postdate);
	//		}
	//		ModelAndView mav = new ModelAndView();
	//		mav.addObject("data", orderlist);
	//		mav.setViewName("refund");
	//		return mav;
	//	}
	//	
	//	@PostMapping(value = "/refund")
	//	@ResponseBody
	//	public ResponseEntity<Map<String, Object>> refundOrder(@RequestBody Map<String, Object> map) {
	//		System.out.println("@@@@@@@@@@@@@@@@@"+map+"@@@@@@@@@@@@@@@@");
	//	    int rs = this.promotionTempService.refundOrder(map);
	//	    System.out.println("@@@@@@@@@@@@@@@@@"+rs+"@@@@@@@@@@@@@@@@");
	//
	//	    Map<String, Object> response = new HashMap<String, Object>();
	//	    if(rs == 1) {
	//	        response.put("success", true);
	//	    } else {
	//	        response.put("success", false);
	//	    }
	//	    
	//	    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	//	}
	//	
	//	@PostMapping(value = "/check")
	//	@ResponseBody
	//	public ResponseEntity<Map<String, Object>> check(@RequestBody Map<String, Object> map, HttpSession session) {
	//		String member_id = (String)session.getAttribute("member_id");
	//		//double use_point = Double.parseDouble((String) map.get("use_point"));
	//		double use_point = Double.parseDouble(Integer.toString((Integer) map.get("use_point")));
	//
	//		map.put("member_id", member_id);
	//		System.out.println("@@@@@@@@@@@@@@@@@"+map+"@@@@@@@@@@@@@@@@");
	//		
	//		if(use_point != 0.00) {
	//			this.promotionTempService.insertUsepoint(map);
	//		}
	//		
	//		int rs = this.promotionTempService.checkOrder(map);
	//		System.out.println("@@@@@@@@@@@@@@@@@"+rs+"@@@@@@@@@@@@@@@@");
	//		
	//		Map<String, Object> response = new HashMap<String, Object>();
	//		if(rs == 1) {
	//			this.promotionTempService.insertMypoint(map);
	//			response.put("success", true);
	//		} else {
	//			response.put("success", false);
	//		}
	//		
	//		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	//	}
	//	
	////	@GetMapping(value = "/mypoint")
	////	public ModelAndView mypoint(HttpSession session) {
	////		String member_id = (String)session.getAttribute("member_id");
	////		Map<String, Object> dataMap = new HashMap<>();
	////		dataMap.put("member_id", member_id);
	////		
	////
	////		List<MypointDTO> mypoint = this.promotionTempService.selectmypoint1(dataMap);
	////		List<Map<String, Object>> orderlist = this.promotionTempService.selectmypoint(dataMap);
	////
	////		for(Map<String, Object> order : orderlist) {
	////			String date = order.get("order_date").toString();
	////			String ymd=date.substring(0,10);
	////			String ymd2=ymd.replace("-",".");
	////			String hms=date.substring(11);
	////			String postdate=ymd2+" "+hms;
	////			order.put("order_date", postdate);
	////		}
	////		ModelAndView mav = new ModelAndView();
	////		mav.addObject("data", orderlist);
	////		mav.addObject("data1", mypoint);
	////		mav.setViewName("mypoint");
	////		return mav;
	////	}
	//	
	//	@GetMapping(value = "/mypoint")
	//	@ResponseBody
	//	public MyPointResponseDTO mypoint(HttpSession session) {
	//	    String member_id = "qwer1234";
	//	    Map<String, Object> dataMap = new HashMap<>();
	//	    dataMap.put("member_id", member_id);
	//
	//	    List<MypointDTO> mypoint = this.promotionTempService.selectmypoint1(dataMap);
	//	    List<Map<String, Object>> orderMapList = this.promotionTempService.selectmypoint(dataMap);
	//	    //System.out.println(orderMapList+"@$!@$@!$orderMapList");
	//
	//	    List<PromotionDTO> orderlist = new ArrayList<>();
	//	    for(Map<String, Object> order : orderMapList) {
	//	        String date = order.get("order_date").toString();
	//	        String ymd=date.substring(0,10);
	//	        String ymd2=ymd.replace("-",".");
	//	        String hms=date.substring(11);
	//	        String postdate=ymd2+" "+hms;
	//	        order.put("order_date", postdate);
	//	        
	//	        PromotionDTO dto = new PromotionDTO();
	//	        dto.setMember_id((String) order.get("member_id"));
	//	        dto.setOrder_date((String) order.get("order_date"));
	//	        dto.setQuantity((Integer) order.get("quantity"));
	//	        dto.setPromotion_name((String) order.get("promotion_name"));
	//	        dto.setPromotion_img((String) order.get("promotion_img"));
	//	        dto.setTicket_name((String) order.get("ticket_name"));
	//	        dto.setUse_point((BigDecimal) order.get("use_point"));
	//	        dto.setTicket_price((Integer) order.get("ticket_price"));
	//	        dto.setPromotion_content((String) order.get("promotion_content"));
	//	        dto.setDiscount((Double) order.get("discount"));
	//	        dto.setOrder_id((Integer) order.get("order_id"));
	//	        dto.setA_name((String) order.get("a_name"));
	//	        dto.setCheckrefund((Boolean) order.get("checkrefund"));
	//	        dto.setCheckorder((Boolean) order.get("checkorder"));
	//	        
	//	        orderlist.add(dto);
	//	     
	//	    }
	//	    System.out.println(mypoint+"@$!@$mypoint!$@$");
	//
	//	    MyPointResponseDTO response = new MyPointResponseDTO(orderlist, mypoint);
	//	    return response;
	//	}


}
