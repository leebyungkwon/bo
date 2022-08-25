package com.sys.bo.common.repository;

import org.apache.ibatis.annotations.Mapper;

import com.sys.bo.task.login.domain.MemberDomain;

@Mapper
public interface CommonRepository  {
	
	MemberDomain saveLoginLog(MemberDomain memberDomain);
	
}
