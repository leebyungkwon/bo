package com.sys.bo.task.board.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sys.bo.config.message.ResponseMsg;
import com.sys.bo.config.string.CosntPage;
import com.sys.bo.task.board.entity.BoardEntity;
import com.sys.bo.task.board.service.BoardService;

import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping(value="/bo/board")
public class BoardController {
	
	@Autowired BoardService boardService;
	
	@GetMapping(value="/boardList")
	public String boardList() {
		return CosntPage.BoBoardPage+"/boardList";
	}
	@GetMapping(value="/reg")
	public ModelAndView reg() {
    	ModelAndView mv = new ModelAndView(CosntPage.BoBoardPage+"/boardReg");
        return mv;
	}
	
	@GetMapping(value="/notice")
	public ModelAndView notice() {
    	ModelAndView mv = new ModelAndView(CosntPage.BoBoardPage+"/noticeList");
        return mv;
	}
	@PostMapping(value="/list")
	public ResponseEntity<ResponseMsg> list(HttpServletRequest request, BoardEntity board, Pageable pageable){
		Long logId = (Long) request.getAttribute("lid");
		ResponseMsg responseMsg = new ResponseMsg(HttpStatus.OK ,null );
		responseMsg.setLogId(logId);
    	responseMsg.setData(boardService.findAll(pageable));
    	responseMsg.setLogId(logId);
		return new ResponseEntity<ResponseMsg>(responseMsg ,HttpStatus.OK);
	}
	@GetMapping(value="/noticeReg")
	public ModelAndView noticeReg(BoardEntity board) {
    	ModelAndView mv = new ModelAndView(CosntPage.BoBoardPage+"/noticeReg");
    	String type = "NOTICE";
    	if(null != board.getBoardNo()) {
    		BoardEntity b = boardService.findById(board).get();
    		mv.addObject("board", b);
    		type = b.getBoardType();
    	}
		mv.addObject("type", type);
        return mv;
	}
	@PostMapping("/noticeReg")
    public ModelAndView noticeRegExce(BoardEntity board) {
		boardService.noticeSave(board);
    	ModelAndView mv = new ModelAndView("redirect:/"+CosntPage.BoBoardPage+"/notice");
        return mv;
    }
	@PostMapping("/p/noticeReg")
    public ModelAndView pNoticeRegExce() {
    	ModelAndView mv = new ModelAndView(CosntPage.BoBoardPage+"/p/pNoticeReg");
        return mv;
    }
	
	@PostMapping("/noticeSave")
	public ResponseEntity<ResponseMsg> noticeSave(BoardEntity board) {
		ResponseMsg responseMsg = boardService.noticeSave(board);
		
		return new ResponseEntity<>(responseMsg, HttpStatus.OK);
	}
	
	@GetMapping(value="/excelTmpl")
	public ModelAndView excelTmpl() {
    	ModelAndView mv = new ModelAndView(CosntPage.BoBoardPage+"/excelTmplList");
        return mv;
	}
}
