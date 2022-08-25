package com.sys.bo.task.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sys.bo.task.system.domain.CodeMstDomain;


@Mapper
public interface CodeMapper  {
	int saveMstCode(CodeMstDomain mst);

	List<CodeMstDomain> selectMstCodeList(CodeMstDomain mst);
}
