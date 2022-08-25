package com.sys.bo.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sys.bo.common.mapper.CommonMapper;
import com.sys.bo.task.login.domain.MemberDomain;

@Service
public class CommonService {
	
	@Autowired
	private CommonMapper commonRepository;
	
	// 로그인 로그 저장
	public void saveLoginLog(MemberDomain memberDomain) {
		commonRepository.saveLoginLog(memberDomain);
	}

}
