package com.sys.bo.task.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sys.bo.task.board.domain.BoardDomain;
import com.sys.bo.task.board.mapper.BoardMapper;

@Service
public class BoardService {

	@Autowired 
	private BoardMapper boardMapper;

	public List<BoardDomain> selectBoardList(BoardDomain board) {
		return null;
	}


}
