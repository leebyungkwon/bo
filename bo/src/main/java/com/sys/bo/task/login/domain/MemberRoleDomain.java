package com.sys.bo.task.login.domain;

import org.apache.ibatis.type.Alias;

import com.sys.bo.common.domain.BaseDomain;

import lombok.Data;

@Data
@Alias("memberRole")
public class MemberRoleDomain extends BaseDomain{
	private String sysopid;
	private String role;
}
