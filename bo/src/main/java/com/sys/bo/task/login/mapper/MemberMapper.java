package com.sys.bo.task.login.mapper;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sys.bo.task.member.entity.MemberEntity;


public interface MemberMapper extends JpaRepository<MemberEntity, Long> {
	MemberEntity findByEmail(String email);
}