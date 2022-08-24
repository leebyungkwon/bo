package com.sys.bo.task.templete.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sys.bo.task.board.entity.BoardDomain;

@Mapper
public interface TempleteMapper  {
	List<BoardDomain> selectTemplete(BoardDomain entity);
}
