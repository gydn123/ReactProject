package com.exciting.customerService.controller;

<<<<<<< HEAD
import com.exciting.customerService.service.CustomerService;
import com.exciting.dto.*;
import com.exciting.entity.AnnouncementEntity;
import com.exciting.entity.BoardImgEntity;
import com.exciting.entity.FaqEntity;
import com.exciting.entity.InquiryEntity;
import com.exciting.utils.ChangeJson;
import com.exciting.utils.ChangeTEXT;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
=======
import java.io.File;
import java.time.LocalDateTime;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
>>>>>>> c4c5d371ef6604e7148b41197bdd85cb47685816
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

<<<<<<< HEAD
=======
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.exciting.customerService.service.CustomerService;
import com.exciting.dto.AnnouncementDTO;
import com.exciting.dto.BoardImgDTO;
import com.exciting.dto.FaqDTO;
import com.exciting.dto.InquiryDTO;
import com.exciting.dto.ResponseDTO;
import com.exciting.entity.AnnouncementEntity;
import com.exciting.entity.BoardImgEntity;
import com.exciting.entity.FaqEntity;
import com.exciting.entity.InquiryEntity;
import com.exciting.utils.ChangeJson;
import com.exciting.utils.FileUtils;

import lombok.extern.log4j.Log4j2;

