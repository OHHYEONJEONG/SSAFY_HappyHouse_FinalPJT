package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.NoticeBoardDto;
import com.ssafy.happyhouse.service.NoticeBoardService;

import io.swagger.annotations.ApiOperation;

//http://localhost:9999/vue/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/noticeboard")
public class NoticeBoardController {

	private static final Logger logger = LoggerFactory.getLogger(NoticeBoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private NoticeBoardService boardService;

	/**
	 * 모든 글의 정보 리스트를 반환한다.
	 * @return
	 * @throws Exception
	 */
    @ApiOperation(value = "모든 게시글의 정보를 반환한다.", response = List.class)
	@GetMapping("/select")
	public ResponseEntity<List<NoticeBoardDto>> retrieveBoard() throws Exception {
		logger.debug("selectedBoard - 호출");
		return new ResponseEntity<List<NoticeBoardDto>>(boardService.retrieveBoard(), HttpStatus.OK);
	}

    /**
     * 글 번호에 대한 상세보기 기능 제공
     * @param no 선택한 글 번호
     * @return
     */
    @ApiOperation(value = "글번호에 해당하는 게시글의 정보를 반환한다.", response = NoticeBoardDto.class)    
	@GetMapping("/detail/{no}")
	public ResponseEntity<NoticeBoardDto> detailBoard(@PathVariable int no) {
		logger.debug("detailBoard - 호출");
		return new ResponseEntity<NoticeBoardDto>(boardService.detailBoard(no), HttpStatus.OK);
	}
    

    /**
     * 새로운 게시글을 정보를 등록한다. create
     * @param board 입력한 게시글 정보 데이터 
     * @return
     */
    @ApiOperation(value = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/insertBoard")
	public ResponseEntity<String> writeBoard(@RequestBody NoticeBoardDto board) {
		logger.debug("writeBoard - 호출"+board);
		if (boardService.writeBoard(board)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    /**
     * 글 no 값에 대한 글 정보 수정 
     * @param board 선택한 게시글에 대한 글 정보
     * @return
     */
    @ApiOperation(value = "글번호에 해당하는 게시글의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/update/{no}")
	public ResponseEntity<String> updateBoard(@RequestBody NoticeBoardDto board) {
		logger.debug("updateBoard - 호출");
		logger.debug("" + board);
		
		if (boardService.updateBoard(board)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
    
  /**
   * 조회수  count
   * @param views 조회수
   */
    @ApiOperation(value = "글번호에 해당하는 views를 카운트를 올린다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/countViewsBoard/{no}")
	public void countViewsBoard(@PathVariable int no) {
		logger.debug("countViewsBoard - 호출");
		boardService.countViewsBoard(no);
	}
    

    /**
     * 해당 글 번호에 대한 정보 삭제
     * @param no 해당 글 번호
     * @return
     */
    @ApiOperation(value = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/delete/{no}")
	public ResponseEntity<String> deleteBoard(@PathVariable int no) {
		logger.debug("deleteBoard - 호출");
		if (boardService.deleteBoard(no)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}