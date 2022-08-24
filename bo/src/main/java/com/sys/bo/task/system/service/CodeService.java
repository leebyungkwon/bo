package com.sys.bo.task.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.bo.config.message.ResponseMsg;
import com.sys.bo.task.system.entity.CodeDetEntity;
import com.sys.bo.task.system.entity.CodeMstEntity;
import com.sys.bo.task.system.mapper.CodeDetRepository;
import com.sys.bo.task.system.mapper.CodeMstRepository;

@Service
public class CodeService {

	@Autowired	private CodeMstRepository codeMstRepository;
	@Autowired	private CodeDetRepository codeDetRepository;

	@Transactional
	public ResponseMsg codeMstSave(CodeMstEntity mst) {
		CodeMstEntity b = codeMstRepository.save(mst);
		if (b == null)
			return new ResponseMsg(HttpStatus.BAD_GATEWAY, "COM0002");
		return new ResponseMsg(HttpStatus.OK, "COM0001", b, "저장 되었습니다.");
	}

	
	@Transactional(readOnly=true)
	public List<CodeMstEntity> mstFindById(CodeMstEntity mst) {
		//if(mst == null)	return codeMstRepository.findByCdMstNo(mst.getCdMstNo());
		//else return codeMstRepository.findByCdMstNo(mst.getCdMstNo());
		return codeMstRepository.findByCdMstNo(mst.getCdMstNo());
	}

	@Transactional(readOnly=true)
	public List<CodeDetEntity> findByCodeMst(CodeMstEntity mst) {
		return codeDetRepository.findByCodeMst(mst);
	}


}
