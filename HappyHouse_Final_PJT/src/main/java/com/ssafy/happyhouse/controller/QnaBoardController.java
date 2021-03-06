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

import com.ssafy.happyhouse.dto.QnaBoardDto;
import com.ssafy.happyhouse.service.QnaBoardService;

import io.swagger.annotations.ApiOperation;

//http://localhost:9999/happyhouse/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/qnaboard")
public class QnaBoardController {

	private static final Logger logger = LoggerFactory.getLogger(QnaBoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private QnaBoardService boardService;

	/**
	 * 모든 글 정보 반환
	 * @return
	 * @throws Exception
	 */
    @ApiOperation(value = "모든 게시글의 정보를 반환한다.", response = List.class)
	@GetMapping("/qselect")
	public ResponseEntity<List<QnaBoardDto>> retrieveBoard() throws Exception {
		logger.debug("selectedBoard - 호출");
		return new ResponseEntity<List<QnaBoardDto>>(boardService.retrieveBoard(), HttpStatus.OK);
	}

    /**
     * 해당 글 넘버에 대한 글 상세보기 제공
     * @param no 선택한 글 넘버
     * @return
     */
    @ApiOperation(value = "글번호에 해당하는 게시글의 정보를 반환한다.", response = QnaBoardDto.class)    
	@GetMapping("/qdetail/{no}")
	public ResponseEntity<QnaBoardDto> detailBoard(@PathVariable int no) {
		logger.debug("detailBoard - 호출");
		return new ResponseEntity<QnaBoardDto>(boardService.detailBoard(no), HttpStatus.OK);
	}

    /**
     * 새롭게 생성하고자 하는 글 등록
     * @param board 새롭게 적고자 하는 글에 대한 정보
     * @return
     */
    @ApiOperation(value = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/qinsertBoard")
	public ResponseEntity<String> writeBoard(@RequestBody QnaBoardDto board) {
		logger.debug("writeBoard - 호출");
		if (boardService.writeBoard(board)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    /**
     * 해당 글에 대한 정보 수정
     * @param board 가져온 글에 대한 정보
     * @return
     */
    @ApiOperation(value = "글번호에 해당하는 게시글의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/qupdate/{no}")
	public ResponseEntity<String> updateBoard(@RequestBody QnaBoardDto board) {
		logger.debug("updateBoard - 호출");
		logger.debug("" + board);
		
		if (boardService.updateBoard(board)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    /**
     * 선택한 글 넘버에 맞는 글 삭제
     * @param no 선택한 글의 넘버
     * @return
     */
    @ApiOperation(value = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/qdelete/{no}")
	public ResponseEntity<String> deleteBoard(@PathVariable int no) {
		logger.debug("deleteBoard - 호출");
		if (boardService.deleteBoard(no)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}