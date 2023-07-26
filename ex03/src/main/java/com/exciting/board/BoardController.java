package com.exciting.board;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
<<<<<<< HEAD
import java.util.Objects;
import java.util.stream.Collectors;

import com.exciting.entity.*;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
=======
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> c4c5d371ef6604e7148b41197bdd85cb47685816
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.exciting.board.service.BoardService;
import com.exciting.dto.BoardDTO;
import com.exciting.dto.BoardFavoriteDTO;
import com.exciting.dto.BoardImgDTO;
import com.exciting.dto.BoardReplyDTO;
import com.exciting.dto.ResponseDTO;
<<<<<<< HEAD
import com.exciting.utils.ChangeJson;
import com.exciting.utils.ChangeTEXT;

import lombok.extern.log4j.Log4j2;

import javax.annotation.PostConstruct;
=======
import com.exciting.entity.BoardEntity;
import com.exciting.entity.BoardFavoriteEntity;
import com.exciting.entity.BoardImgEntity;
import com.exciting.entity.BoardReplyEntity;
import com.exciting.utils.ChangeJson;
import com.exciting.utils.ChangeTEXT;
import com.exciting.utils.FileUtils;

import lombok.extern.log4j.Log4j2;
>>>>>>> c4c5d371ef6604e7148b41197bdd85cb47685816
//import utils.BoardPage;
//import utils.ChangeJava;
//import utils.ChangeJavanontextarea;
//import utils.ChangeHtml;

@RestController
@Log4j2
@RequestMapping("/board")
public class BoardController {

	@Autowired
	BoardService service;

<<<<<<< HEAD
	private static String BOARD_UPLOAD_PATH = "D:/static/uploads/";

	// 이미지삭제 deleteimage
	@DeleteMapping("/deleteBoardImg")
	@ResponseBody
	public void deleteBoardImg(@RequestParam(value = "boardimg_num", required = false) Integer boardimg_num,
			@RequestParam(value = "board_id", required = false) Integer board_id) {
		BoardImgEntity boardImgEntity = createBoardImgEntity(boardimg_num, board_id, null);
		List<BoardImgEntity> originData = service.boardImgDelete(boardImgEntity);
		deleteFiles(originData);
	}

	// 이미지업로드 imageUpload
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

	/*
	 * 
	 * 이미지 Image 삭제 Delete Start
	 * 
	 * 
	 */

	@DeleteMapping("/deleteBoardImg")
	@ResponseBody
	public void deleteBoardImg(@RequestParam(value = "boardimg_num", required = false) Integer  boardimg_num,
			@RequestParam(value = "board_id", required = false) Integer  board_id) {
System.out.println("asdasdasdasdasdasdasd"+board_id);
		BoardImgEntity boardImgEntity = new BoardImgEntity();

		if (boardimg_num != null)
			boardImgEntity.setBoardimg_num(boardimg_num);
		else if (board_id != null)
			boardImgEntity.setBoard_id(board_id);

		// 기존 이미지 데이터를 조회 및 DB에 저장된 이미지 정보 삭제
		List<BoardImgEntity> OriginData = service.boardImgDelete(boardImgEntity);

		// dto변환
		List<BoardImgDTO> OriginDataDTO = OriginData.stream().map(BoardImgDTO::new).collect(Collectors.toList());

		for (BoardImgDTO rs : OriginDataDTO) {

			// String uploadDir = BOARD_UPLOAD_PATH;
			String uploadDir = new File(BOARD_UPLOAD_PATH).getAbsolutePath();
			File file = new File(uploadDir, rs.getBoardimg());
			// String safeFile = uploadDir+"/"+originalFileName;
			if (file.exists()) { // 파일이 존재하면
				file.delete(); // 파일 삭제
			}
		}

		// int rs = service.deleteBoardImg(map);

	}
	/*
	 * 
	 * 이미지 Image 삭제 Delete End
	 * 
	 */

