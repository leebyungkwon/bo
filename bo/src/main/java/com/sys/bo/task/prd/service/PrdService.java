package com.sys.bo.task.prd.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.bo.config.message.ResponseMsg;
import com.sys.bo.task.prd.entity.PrdDomain;
import com.sys.bo.task.prd.entity.PrdEntity;
import com.sys.bo.task.prd.mapper.PrdMapper;
import com.sys.bo.task.prd.mapper.PrdRepository;

@Service
public class PrdService {

	@Autowired
	private PrdRepository prdRepository;
	
	@Autowired
	private PrdMapper prdMapper;

	@Transactional
	public ResponseMsg prdSave(PrdEntity prd) {
		PrdEntity b = prdRepository.save(prd);
		if (b == null)
			return new ResponseMsg(HttpStatus.BAD_GATEWAY, "COM0002");
		return new ResponseMsg(HttpStatus.OK, "COM0001", b, "저장</br>되었습니다.");
	}

	
	@Transactional(readOnly=true)
	public Optional<PrdEntity> findById(PrdEntity prd) {
		return prdRepository.findById(prd.getPrdNo());
	}


	public List<PrdDomain> selectPrd(PrdDomain prd) {
		return prdMapper.selectPrd(prd);
	}

}
