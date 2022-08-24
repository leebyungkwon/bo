package com.sys.bo.task.system.mapper;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sys.bo.task.system.entity.CodeDetEntity;
import com.sys.bo.task.system.entity.CodeMstEntity;

public interface CodeDetRepository extends JpaRepository<CodeDetEntity, Long> {
	List<CodeDetEntity> findByCodeMst(CodeMstEntity codeMst);
}
