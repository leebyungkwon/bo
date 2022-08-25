package com.sys.bo.common.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.sys.bo.task.login.domain.MemberDomain;

@Mapper
public interface CommonMapper  {
	
	MemberDomain saveLoginLog(MemberDomain memberDomain);
	
}
