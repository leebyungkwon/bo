package com.sys.bo.task.login.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.sys.bo.task.login.domain.MemberDomain;

@Mapper
public interface MemberRepository  {
	MemberDomain findById(String memberId);
}
