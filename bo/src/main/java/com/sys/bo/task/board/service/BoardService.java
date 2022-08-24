package com.sys.bo.task.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sys.bo.config.message.ResponseMsg;
import com.sys.bo.task.board.entity.BoardEntity;
import com.sys.bo.task.board.mapper.BoardRepository;

@Service
public class BoardService {

	@Autowired private BoardRepository boardRepository;
	
	public ResponseMsg noticeSave(BoardEntity board) {
		BoardEntity b = boardRepository.save(board);
		if(b != null) return new ResponseMsg(HttpStatus.BAD_GATEWAY ,"COM0002");
		return new ResponseMsg(HttpStatus.OK ,"COM0001", b,"저장저장저장</br>되었다");
	}
	public List<BoardEntity> findAll(PageRequest pageable) {
		Page<BoardEntity> p = boardRepository.findAll(pageable);
		return p.getContent();
	}
	
	public HashMap<String, Object> findAll(Pageable pageable) {
			HashMap<String, Object> result = new HashMap<String, Object>(); 
		Page<BoardEntity> p = boardRepository.findAll(pageable);
		result.put("list", p.getContent());
		result.put("pageCnt", p.getTotalPages());
		return result;
	}
	public Optional<BoardEntity> findById(BoardEntity board) {
		return boardRepository.findById(board.getBoardNo());
	}

}
