package com.sys.bo.task.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.bo.config.message.ResponseMsg;
import com.sys.bo.task.system.domain.CodeDetDomain;
import com.sys.bo.task.system.domain.CodeMstDomain;
import com.sys.bo.task.system.mapper.CodeMapper;

@Service
public class CodeService {

	@Autowired	private CodeMapper codeMapper;

	@Transactional
	public ResponseMsg saveMstCode(CodeMstDomain mst) {
		int b = codeMapper.saveMstCode(mst);
		if (b == 0)
			return new ResponseMsg(HttpStatus.BAD_GATEWAY, "COM0002");
		return new ResponseMsg(HttpStatus.OK, "COM0001", b, "저장 되었습니다.");
	}

	
	@Transactional(readOnly=true)
	public List<CodeMstDomain> selectMstCodeList(CodeMstDomain mst) {
		List<CodeMstDomain> list = codeMapper.selectMstCodeList(mst);
		return list;
		
	}

	@Transactional(readOnly=true)
	public List<CodeDetDomain> findByCodeMst(CodeMstDomain mst) {
		//return codeDetRepository.findByCodeMst(mst);
		return null;
	}


}
