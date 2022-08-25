package com.sys.bo.task.login.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.sys.bo.task.login.domain.MemberDomain;

@Mapper
public interface MemberMapper  {
	MemberDomain findById(String memberId);
}
