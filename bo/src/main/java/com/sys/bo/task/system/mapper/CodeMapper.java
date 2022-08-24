package com.sys.bo.task.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sys.bo.task.prd.entity.PrdDomain;

@Mapper
public interface CodeMapper  {
	List<PrdDomain> selectPrd(PrdDomain p);
}
