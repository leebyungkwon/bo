package com.sys.bo.task.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sys.bo.task.board.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired 
	private BoardRepository boardRepository;


}
