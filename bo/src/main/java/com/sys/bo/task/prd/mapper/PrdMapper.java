package com.sys.bo.task.prd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sys.bo.task.prd.entity.PrdDomain;

@Mapper
public interface PrdMapper  {
	List<PrdDomain> selectPrd(PrdDomain p);
}