>>>>>>> c4c5d371ef6604e7148b41197bdd85cb47685816
@RestController
@Log4j2
@RequestMapping("/customer")
public class CustomerServiceController {
	
	
	private CustomerService service;
	public CustomerServiceController(CustomerService service) {
		this.service = service;
	}
	

<<<<<<< HEAD
	private static final String BOARD_UPLOAD_PATH="D:\\static\\uploads";
	


	
//공지사항리스트 가져오기
	@GetMapping("/announcement")
	@ResponseBody
	public Page<AnnouncementDTO> getAnnouncement(AnnouncementDTO announcementDTO, @RequestParam Map<String, Object> map) {
=======
	private static final String BOARD_UPLOAD_PATH;
	
	
	static {
        
 
		try {
			String dirPath = "C:\\static\\uploads";;
		    BOARD_UPLOAD_PATH = dirPath;
		    FileUtils.createDirectory(BOARD_UPLOAD_PATH);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to get the upload path", e);
		}

    }
	
	

	@GetMapping("/announcement")
	@ResponseBody
	public Page<AnnouncementDTO> Announcement(AnnouncementDTO announcementDTO, @RequestParam Map<String, Object> map) {
>>>>>>> c4c5d371ef6604e7148b41197bdd85cb47685816

		AnnouncementEntity entity = AnnouncementDTO.toEntity(announcementDTO);
		int pageNum = Integer.parseInt(String.valueOf(map.get("pageNum")));
		String Search = String.valueOf(map.get("search"));

		Page<AnnouncementDTO> announcementList = service.getAnnouncementList(entity, pageNum, Search);

<<<<<<< HEAD
=======
		

		
		System.out.println(announcementList);

>>>>>>> c4c5d371ef6604e7148b41197bdd85cb47685816
		return announcementList;

		// 텍스트 처리

	}

<<<<<<< HEAD
	
//이미지 업로드
=======
	/*
	 * 
	 * 이미지 Image 업로드 Upload Start
	 * 
	 */

>>>>>>> c4c5d371ef6604e7148b41197bdd85cb47685816
	@PostMapping("/imageUpload")
	@ResponseBody
	public void imageUpload(@RequestParam(value = "file", required = false) List<MultipartFile> mf,
			@RequestParam(value="announcement_num", required=false) Integer announcement_num,
		    @RequestParam(value="inquiry_num", required=false) Integer inquiry_num) {
<<<<<<< HEAD
=======

>>>>>>> c4c5d371ef6604e7148b41197bdd85cb47685816
		
		try {
			if (mf != null) {
				String firstFileName = mf.get(0).getOriginalFilename();
				boolean isFileNotEmpty = firstFileName != null && !firstFileName.equals("");
				if (isFileNotEmpty) {
<<<<<<< HEAD
					for (MultipartFile file : mf) {
						BoardImgEntity boardImgEntity = imageParamsCheck(announcement_num,inquiry_num,null);

						String newFileName = changeImageName(file.getOriginalFilename());
						String safeFile = getFilePath(newFileName);

						//파일 이름저장
						boardImgEntity.setBoardimg(newFileName);
=======

					for (MultipartFile file : mf) {
						BoardImgEntity boardImgEntity = new BoardImgEntity();

						String originalFileName = System.currentTimeMillis() + file.getOriginalFilename();

						String uploadDir = BOARD_UPLOAD_PATH;
						String safeFile = uploadDir + "/" + originalFileName;

						//받은값이 있을시 넣음

						if(announcement_num != null && announcement_num != 0)
							boardImgEntity.setAnnouncement_num(announcement_num);
						else if(inquiry_num != null && inquiry_num != 0)
							boardImgEntity.setInquiry_num(inquiry_num);

						//파일 이름저장
						boardImgEntity.setBoardimg(originalFileName);
>>>>>>> c4c5d371ef6604e7148b41197bdd85cb47685816

						//DB에 저장
						service.customerImgInsert(boardImgEntity);

						//폴더에 저장
						file.transferTo(new File(safeFile));
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("BoardImgInsert Error");
		}

	}

<<<<<<< HEAD

	//이미지 삭제
	@DeleteMapping("/deleteBoardImg")
	@ResponseBody
	public void deleteBoardImg(@RequestParam(value = "announcement_num", required = false) Integer announcement_num,
							   @RequestParam(value = "inquiry_num", required = false) Integer inquiry_num,
							   @RequestParam(value="boardimg_num", required=false) Integer boardimg_num) {

		BoardImgEntity boardImgEntity = imageParamsCheck(announcement_num,inquiry_num,boardimg_num);


		// 기존 이미지 데이터를 조회 및 DB에 저장된 이미지 정보 삭제
		List<BoardImgEntity> OriginData = service.customerImgDelete(boardImgEntity);

		deleteFiles(OriginData);
	}

	@PostMapping("/insertAnnouncement")
	@ResponseBody
	public int announcementPost(@RequestBody AnnouncementDTO announcementDTO) {

		AnnouncementEntity entity = AnnouncementDTO.toEntity(announcementDTO);
		entity.setC_type("공지");
		entity.setPostdate(LocalDateTime.now());

		int announcement_num =service.insertAnnouncement(entity);


		return announcement_num;
	}

	private String changeImageName(String originalFileName){
		String newFileName = System.currentTimeMillis() + originalFileName;
		return newFileName;
	}

	private String getFilePath(String newFileName){
		String uploadDir = BOARD_UPLOAD_PATH;
		String safeFile = uploadDir + "/" + newFileName;

		return safeFile;
	}

	private BoardImgEntity imageParamsCheck(Integer announcement_num,Integer inquiry_num,Integer boardimg_num){
		BoardImgEntity boardImgEntity =new BoardImgEntity();

		if (announcement_num != null)
=======
	/*
	 * 
	 * 이미지 Image 업로드 Upload End
	 * 
	 */
	

	/*
	 * 
	 * 이미지 삭제 Image Delete Start
	 * 
	 */
	
	@DeleteMapping("/deleteBoardImg")
	@ResponseBody
	public void deleteBoardImg(@RequestParam(value = "announcement_num", required = false) Integer announcement_num,
			@RequestParam(value = "inquiry_num", required = false) Integer inquiry_num,
			@RequestParam(value="boardimg_num", required=false) Integer boardimg_num) {
		System.out.println(inquiry_num+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

		BoardImgEntity boardImgEntity = new BoardImgEntity();
		System.out.println(announcement_num);
		if (announcement_num != null) 
>>>>>>> c4c5d371ef6604e7148b41197bdd85cb47685816
			boardImgEntity.setBoardimg_num(announcement_num);
		else if (inquiry_num != null)
			boardImgEntity.setInquiry_num(inquiry_num);
		else if(boardimg_num!= null)
			boardImgEntity.setBoardimg_num(boardimg_num);
<<<<<<< HEAD

		return boardImgEntity;
	}

	private void deleteFiles(List<BoardImgEntity> originData) {
		for (BoardImgEntity imgEntity : originData) {
			String filePath = getFilePath(imgEntity.getBoardimg());
			deleteFile(filePath);
		}
	}

	private void deleteFile(String filePath) {
		File file = new File(filePath);
		if (file.exists()) {
			file.delete();
		}
	}
	
	
	//공지사항 글쓰기









	//공지사항 getAnnouncementDetail
=======
		System.out.println(boardImgEntity+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

		// 기존 이미지 데이터를 조회 및 DB에 저장된 이미지 정보 삭제
		List<BoardImgEntity> OriginData = service.customerImgDelete(boardImgEntity);
		System.out.println("喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝"+OriginData);

		// dto변환
		List<BoardImgDTO> OriginDataDTO = OriginData.stream().map(BoardImgDTO::new).collect(Collectors.toList());
		System.out.println("喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝喝"+OriginDataDTO);
		for (BoardImgDTO rs : OriginDataDTO) {

			// String uploadDir = BOARD_UPLOAD_PATH;
			String uploadDir = new File(BOARD_UPLOAD_PATH).getAbsolutePath();
			System.out.println(uploadDir);
			File file = new File(uploadDir, rs.getBoardimg());
			System.out.println(file);
			// String safeFile = uploadDir+"/"+originalFileName;
			if (file.exists()) { // 파일이 존재하면
				file.delete(); // 파일 삭제
			}
		}

		// int rs = service.deleteBoardImg(map);

	}
	
	/*
	 * 
	 * 이미지 삭제 Image Delete End
	 * 
	 */
	

	
	/*
	 * 
	 * 공지사항 글쓰기 announcement Write Start
	 * 
	 */
	
	//공지사항 글쓰기
	@PostMapping("/insertAnnouncement")
	@ResponseBody
	public int announcementPost(@RequestBody AnnouncementDTO announcementDTO) {
		
		AnnouncementEntity entity = AnnouncementDTO.toEntity(announcementDTO);
		
		
		
		entity.setC_type("공지");
		entity.setPostdate(LocalDateTime.now());
		
		int announcement_num =service.insertAnnouncement(entity);
		
		
		return announcement_num;
	}
	
	/*
	 * 
	 * 공지사항 글쓰기 announcement Write END
	 * 
	 */
	
	
	
	/*
	 * 
<<<<<<< HEAD
	 * 공지사항 세부 announcement Detail update Start
=======
	 * 공지사항 세부 announcement Detail Start
>>>>>>> 00efd0a6b25fd4cc196b249b2b4fc405442ad136
	 * 
	 */
>>>>>>> c4c5d371ef6604e7148b41197bdd85cb47685816
	@GetMapping(value ={"/view","/updateAnnouncement"})
	@ResponseBody
	public ResponseEntity<?> announcementView(AnnouncementDTO announcementDTO) {
		
		
		try {
			
			AnnouncementEntity entity = AnnouncementDTO.toEntity(announcementDTO);
<<<<<<< HEAD
=======
			
>>>>>>> c4c5d371ef6604e7148b41197bdd85cb47685816
			AnnouncementEntity announcementData = service.getAnnouncementOne(entity);
			
			
			List<BoardImgEntity> boardImgList = service.getAnnouncementImg(entity);

			//DTO변환
			List<BoardImgDTO> boardImgDTO = boardImgList.stream()
				    .filter(Objects::nonNull) // "null" 값인 요소들을 제외하고 필터링
				    .map(BoardImgDTO::new)
				    .collect(Collectors.toList());
			
			//이미지 경로 주입
			int num =0;
			String boardImg =null;
			for(BoardImgDTO i : boardImgDTO) {
				boardImg = i.getBoardimg();
<<<<<<< HEAD
				boardImg = "http://localhost:8080/uploads/"+ boardImg;
=======
				boardImg = "/uploads/"+ boardImg;
				System.out.println(BOARD_UPLOAD_PATH);
>>>>>>> c4c5d371ef6604e7148b41197bdd85cb47685816
				i.setBoardimg(boardImg);
				num++;
			}

			JSONObject jsonData = ChangeJson.ToChangeJson(announcementData);
			
			jsonData.put("boardImg", boardImgDTO);

			ResponseDTO<JSONObject> response = ResponseDTO.<JSONObject>builder().json(jsonData).build();
			
			return ResponseEntity.ok().body(response.getJson());
			
		} catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<JSONObject> response = ResponseDTO.<JSONObject>builder().error(error).build();
			return ResponseEntity.badRequest().body(response.getError());
		}
		
	}
<<<<<<< HEAD

=======
	/*
	 * 

	 * 공지사항 세부 announcement Detail update END
	 * 
	 */
>>>>>>> c4c5d371ef6604e7148b41197bdd85cb47685816

	/*
	 * 
	 * 공지사항 세부 announcement Detail update END
	 * 
	 */
	
	@PutMapping("/updateAnnouncement")
	public void updateBoardpost(@RequestBody AnnouncementDTO dto){
<<<<<<< HEAD
=======
		System.out.println(dto);
>>>>>>> c4c5d371ef6604e7148b41197bdd85cb47685816

		AnnouncementEntity announcementEntity = AnnouncementDTO.toEntity(dto);
		
		service.updateAnnounment(announcementEntity);
		
	}

	/*
	 * 
	 * 공지사항 세부 announcement Detail update END
	 * 
	 */
	
	/*
	 * 
	 * 공지사항 세부 삭제 announcementDetail Delete Start
	 * 
	 */
	
	
/*
 * 
 * 공지사항 세부 삭제 announcementDetail Delete END
 * 
 */
	
	@DeleteMapping("deleteAnnouncement")
	public String  deleteAnnouncement(AnnouncementDTO dto) {
		
		AnnouncementEntity announcementEntity = AnnouncementDTO.toEntity(dto);
	
		service.deleteAnnouncement(announcementEntity);
		
		return "forward:/deleteBoardImg";
		
	}
<<<<<<< HEAD


=======
	
	
	


//	
//	@RequestMapping(value = "/customer/boardImgShow", method = RequestMethod.POST)
//	@ResponseBody
//	public List<Map<String,Object>> boardImgShow(@RequestParam Map<String,Object>map){
//		//System.out.println("ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
//		//System.out.println("ajax 이미지1+++++++++++++++++++++++++++++++++++++++++++++++++++"+map);
//		List<Map<String,Object>> list = service.customerImgSelect(map);
//		
//		//System.out.println("ajax 이미지2+++++++++++++++++++++++++++++++++++++++++++++++++++"+list);
//
//		if(list.size()!=0) {			
//			for(Map<String,Object> img : list){
//				img.put("boardImg", BOARD_LOAD_PATH+img.get("boardImg"));
//			}
//		}
//		
//		
//		return list;
//	}
//	
//	

>>>>>>> c4c5d371ef6604e7148b41197bdd85cb47685816
	
	@GetMapping("/getfaqList")
	@ResponseBody
	public Page<FaqDTO> getfaqList(FaqDTO dto,int pageNum) {
		
		FaqEntity faqEntity = FaqDTO.toEntity(dto);
<<<<<<< HEAD
		Page<FaqDTO> faqList = service.getFaqList(faqEntity,pageNum);

		for(FaqDTO faqdto : faqList){
			faqdto.setTitle(ChangeTEXT.ToTextarea(faqdto.getTitle()));
			faqdto.setContent(ChangeTEXT.ToTextarea(faqdto.getContent()));
		}
		
		return faqList;
	}

	@PutMapping("/updateFaq")
	public void updateFaq(@RequestBody FaqDTO dto){
		FaqEntity faqEntity = FaqDTO.toEntity(dto);
		service.updateFaq(faqEntity);
	}

	@DeleteMapping("/deleteFaq")
	public void deleteFaq(FaqDTO dto){
		FaqEntity faqEntity = FaqDTO.toEntity(dto);
		service.deleteFaq(faqEntity);
	}
	
=======
		System.out.println(dto+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		String f_type= null;
		Page<FaqDTO> faqList = service.getFaqList(faqEntity,pageNum);
		
		return faqList;
	}
	
//	
//	//이거 나중에 업데이트로 이용하셈 
//	@RequestMapping(value = "/customer/getfaqList", method = RequestMethod.GET)
//	@ResponseBody
//	public List<Map<String,Object>> getmap2(@RequestParam Map<String,Object> map) {
//		//System.out.println("+++++++++++++너왔어?"+map);
//		
//		CustomerServiceController cus = new CustomerServiceController();
//		System.out.println(map.get("start"));
//		
//		map.put("start", map.get("start"));
//		
//		System.out.println("+++++++++++++++++++++++++++++++++++++++"+map);
//		List<Map<String,Object>> faqList = service.getFaqList(map);
//		
//		for(Map<String,Object> map2 : faqList) {
//			
//			//고객read
//			String content = ChangeJavanontextarea.change(String.valueOf(map2.get("content")));
//			map2.put("content", content);
//			String title = ChangeJavanontextarea.change(String.valueOf(map2.get("title")));
//			map2.put("title", title);
//			
//			
//		
//		}
//		//자주묻는 질문 내용 불러오기
//		//페이징
//		//묻는 질문 c_type로 태그 나눠서 그거 클릭하면 분류별로 보여주는 탭
//		//검색은 필요없을거같음
//		
//		return faqList;
//	}
//	
//	@RequestMapping(value = "/customer/faqWrite", method = RequestMethod.POST)
//	@ResponseBody
//	public int faqWrite(@RequestParam Map<String,Object> map) {
//		
//		String title = ChangeTEXT.change(String.valueOf(map.get("title")));
//		map.put("title", title);
//		String content = ChangeTEXT.change(String.valueOf(map.get("content")));
//		map.put("content", content);
//		
//		int rs=service.faqWrite(map);
//		//자주묻는 질문 내용 불러오기
//		//페이징
//		//묻는 질문 c_type로 태그 나눠서 그거 클릭하면 분류별로 보여주는 탭
//		//검색은 필요없을거같음
//		
//		return rs;
//	}
//	
//	@RequestMapping(value = "/customer/faqUpdate", method = RequestMethod.POST)
//	@ResponseBody
//	public int faqUpdate(@RequestParam Map<String,Object> map) {
//		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+map);
//		String title = ChangeTEXT.change(String.valueOf(map.get("title")));
//		map.put("title", title);
//		String content = ChangeTEXT.change(String.valueOf(map.get("content")));
//		map.put("content", content);
//		
//		int rs= service.faqupdate(map);
//		System.out.println("-----------------------------------------------"+rs);
//		//자주묻는 질문 내용 불러오기
//		//페이징
//		//묻는 질문 c_type로 태그 나눠서 그거 클릭하면 분류별로 보여주는 탭
//		//검색은 필요없을거같음
//		return rs;
//	}
//	
//	@RequestMapping(value = "/customer/faqDelete", method = RequestMethod.POST)
//	@ResponseBody
//	public int faqDelete(@RequestParam Map<String,Object> map) {
//		
//		int rs=service.faqDelete(map);
//		//자주묻는 질문 내용 불러오기
//		//페이징
//		//묻는 질문 c_type로 태그 나눠서 그거 클릭하면 분류별로 보여주는 탭
//		//검색은 필요없을거같음
//		return rs;
//	}
//	
//	
//
>>>>>>> c4c5d371ef6604e7148b41197bdd85cb47685816

	@PostMapping("/insertInquiry")
	@ResponseBody
	public int announcementInquiryPOST(@RequestBody InquiryDTO dto) {
		
		InquiryEntity entity = InquiryDTO.toEntity(dto);
		int inquiry_num = service.insertInquiry(entity);
				
		return inquiry_num;
	}
	
	@GetMapping("/inquiryList")
	@ResponseBody
	public Page<InquiryDTO> consultationDetailsGET(InquiryDTO dto,String type,String search,int pageNum) {
<<<<<<< HEAD
=======
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+dto);
>>>>>>> c4c5d371ef6604e7148b41197bdd85cb47685816
		
		if(search == null)
			search= "";
		InquiryEntity entity = InquiryDTO.toEntity(dto);
		
		Page<InquiryDTO> InquiryList = service.getInquieyList(entity,pageNum,search,type);
		

		return InquiryList;
	
	}
	
	
	@GetMapping("/inquiryView")
	public ResponseEntity<?>  consultationViewGET(InquiryDTO dto) {
<<<<<<< HEAD
=======
		
>>>>>>> c4c5d371ef6604e7148b41197bdd85cb47685816
		try {
			InquiryEntity entity = InquiryDTO.toEntity(dto);
			
			List<Optional<InquiryEntity>> inquiryDetail = service.getInquiryDetail(entity);
			
			List<InquiryEntity> inquiryDetailDataOP = inquiryDetail.stream().map((detail) -> detail.get()).collect(Collectors.toList());
			List<InquiryDTO> inquiryDetailData = inquiryDetailDataOP.stream().map(InquiryDTO::new).collect(Collectors.toList());
			
			ResponseDTO<InquiryDTO> response = ResponseDTO.<InquiryDTO>builder().data(inquiryDetailData).build();
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<String> response = ResponseDTO.<String>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}

	
	
	@GetMapping("/inquiryImage")
	public ResponseEntity<?> inquiryImage(InquiryDTO dto){
		
		try {
			List<BoardImgEntity> boardImg = service.selectInquiryImg(dto);
			List<BoardImgDTO> boardImgList = boardImg.stream().map(BoardImgDTO::new).collect(Collectors.toList());
			
			for(BoardImgDTO b :boardImgList) {
<<<<<<< HEAD
				 b.setBoardimg("http://localhost:8080/uploads/"+b.getBoardimg());
=======
				 b.setBoardimg("/uploads/"+b.getBoardimg());
>>>>>>> c4c5d371ef6604e7148b41197bdd85cb47685816
				
			}	
			
			ResponseDTO<BoardImgDTO> response  = ResponseDTO.<BoardImgDTO>builder().data(boardImgList).build();
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			String  error = e.getMessage();
			ResponseDTO<BoardImgDTO> response  = ResponseDTO.<BoardImgDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
		
	}
	
	@PostMapping("/inquiryAnswer")
	public void consultationViewPOST(@RequestBody InquiryDTO dto) {
		InquiryEntity entity = InquiryDTO.toEntity(dto);
		
		service.InquieyAnswer(entity);		
		
	}
	
	@DeleteMapping("/deleteInquiry")
	public void  deleteInquiry(InquiryDTO dto) {
		int inquiry_num = dto.getInquiry_num();
		InquiryEntity entity = InquiryDTO.toEntity(dto);
		service.deleteInquiry(entity);
		
	}
	

}
