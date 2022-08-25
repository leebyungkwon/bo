package com.sys.bo.task.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sys.bo.config.string.CosntPage;
import com.sys.bo.task.board.domain.BoardDomain;
import com.sys.bo.task.board.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value="/bo/system/board")
public class BoardController {
	
	@Autowired private BoardService boardService;

	@GetMapping(value="/list")
	public ModelAndView codePage() {
    	ModelAndView mv = new ModelAndView(CosntPage.BoSystemPage+"/board/boardPage");
        return mv;
	}

	@GetMapping(value="/mst")
	public ModelAndView codeDet(BoardDomain board) {
    	ModelAndView mv = new ModelAndView(CosntPage.BoSystemPage+"/board/p/codeMst");
    	List<BoardDomain> p = boardService.selectBoardList(board);
    	
    	System.out.println(p);
    	mv.addObject("data", p);
        return mv;
	}
	
}
