package com.sys.bo.task.login.mapper;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sys.bo.task.member.entity.MemberRoleEntity;

public interface MemberRoleMapper extends JpaRepository<MemberRoleEntity, Long> {

}