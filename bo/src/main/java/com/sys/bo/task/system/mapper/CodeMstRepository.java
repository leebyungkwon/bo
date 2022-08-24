package com.sys.bo.task.system.mapper;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sys.bo.task.system.entity.CodeMstEntity;

public interface CodeMstRepository extends JpaRepository<CodeMstEntity, Long> {
	List<CodeMstEntity> findByCdMstNo(Long cdMstNo);
}
