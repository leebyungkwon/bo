package com.sys.bo.task.login.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.sys.bo.task.login.domain.MemberRoleDomain;

@Mapper
public interface MemberRoleRepository  {
	List<MemberRoleDomain> getMemberRoles(String sysopid);
}
