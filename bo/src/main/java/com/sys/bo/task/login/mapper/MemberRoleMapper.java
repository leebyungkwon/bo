package com.sys.bo.task.login.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.sys.bo.task.login.domain.MemberRoleDomain;

@Mapper
public interface MemberRoleMapper  {
	List<MemberRoleDomain> getMemberRoles(String sysopid);
}