	/*
	 * 
	 * 이미지 Image 업로드 Upload Start
	 * 
	 */

>>>>>>> c4c5d371ef6604e7148b41197bdd85cb47685816
	@PostMapping("/imageUpload/{board_id}")
	@ResponseBody
	public String imageUpload(@RequestParam(value = "file", required = false) List<MultipartFile> mf,
			@PathVariable("board_id") int board_id) {
<<<<<<< HEAD
		try {
			if (mf != null && !mf.isEmpty()) {
				String firstFileName = mf.get(0).getOriginalFilename();
				boolean isFileNotEmpty = firstFileName != null && !firstFileName.equals("");
				if (isFileNotEmpty) {
					for (MultipartFile file : mf) {
						String originalFileName = System.currentTimeMillis() + file.getOriginalFilename();
						String safeFile = getFilePath(originalFileName);
						BoardImgEntity boardImgEntity = createBoardImgEntity(null, board_id, originalFileName);
=======

		try {
			if (mf != null) {
				String firstFileName = mf.get(0).getOriginalFilename();
				boolean isFileNotEmpty = firstFileName != null && !firstFileName.equals("");
				if (isFileNotEmpty) {

					for (MultipartFile file : mf) {
						BoardImgEntity boardImgEntity = new BoardImgEntity();

						String originalFileName = System.currentTimeMillis() + file.getOriginalFilename();

						String uploadDir = BOARD_UPLOAD_PATH;
						String safeFile = uploadDir + "/" + originalFileName;

						boardImgEntity.setBoard_id(board_id);
						boardImgEntity.setBoardimg(originalFileName);

>>>>>>> c4c5d371ef6604e7148b41197bdd85cb47685816
						service.boardImgInsert(boardImgEntity);

						file.transferTo(new File(safeFile));
					}
				}
<<<<<<< HEAD
=======

>>>>>>> c4c5d371ef6604e7148b41197bdd85cb47685816
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("BoardImgInsert Error");
		}

		return "/board/createBoard";
	}

<<<<<<< HEAD
	// 게시판글쓰기 boardWrite
	@PostMapping("/addBoard")
=======
	/*
	 * 
	 * 이미지 Image 업로드 Upload End
	 * 
	 */


	/*
	 * 
	 * 게시글쓰기 Writeboard createboard insertboard Start
	 * 
	 */

	@PostMapping("/createBoard")
>>>>>>> c4c5d371ef6604e7148b41197bdd85cb47685816
	public int createBoardPost(@RequestBody BoardDTO boardDTO) {

		BoardEntity boardEntity = BoardDTO.toEntity(boardDTO);

		int board_id = service.createBoard(boardEntity);

		return board_id;
	}
<<<<<<< HEAD
	@GetMapping("/view")
	@ResponseBody
	public ResponseEntity<?> boardView(BoardDTO boardDTO) {
		try {
			BoardEntity boardEntity = BoardDTO.toEntity(boardDTO);
			Long boardReplyCnt = service.boardReplyCnt(boardDTO.getBoard_id());
			service.boardVisit(boardEntity);
			int board_id =boardDTO.getBoard_id();

			List<BoardImgDTO> boardImgDTOList = selectBoardImgAndToDTOList(board_id);

			BoardDTO boardViewData = service.boardView(boardDTO.getBoard_id());
			JSONObject jsonObj = createBoardJsonObject(boardViewData, boardImgDTOList, boardReplyCnt);

			ResponseDTO<JSONObject> response = ResponseDTO.<JSONObject>builder().json(jsonObj).build();
			return ResponseEntity.ok().body(response.getJson());
=======


	/*
	 * 
	 * 게시글쓰기 Writeboard createboard insertboard END
	 * 
	 */



	/*
	 * 
	 * 게시글 낱개 불러오기 START
	 * 
	 */
	@GetMapping("/view")
	@ResponseBody
	public ResponseEntity<?> boardView(BoardDTO boardDTO) {

		try {
			List<Map<String, Object>> boardView = new ArrayList<>();

			// 이미지 처리
			List<Map<String, Object>> boardimg = new ArrayList<>();
			;

			BoardEntity boardEntity = BoardDTO.toEntity(boardDTO);

			Long boardReplyCnt = service.boardReplyCnt(boardDTO.getBoard_id());

			// 조회수 업데이트 처리
			service.boardVisit(boardEntity);

			// 게시판 이미지 불러오기
			List<BoardImgEntity> boardImgEntity = service.boardImgSelect(boardDTO.getBoard_id());

			// 게시판 이미지 dto로 변환
			List<BoardImgDTO> boardImgDTO = boardImgEntity.stream().map(BoardImgDTO::new).collect(Collectors.toList());

			// 이미지 경로 주입
			List<String> boardimgPathData = boardImgDTO.stream().map(img -> "/uploads/" + img.getBoardimg())
					.collect(Collectors.toList());

			int num = 0;
			for (String i : boardimgPathData) {

				boardImgDTO.get(num).setBoardimg(i);
				num += 1;
			}

			// 전체 데이터
			BoardDTO boardViewData = service.boardView(boardDTO.getBoard_id());

			JSONObject jsonObj = ChangeJson.ToChangeJson(boardViewData);

			List<JSONObject> imgjson = boardImgDTO.stream().map(img -> ChangeJson.ToChangeJson(img))
					.collect(Collectors.toList());

			jsonObj.put("boardimg", imgjson);

			jsonObj.put("cnt", boardReplyCnt);
			boardView.add(jsonObj);


			ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(boardView).build();

			return ResponseEntity.ok().body(response.getData());

>>>>>>> c4c5d371ef6604e7148b41197bdd85cb47685816
		} catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
<<<<<<< HEAD
	}

	@GetMapping("/favoriteBoard")
	@ResponseBody
	public ResponseEntity<?> getFavoriteBoard(BoardDTO boardDTO) {
		try {
			BoardEntity entity = BoardDTO.toEntity(boardDTO);
			BoardDTO favoriteData = service.boardView(entity.getBoard_id());
			JSONObject favoriteJson = ChangeJson.ToChangeJson(favoriteData);

			List<JSONObject> responseData = new ArrayList<>();
			responseData.add(favoriteJson);

			ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(responseData).build();
=======

	}

	/*
	 * 
	 * 게시글 낱개 불러오기 END
	 * 
	 */

	@GetMapping("/favoriteBoard")
	@ResponseBody
	public ResponseEntity<?> favoriteBoardGet(BoardDTO boardDTO) {

		try {
			// 게시판 추천 ajax뽑아낼 자료
			BoardEntity entity = BoardDTO.toEntity(boardDTO);

			BoardDTO favoriteData = service.boardView(entity.getBoard_id());

			JSONObject favoriteJSON = ChangeJson.ToChangeJson(favoriteData);

			List<JSONObject> responseData = new ArrayList<>();

			responseData.add(favoriteJSON);

			ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().data(responseData).build();
			;
>>>>>>> c4c5d371ef6604e7148b41197bdd85cb47685816

			return ResponseEntity.ok().body(response.getData());
		} catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<BoardDTO> response = ResponseDTO.<BoardDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
<<<<<<< HEAD
=======

>>>>>>> c4c5d371ef6604e7148b41197bdd85cb47685816
	}

	@PostMapping("/favoriteBoard")
	@ResponseBody
	public int favoriteBoardPost(@RequestBody BoardFavoriteDTO boardFavoriteDTO) {
<<<<<<< HEAD

		try {
			BoardFavoriteEntity entity = BoardFavoriteDTO.toEntity(boardFavoriteDTO);
			// db 작업
			service.changeFavorite(entity, boardFavoriteDTO.getCheckData());
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
=======
		// member_id ,board_id 필요
		// 추천을 이미 햇는지 검사하는 코드
		try {
			BoardFavoriteEntity entity = BoardFavoriteDTO.toEntity(boardFavoriteDTO);
			// db 작업
			service.changefavorite(entity, boardFavoriteDTO.getCheckData());
			return 1;
		} catch (Exception e) {
>>>>>>> c4c5d371ef6604e7148b41197bdd85cb47685816
			return 0;
		}

	}

<<<<<<< HEAD

	@GetMapping("/replyList")
	@ResponseBody
	public ResponseEntity<?> getReplyList(BoardReplyDTO boardReplyDTO) {

		try {

			BoardReplyEntity replyEntity = BoardReplyDTO.ToEntity(boardReplyDTO);
			List<BoardReplyEntity> replyList = service.getCommentList(replyEntity);
			List<BoardReplyDTO> replyDTOList  = replyList.stream().map(BoardReplyDTO::new).collect(Collectors.toList());
			List<JSONObject> replyJsonList = replyDTOList.stream()
					.map(replyDTO -> ChangeJson.ToChangeJson(replyDTO))
					.collect(Collectors.toList());

			ResponseDTO<JSONObject> response = ResponseDTO.<JSONObject>builder().data(replyJsonList).build();
=======
	/*
	 * 
	 * 댓글리스트 출럭 Start
	 * 
	 */
	@GetMapping("/replyList")
	@ResponseBody
	public ResponseEntity<?> replyList(BoardReplyDTO boardReplyDTO) {

		try {

			BoardReplyEntity boardReplyEntity = BoardReplyDTO.ToEntity(boardReplyDTO);
			List<BoardReplyEntity> replyList = service.getCommentList(boardReplyEntity);
			List<BoardReplyDTO> replyListDTO = replyList.stream().map(BoardReplyDTO::new).collect(Collectors.toList());
			List<JSONObject> replyListJson = new ArrayList<>();

			for (BoardReplyDTO i : replyListDTO) {
				JSONObject jsonObj = ChangeJson.ToChangeJson(i);
				replyListJson.add(jsonObj);
			}

			ResponseDTO<JSONObject> response = ResponseDTO.<JSONObject>builder().data(replyListJson).build();
>>>>>>> c4c5d371ef6604e7148b41197bdd85cb47685816

			return ResponseEntity.ok().body(response.getData());
		} catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<JSONObject> response = ResponseDTO.<JSONObject>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}

	}

<<<<<<< HEAD
=======
	/*
	 * 
	 * 댓글리스트 출럭 END
	 * 
	 */

	/*
	 * 
	 * 댓글 추가 Start
	 * 
	 */
>>>>>>> c4c5d371ef6604e7148b41197bdd85cb47685816
	@PostMapping("/reply-insert")
	@ResponseBody
	public ResponseEntity<?> reply_insert(@RequestBody BoardReplyDTO boardReplyDTO) {

		try {
			// 문자 치환
			boardReplyDTO.setB_reply(ChangeTEXT.ToJAVA(boardReplyDTO.getB_reply()));

			BoardReplyEntity entity = BoardReplyDTO.ToEntity(boardReplyDTO);

			// 댓글 추가 작업
			service.replyInsert(entity);

			return ResponseEntity.ok().body(1);
		} catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<BoardReplyDTO> response = ResponseDTO.<BoardReplyDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}

<<<<<<< HEAD

=======
	/*
	 * 
	 * 댓글 추가 END
	 * 
	 */

	/*
	 * 
	 * 대댓글 추가 Start
	 * 
	 */
>>>>>>> c4c5d371ef6604e7148b41197bdd85cb47685816
	@PostMapping("/insertReReply")
	@ResponseBody
	public void reReplyinsert(@RequestBody BoardReplyDTO boardReplyDTO) {
		boardReplyDTO.setB_reply(ChangeTEXT.ToJAVA(boardReplyDTO.getB_reply()));
		BoardReplyEntity entity = BoardReplyDTO.ToEntity(boardReplyDTO);
		service.reReplyInsert(entity);
	}

<<<<<<< HEAD
=======
	/*
	 * 
	 * 대댓글 추가 END
	 * 
	 */

	/*
	 * 
	 * 대댓글 추가 END
	 * 
	 */

>>>>>>> c4c5d371ef6604e7148b41197bdd85cb47685816
	@PutMapping("/replyUpdate")
	@ResponseBody
	public int replyUpdate(@RequestBody BoardReplyDTO boardReplyDTO) {

		BoardReplyEntity ReplyEntity = BoardReplyDTO.ToEntity(boardReplyDTO);

		service.boardReply(ReplyEntity);

		return 0;
	}

<<<<<<< HEAD
=======
	/*
	 * 
	 * 대댓글 추가 END
	 * 
	 */
>>>>>>> c4c5d371ef6604e7148b41197bdd85cb47685816

	@DeleteMapping("/replyDelete")
	@ResponseBody
	public void replyDelete(BoardReplyDTO boardReplyDTO) {
		BoardReplyEntity boardReplyEntity = BoardReplyDTO.ToEntity(boardReplyDTO);
		service.replyDelete(boardReplyEntity);
	}

<<<<<<< HEAD
	@GetMapping("/updateBoard")
	@ResponseBody
	public ResponseEntity<?> updateBoard(BoardDTO boardDTO) {
		try {
			BoardEntity entity = BoardDTO.toEntity(boardDTO);
			BoardEntity boardDataEntity = service.updateBoard(entity);
			BoardDTO boardDataDTO = new BoardDTO(boardDataEntity);
			JSONObject jsonData = ChangeJson.ToChangeJson(boardDataDTO);

			List<BoardImgEntity> imgListEntity = service.boardImgSelect(boardDataEntity.getBoard_id());
			List<BoardImgDTO> imgList = imgListEntity.stream().map(BoardImgDTO::new).collect(Collectors.toList());
			List<BoardImgDTO> finalImgData = conversionImgPath(imgList);

			jsonData.put("boardImg", finalImgData);

			ResponseDTO<JSONObject> response = ResponseDTO.<JSONObject>builder().json(jsonData).build();
			return ResponseEntity.ok().body(response.getJson());
		} catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<JSONObject> response = ResponseDTO.<JSONObject>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}

	@PostMapping("/updateBoard")
	public int updateBoardpost(@RequestBody BoardDTO boardDTO) {
		try {
			BoardEntity boardentity = BoardDTO.toEntity(boardDTO);
=======
	/*
	 * 
	 * Board 게시글 update 수정 GET Start
	 * 
	 */
	@GetMapping("/updateBoard")
	@ResponseBody
	public ResponseEntity<?> updateBoard(BoardDTO boardDTO) {

		try {
			BoardEntity entity = BoardDTO.toEntity(boardDTO);

			// 게시물 정보 가져오기
			BoardEntity BoardDataEntity = service.updateBoard(entity);

			BoardDTO BoardDataDTO = new BoardDTO(BoardDataEntity);

			// json 파싱
			JSONObject jsonData = ChangeJson.ToChangeJson(BoardDataDTO);

			List<BoardImgEntity> imgListEntity = service.boardImgSelect(BoardDataEntity.getBoard_id());
			List<BoardImgDTO> imgList = imgListEntity.stream().map(BoardImgDTO::new).collect(Collectors.toList());

			// 이미지 각각의 요소 앞에 경로 주입
			List<String> finalImgData = imgList.stream().map(img -> "/uploads/" + img.getBoardimg())
					.collect(Collectors.toList());

			int num = 0;
			for (String i : finalImgData) {
				imgList.get(num).setBoardimg(i);
				num += 1;
			}
			// 이미지데이터 같이 전송
			jsonData.put("boardImg", imgList);

			ResponseDTO<JSONObject> response = ResponseDTO.<JSONObject>builder().json(jsonData).build();

			return ResponseEntity.ok().body(response.getJson());
		} catch (Exception e) {
			String error = e.getMessage();

			ResponseDTO<JSONObject> response = ResponseDTO.<JSONObject>builder().error(error).build();

			return ResponseEntity.badRequest().body(response);
		}

	}
	/*
	 * 
	 * Board 게시글 update 수정 GET END
	 * 
	 */

	/*
	 * 
	 * Board 게시글 update 수정 POST Start
	 * 
	 */

	@PostMapping("/updateBoard")
	public int updateBoardpost(@RequestBody BoardDTO boardDTO) {

		try {
			BoardEntity boardentity = BoardDTO.toEntity(boardDTO);

>>>>>>> c4c5d371ef6604e7148b41197bdd85cb47685816
			service.commitUpdateBoard(boardentity);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 2;
		}
	}

<<<<<<< HEAD
	@DeleteMapping("/deleteBoard")
	public void deleteBoard(BoardDTO boardDTO) {
		BoardEntity entity = BoardDTO.toEntity(boardDTO);
		List<BoardImgEntity> deleteImgs = service.deleteBoard(entity);
		deleteFiles(deleteImgs);
	}


	// 파일삭제로직 fileDelete(여러개)
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

	private String getFilePath(String fileName) {
		return BOARD_UPLOAD_PATH + fileName;
	}

	private BoardImgEntity createBoardImgEntity(Integer boardimg_num, Integer board_id, String originalFileName) {
		BoardImgEntity boardImgEntity = new BoardImgEntity();
		if (boardimg_num != null) {
			boardImgEntity.setBoardimg_num(boardimg_num);
		}
		if (board_id != null) {
			boardImgEntity.setBoard_id(board_id);
		}
		boardImgEntity.setBoardimg(originalFileName);
		return boardImgEntity;
	}

	private List<BoardImgDTO> selectBoardImgAndToDTOList(int board_id){
		List<BoardImgEntity> boardImgEntityList = service.boardImgSelect(board_id);
		List<BoardImgDTO> boardImgDTOList = boardImgEntityList.stream().map(BoardImgDTO::new).collect(Collectors.toList());
		boardImgDTOList = conversionImgPath(boardImgDTOList);
		return boardImgDTOList;
	}

	private List<BoardImgDTO> combineImgPath(List<BoardImgDTO> boardImgDTOList, List<String> boardimgPathData){
		int num = 0;
		for (String path : boardimgPathData) {
			boardImgDTOList.get(num).setBoardimg(path);
			num++;
		}
		return boardImgDTOList;
	}

	private List<BoardImgDTO> conversionImgPath(List<BoardImgDTO> boardImgDTOList){
		List<String> boardimgPathData = boardImgDTOList.stream()
				.map(img -> "http://localhost:8080/uploads/" + img.getBoardimg())
				.collect(Collectors.toList());
		return combineImgPath(boardImgDTOList, boardimgPathData);
	}

	private JSONObject createBoardJsonObject(BoardDTO boardViewData, List<BoardImgDTO> boardImgDTOList, Long boardReplyCnt) {
		JSONObject boardJsonObj = null;
		if (Objects.nonNull(boardViewData)) {
			boardJsonObj = ChangeJson.ToChangeJson(boardViewData);
			if (!boardImgDTOList.isEmpty()) {
				List<JSONObject> imgJsonList = boardImgDTOList.stream()
						.map(img -> ChangeJson.ToChangeJson(img))
						.collect(Collectors.toList());
				boardJsonObj.put("boardimg", imgJsonList);
			}
			if (boardReplyCnt != null) {
				boardJsonObj.put("cnt", boardReplyCnt);
			}
		}
		return boardJsonObj;
	}



	/*
	 *
	 * Board 게시글 Delete 삭제 END
	 *
=======
	/*
	 * 
	 * Board 게시글 update 수정 POST END
	 * 
	 */

	/*
	 * 
	 * Board 게시글 Delete 삭제 Start
	 * 
	 */

	@DeleteMapping("/deleteBoard")
	public void deleteBoard(BoardDTO boardDTO) {

		BoardEntity entity = BoardDTO.toEntity(boardDTO);

		List<BoardImgEntity> deleteImgs = service.deleteBoard(entity);

		//		BoardImgEntity boardImgEntity = new BoardImgEntity();


		// dto변환
		List<BoardImgDTO> OriginDataDTO = deleteImgs.stream().map(BoardImgDTO::new).collect(Collectors.toList());

		for (BoardImgDTO rs : OriginDataDTO) {

			// String uploadDir = BOARD_UPLOAD_PATH;
			String uploadDir = new File(BOARD_UPLOAD_PATH).getAbsolutePath();
			File file = new File(uploadDir, rs.getBoardimg());
			// String safeFile = uploadDir+"/"+originalFileName;
			if (file.exists()) { // 파일이 존재하면
				file.delete(); // 파일 삭제
			}
		}
	}

	/*
	 * 
	 * Board 게시글 Delete 삭제 END
	 * 
>>>>>>> c4c5d371ef6604e7148b41197bdd85cb47685816
	 */

}
