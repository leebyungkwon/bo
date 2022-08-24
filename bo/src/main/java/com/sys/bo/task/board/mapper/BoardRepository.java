package com.sys.bo.task.board.mapper;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sys.bo.task.board.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

}
