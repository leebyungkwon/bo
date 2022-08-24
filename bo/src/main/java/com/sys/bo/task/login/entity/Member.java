package com.sys.bo.task.login.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.sys.bo.task.member.entity.MemberEntity;
import com.sys.bo.task.member.entity.MemberRoleEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member extends User {
	private static final String ROLE_PREFIX = "ROLE_";
	private static final long serialVersionUID = 1L;
	
	public Member(MemberEntity member) { 
		super(member.getMemberNo().toString(), member.getPassword(), makeGrantedAuthority(member.getRoles()));
	}
	private static List<GrantedAuthority> makeGrantedAuthority(List<MemberRoleEntity> roles){
		List<GrantedAuthority> list = new ArrayList<>();
		roles.forEach(role -> list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.getRoleName())));
		return list;
	}
	
}