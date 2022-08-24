package com.sys.bo.task.templete.mapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sys.bo.task.board.entity.BoardEntity;

public interface TempleteJpaRepository extends JpaRepository<BoardEntity, Long> {

	Page<BoardEntity> findByBoardTitleIgnoreCaseStartsWith(String boardTitle, Pageable pageable);

}
