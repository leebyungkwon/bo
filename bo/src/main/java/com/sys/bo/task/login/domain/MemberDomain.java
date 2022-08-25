package com.sys.bo.task.login.domain;

import java.util.List;

import org.apache.ibatis.type.Alias;

import com.sys.bo.common.domain.BaseDomain;

import lombok.Data;

@Data
@Alias("member")
public class MemberDomain extends BaseDomain{
	
	private String sysopid;
	private String code;
	private String password;
	private String name;
	private String department;
	private String email;
	private String phoneNo;
	private String mobileNo;
	private String createAt;
	private String updatePasswordAt;
	private String updateBy;
	private String inshopAdmionYn;
	private long inshopwSeq;
	
	List<MemberRoleDomain> roles;
	
}