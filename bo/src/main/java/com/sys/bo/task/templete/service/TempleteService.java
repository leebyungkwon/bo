package com.sys.bo.task.templete.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.bo.config.message.ResponseMsg;
import com.sys.bo.task.board.entity.BoardDomain;
import com.sys.bo.task.board.entity.BoardEntity;
import com.sys.bo.task.templete.mapper.TempleteJpaRepository;
import com.sys.bo.task.templete.mapper.TempleteMapper;

@Service
public class TempleteService {

	@Autowired
	private TempleteJpaRepository templeteJpaRepository;
	@Autowired
	private TempleteMapper templeteRepository;

	@Transactional
	public ResponseMsg templeteSave(BoardEntity board) {
		System.out.println("### templeteSave :: "+board);
		BoardEntity b = templeteJpaRepository.save(board);
		System.out.println("### templeteSave :: "+b);
		if (b == null)
			return new ResponseMsg(HttpStatus.BAD_GATEWAY, "COM0002");
		return new ResponseMsg(HttpStatus.OK, "COM0001", b, "저장</br>되었습니다.");
	}

	@Transactional(readOnly=true)
	public HashMap<String, Object> findAll(BoardEntity board, Pageable pageable) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		//Page<BoardEntity> p = templeteRepository.findAll(pageable);

		Page<BoardEntity> p = templeteJpaRepository.findByBoardTitleIgnoreCaseStartsWith(board.getBoardTitle(), pageable);
		
		result.put("data", p);
		result.put("list", p.getContent());
		result.put("pageCnt", p.getTotalPages());
		return result;
	}
	
	@Transactional(readOnly=true)
	public Optional<BoardEntity> findById(BoardEntity board) {
		return templeteJpaRepository.findById(board.getBoardNo());
	}

	@Transactional
	public List<BoardDomain> selectTemplete(BoardDomain board) {
		return templeteRepository.selectTemplete(board);
	}

}
